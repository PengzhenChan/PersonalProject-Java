import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class WordCount {
    public static void main(String[] args) {
        if(args.length!=2||!args[0].endsWith(".txt")||!args[1].endsWith(".txt")){
            System.out.println("输入非法参数，程序结束！");
            return;
        }

        //文件文本内容
        String context = "";

        //将要写入文件的内容
        String result = "";

        //字符数、单词数、有效行数
        int countChar, countWord, countLine;

        //存放频率前10的单词和出现次数
        List<Map.Entry<String,Integer>> maxFrequency;

        String inputFile = args[0];
        String outputFile = args[1];

        context = Lib.txtToString(inputFile);
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
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
            out.write(result);
            out.close();
        } catch (IOException e) {
            System.out.println("写入文件失败！");
            e.printStackTrace();
        }

    }
}
