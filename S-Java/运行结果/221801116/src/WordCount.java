import java.io.*;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordCount {
    public static void main(String[] args){
        File dir = new File("...");
        File input = new File(args[0]);
        String inputPath = null;
        String outputPath = null;
        if(input.exists()){
            try{
                inputPath = args[0];
                outputPath = args[1];
            }catch (IndexOutOfBoundsException e){
                System.out.println("请检查是否正确输入命令行参数\n当前输入文件位置为\n"+inputPath);
                System.exit(-1);
            }
        }else{
            try{
                inputPath = dir.getCanonicalPath()+args[0];
                outputPath = dir.getCanonicalPath()+args[1];
            }catch (IndexOutOfBoundsException | IOException e){
                System.out.println("请检查是否正确输入命令行参数\n当前输入文件位置为\n"+inputPath);
                System.exit(-1);
            }
        }



        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath),"UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("错误位于WordCount类main方法,原因可能是未能正确创建output.txt文件");
            System.exit(-1);
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<GetMyTask> taskList = new ArrayList<>();
        for(int i = 1;i < 5;i++){
            taskList.add(new GetMyTask(inputPath,i));
        }
        try{
            List<Future<String>> futureList = executorService.invokeAll(taskList);
            for(Future<String> future : futureList){
                try {
                    String str = future.get();
                    if(str!=null){
                        bw.write(str);
                    }
                } catch (IOException e) {
                    System.out.println("错误位于WordCount类main方法,原因可能是文件流读写异常");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
