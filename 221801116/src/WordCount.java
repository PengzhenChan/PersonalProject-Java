import java.io.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        File dir = new File(".");
        String inputPath = dir.getCanonicalPath()+"\\"+args[0];
        String outputPath = dir.getCanonicalPath()+"\\"+args[1];

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath)));
        } catch (FileNotFoundException e) {
            System.out.println("错误位于WordCount类main方法,原因可能是未能正确创建output.txt文件");
        }
        try {
            bw.write(CountChar.countChar(inputPath));
            bw.write(CountWord.countWord(inputPath));
            bw.write(CountLine.countLine(inputPath));
        } catch (IOException e) {
            System.out.println("错误位于WordCount类main方法,原因可能是文件流读写异常");
        }
        String[] wordStr = CountWordRate.countWordRate(inputPath);
        for(int i = 0;i < 10;i++){
            if(wordStr[i]!=null){
                try {
                    bw.write(wordStr[i]);
                } catch (IOException e) {
                    System.out.println("错误位于WordCount类main方法,原因可能是文件流读写异常");
                }
            }else{
                break;
            }
        }
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("错误位于WordCount类main方法,原因可能是未能正确关闭文件流");
        }
    }
}
