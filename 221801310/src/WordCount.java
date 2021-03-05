import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        File inputFile;
        File outputFile;
        //通过命令行给出文件路径
       if(args.length!=2){
           System.out.println("请输入两个文件的绝对路径或相对路径！");
           return;
       }
       inputFile = new File(args[0]);
       outputFile = new File(args[1]);

        final CountDownLatch countDownLatch = new CountDownLatch(2);
        FutureTask<HashMap<String, Integer>> futureTask1;
        FutureTask<Long> futureTask2;

        String content;     //文本内容
        long asciiCharNum;
        long wordNum;
        long lines = 0;
        HashMap<String, Integer> legalWords = new LinkedHashMap<>();

        content = Lib.readTxt(inputFile);
        asciiCharNum = Lib.countChar(content);
        //线程1
        Callable<HashMap<String, Integer>> callable1 = new Callable<HashMap<String, Integer>>() {
            HashMap<String, Integer> legalWords;
            @Override
            public HashMap<String, Integer> call() {
                try {
                    Thread.sleep(5);
                }catch (Exception e){
                    e.printStackTrace();
                }
                this.legalWords = Lib.findLegal(content);
                countDownLatch.countDown();

                return this.legalWords;
            }
        };
        futureTask1 = new FutureTask<HashMap<String, Integer>>(callable1);
        new Thread(futureTask1).start();     //创建新线程
        //线程2
        Callable<Long> callable2 = new Callable<Long>() {
            Long line;
            @Override
            public Long call() {
                line = Lib.countLine(content);
                countDownLatch.countDown();
                return line;
            }
        };
        futureTask2 = new FutureTask<Long>(callable2);
        new Thread(futureTask2).start();

        try {
            lines = futureTask2.get();
            legalWords = futureTask1.get();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            countDownLatch.await(); //阻塞当前主线程
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        wordNum = Lib.countWordNum();
        List<Map.Entry<String, Integer>> list = Lib.sortHashMap(legalWords);
        Lib.outputToTxt(asciiCharNum, wordNum, lines, list, outputFile);
    }
}