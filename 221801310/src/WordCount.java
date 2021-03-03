import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        String inputFile = "input1.txt";
        String outputFileName = "output1.txt";
        String content;
        int asciiCharNum = 0;
        int wordNum = 0;
        int lines = 0;
        HashMap<String, Integer> hashMap;

        content = ReadTxt.readTxt(inputFile);
        asciiCharNum = CountAsciiChar.countChar(content);
        wordNum = CountWord.countWordNum(content);
        lines = CountLine.countLine(content);

        hashMap = CountFrequency.countFrequency(content);
        List<Map.Entry<String, Integer>> list = CountFrequency.sortHashMap(hashMap);

        OutputToTxt.outputToTxt(asciiCharNum, wordNum, lines, list, outputFileName);

        return ;
    }
}