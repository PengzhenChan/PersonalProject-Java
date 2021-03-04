import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        FutureTask<HashMap<String, Integer>> futureTask1;
        FutureTask<Integer> futureTask2;
        //开始计时
        long startTime = System.currentTimeMillis();

        String inputFile = "input7.txt";
        String outputFileName = "out7.txt";
        String content;     //文本内容
        long asciiCharNum;
        long wordNum;
        long lines = 0;
        HashMap<String, Integer> legalWords = new LinkedHashMap<>();

        content = ReadTxt.readTxt(inputFile);
        asciiCharNum = CountAsciiChar.countChar(content);

        //合法单词的hashMap
//        legalWords = SplitWord.findLegal(content);
        Callable callable1 = new Callable() {
            HashMap<String, Integer> legalWords;
            @Override
            public HashMap<String, Integer> call() {
                try {
                    Thread.sleep(5);
                }catch (Exception e){
                    e.printStackTrace();
                }
                this.legalWords = SplitWord.findLegal(content);
                countDownLatch.countDown();
                return legalWords;
            }
        };
        futureTask1 = new FutureTask<HashMap<String, Integer>>(callable1);
        new Thread(futureTask1).start();     //创建新线程


//        lines = CountLine.countLine(content);
        Callable callable2 = new Callable() {
            Integer line;
            @Override
            public Integer call() {
                line = CountLine.countLine(content);
//                line = CountLine.countLine1(inputFile);
                countDownLatch.countDown();
                return line;
            }
        };
        futureTask2 = new FutureTask<Integer>(callable2);
        new Thread(futureTask2).start();

        //取出结果
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

//        hashMap = CountFrequency.countFrequency(content);
//        hashMap = CountFrequency.countFrequency(legalWords);
        try {
//            System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
            countDownLatch.await(); //阻塞当前主线程
//            System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        wordNum = SplitWord.countWordNum();
        List<Map.Entry<String, Integer>> list = CountFrequency.sortHashMap(legalWords);


        OutputToTxt.outputToTxt(asciiCharNum, wordNum, lines, list, outputFileName);

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行总时间："+(endTime-startTime)+"ms");
    }
}


