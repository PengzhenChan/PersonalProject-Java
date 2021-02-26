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
            while ((line = br.readLine())!= null) {
                strBuf.append(line);
                strBuf.append("\n");
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
    public static BufferedWriter createFileWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        FileOutputStream outputStream = new FileOutputStream(name);  
        OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream, "utf-8");
        BufferedWriter writer = new BufferedWriter(outputWriter);
        return writer;
    }
    
    /**
     * 输出结果到指定文件
     * @throws IOException 
     */
    public static void output(int charsNum,String filePath) throws IOException {
        StringBuilder str = new StringBuilder("characters: " + charsNum + "\r\n");
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
                    e.printStackTrace();
                }
            }
        }
    }
}
