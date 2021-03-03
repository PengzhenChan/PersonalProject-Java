import java.util.zip.CheckedOutputStream;

/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        String content;
        int asciiCharNum = 0;
        int wordNum = 0;

        content = ReadTxt.readTxt(inputFile);
        System.out.println(content);

        asciiCharNum = CountAsciiChar.countChar(content);
        System.out.println(asciiCharNum);

        wordNum = CountWord.countWordNum(content);
        System.out.println(wordNum);
    }
}