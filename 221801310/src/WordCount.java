import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;
import java.util.zip.CheckedOutputStream;

/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        String inputFile = "test1.txt";
        String outputFileName = "testout1.txt";
        String content;     //文本内容
        long asciiCharNum = 0;
        int wordNum = 0;
        int lines = 0;
        HashMap<String, Integer> legalWords;

        long startTime1 = System.currentTimeMillis();
        content = ReadTxt.readTxt(inputFile);
//        content = ReadTxt.openFile(inputFile);


        long endTime1 = System.currentTimeMillis();
        System.out.println("读取文件时间："+(endTime1-startTime1)+"ms");

///////////////////////////////////////////////////////////////////////

        long startTime2 = System.currentTimeMillis();
        asciiCharNum = CountAsciiChar.countChar(content);
        long endTime2 = System.currentTimeMillis();
        System.out.println("计算ASCII码时间："+(endTime2-startTime2)+"ms");

///////////////////////////////////////////////////////////////////////

        long startTime3 = System.currentTimeMillis();

        //合法单词的hashMap
        legalWords = SplitWord.findLegal(content);
        wordNum = CountWord.countWordNum(legalWords);

        long endTime3 = System.currentTimeMillis();
        System.out.println("计算合法单词数时间："+(endTime3-startTime3)+"ms");

///////////////////////////////////////////////////////////////////////
        long startTime4 = System.currentTimeMillis();

        lines = CountLine.countLine(content);

        long endTime4 = System.currentTimeMillis();
        System.out.println("计算有效行数时间："+(endTime4-startTime4)+"ms");

///////////////////////////////////////////////////////////////////////
        long startTime5 = System.currentTimeMillis();

//        hashMap = CountFrequency.countFrequency(content);
//        hashMap = CountFrequency.countFrequency(legalWords);
        List<Map.Entry<String, Integer>> list = CountFrequency.sortHashMap(legalWords);

        long endTime5 = System.currentTimeMillis();
        System.out.println("排序时间："+(endTime5-startTime5)+"ms");

///////////////////////////////////////////////////////////////////////

        long startTime6 = System.currentTimeMillis();
        OutputToTxt.outputToTxt(asciiCharNum, wordNum, lines, list, outputFileName);
        long endTime6 = System.currentTimeMillis();
        System.out.println("输出到文件时间："+(endTime6-startTime6)+"ms");

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行总时间："+(endTime-startTime)+"ms");
    }
}


