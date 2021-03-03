import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordCount {
    public static void main(String[] args) throws IOException {
        File dir = new File(".");
        String inputPath = dir.getCanonicalPath()+"\\"+args[0];
        String outputPath = dir.getCanonicalPath()+"\\"+args[1];

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath),"UTF-8"));
        } catch (FileNotFoundException e) {
            System.out.println("错误位于WordCount类main方法,原因可能是未能正确创建output.txt文件");
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<GetMyTask> taskList = new ArrayList<>();
        for(int i=1;i<5;i++){
            taskList.add(new GetMyTask(inputPath,i));
        }
        try{
            List<Future<String>> futureList = executorService.invokeAll(taskList);
            for(Future<String> future : futureList){
                try {
                    bw.write(future.get());
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
            System.out.println("错误位于WordCount类main方法,原因可能是未能正确关闭文件流");
        }
    }
}
