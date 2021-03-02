import java.io.*;

/* IO工具 */
public class IOTool {

    /* 从文件读取字符流 */
    public StringBuffer fileInputToString(String filePath) throws IOException {
        //UTF-8编码进行读取
        FileInputStream file = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(file,"UTF-8");
        BufferedReader br = new BufferedReader(reader);
        //读取文件流存在字符串中
        int temp;
        StringBuffer fileResult = new StringBuffer();
        while ((temp = br.read()) != -1) {
            fileResult.append((char)temp);
        }
        //关闭文件流
        file.close();
        return  fileResult;
    }

    /* 将结果输出到文件中 */
    public void OutputToFile (String filePath, int sumCharacter,int wordsNum) throws IOException {
        FileOutputStream file = new FileOutputStream(filePath);
        OutputStreamWriter writer = new OutputStreamWriter(file,"UTF-8");
        //输出内容
        String context ="characters:"+sumCharacter+System.getProperty("line.separator");
        context += "words:"+wordsNum+System.getProperty("line.separator");
        //转码成UTF-8
        byte[] bytes = context.getBytes("UTF-8");
        file.write(bytes);
        //关闭文件流
        file.close();
    }

}

