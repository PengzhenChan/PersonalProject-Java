import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCountIO {
    /**
     * 读取文件转化为string形式
     * @param filePath 文件路径
     * @return 文件内容转成的String
     */
    public static String fileToString(String filePath) {
        StringBuffer strBuf = new StringBuffer();
        String line;
        
        try {
            InputStream inpStr = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inpStr));
            line = br.readLine();
            strBuf.append(line);
            if((line = br.readLine())!= null) {
                do {
                    strBuf.append(line);
                    strBuf.append("\n");
                } while ((line = br.readLine())!= null);
            }
            inpStr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuf.toString();
    }
    
    /**
     * 创建以utf-8编码的文件输出流
     * @param name 文件路径
     * @return BufferedWriter 以utf-8编码的文件输出流
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static BufferedWriter createFileWriter(String name)
            throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream outputStream = new FileOutputStream(name);  
        OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream, "utf-8");
        BufferedWriter writer = new BufferedWriter(outputWriter);
        return writer;
    }
    
    /**
     * 输出结果到指定文件
     * @throws IOException 
     */
    public static void output(int charsNum,int wordsNum,int linesNum,
            List<Map.Entry<String, Integer>> highFreqList,String filePath) 
            throws IOException {
        StringBuilder str = new StringBuilder("characters: " + charsNum + "\r\n"
                + "words:" + wordsNum + "\r\n"
                + "lines:" + linesNum + "\r\n");
        for (int i = 0; i < highFreqList.size(); i++) {
            Map.Entry<String, Integer> temp = highFreqList.get(i);
            str.append(temp.getKey());
            str.append(":");
            str.append(temp.getValue());
            str.append("\r\n");
        }
        BufferedWriter writer = createFileWriter(filePath);
        
        try {
            writer.write(str.toString());
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (writer != null){
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭文件输入流失败");
                }
            }
        }
    }

}
