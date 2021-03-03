import java.io.IOException;


public class WordCount {
    public static void main(String[] args) {
        try {
            String context = Lib.txtToString("1.txt");
            System.out.println("characters: " + Lib.countChar(context));
            System.out.println("words:" + Lib.countWord(context));
            System.out.println("lines:" + Lib.countLine(context));

        } catch (IOException e){
            System.out.println("txt文件转字符串失败！");
        }
    }
}
