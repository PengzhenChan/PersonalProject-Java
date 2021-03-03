import java.util.HashMap;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        String content;
        int asciiCharNum = 0;
        int wordNum = 0;
        int lines = 0;

        content = ReadTxt.readTxt(inputFile);
//        System.out.println(content);

        lines = CountLine.countLine(content);
        System.out.println(lines);
//        asciiCharNum = CountAsciiChar.countChar(content);
//        System.out.println(asciiCharNum);

        wordNum = CountWord.countWordNum(content);
        System.out.println(wordNum);
//        HashMap<String, Integer> hashMap = CountFrequency.countFrequency(content);
//        for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
    }
}