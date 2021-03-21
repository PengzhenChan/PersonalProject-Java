import java.io.*;
import java.util.List;
import java.util.Map;

/* IO工具 */
public class IOTool {

    /* 从文件读取字符流 */
    public StringBuilder fileInputToString(String filePath) throws IOException {
        //UTF-8编码进行读取
        FileInputStream file = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(file,"UTF-8");
        BufferedReader br = new BufferedReader(reader);

        //读取文件流存在字符串中
        int temp;
        StringBuilder fileResult = new StringBuilder();
        while ((temp = br.read()) != -1) {
            fileResult.append((char)temp);
        }

        //关闭文件流
        file.close();
        return  fileResult;
    }

    /* 将结果输出到文件中 */
    public void OutputToFile (String filePath, int sumCharacter, int wordsNum, int lines,
                              List<Map.Entry<String, Integer>> list) throws IOException {
        //以UTF-8格式输出到文件中
        FileOutputStream file = new FileOutputStream(filePath);
        OutputStreamWriter writer = new OutputStreamWriter(file,"UTF-8");

        //输出总字符数
        String context ="characters: "+sumCharacter+System.getProperty("line.separator");
        //输出有效单词数
        context += "words: "+wordsNum+System.getProperty("line.separator");
        //输出有效行数
        context += "lines: "+lines+System.getProperty("line.separator");
        //输出频次前10的单词数
        int num = 0;
        for(Map.Entry<String,Integer> mapping:list){
            num++;
            context += mapping.getKey()+": "+mapping.getValue()+System.getProperty("line.separator");
            if (num == 10)
                break;
        }

        //转码成UTF-8
        byte[] bytes = context.getBytes("UTF-8");
        file.write(bytes);
        //关闭文件流
        file.close();
    }
}

