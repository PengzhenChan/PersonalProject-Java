import java.io.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        File dir = new File(".");
        String inputPath = dir.getCanonicalPath()+"\\"+args[0];
        String outputPath = dir.getCanonicalPath()+"\\"+args[1];

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath)));
        bw.write(CountChar.countChar(inputPath));
        bw.write(CountWord.countWord(inputPath));
        bw.write(CountLine.countLine(inputPath));
        String[] wordStr = CountWordRate.countWordRate(inputPath);
        for(int i = 0;i < 10;i++){
            if(wordStr[i]!=null){
                bw.write(wordStr[i]);
            }else{
                break;
            }
        }
        bw.flush();
    }
}
