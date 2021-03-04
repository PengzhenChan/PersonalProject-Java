import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class WordCount {
    public static void main(String[] args) {
        String context = "";
        String result = "";
        List<Map.Entry<String,Integer>> maxFrequency;
        int countChar, countWord, countLine;

       // String inputFile = args[0];
        //String outputFile = args[1];

        String inputFile = "1.txt";
        String outputFile = "2.txt";


        try {
            context = Lib.txtToString(inputFile);
        } catch (IOException e){
            System.out.println("txt文件转字符串失败！");
            return;
        }
        if(context == null|| context.equals("")){
            System.out.println("待读取文本内容为空！");
            return;
        }

        countChar = Lib.countChar(context);
        countWord = Lib.countWord(context);
        countLine = Lib.countLine(context);
        maxFrequency = Lib.staticFrequency(context);
        result = "characters: " + countChar + "\n" + "words:" +countWord + "\n" + "lines:" + countLine + "\n";
        for(int i = 0; i<maxFrequency.size(); i++){
            result = result.concat(maxFrequency.get(i).getKey() + ": " + maxFrequency.get(i).getValue() + "\n");
        }

        /*
        在控制台输出结果
         */
        System.out.println(result);

        /*
        文件中输出结果
         */

    }
}
