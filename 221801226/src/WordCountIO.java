import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class WordCountIO {
    /**
     * 读取文件转化为string形式
     * @param filePath 文件路径
     * @return 文件内容转成的String
     * @throws IOException 
     */
    public static String fileToString(String filePath){
        StringBuilder strBud = new StringBuilder();
        try {
            FileInputStream fileinputstream=new FileInputStream(filePath);
            InputStreamReader inputstreamreader=new InputStreamReader(fileinputstream);
            BufferedReader br = new BufferedReader(inputstreamreader);
            int c;
            while ((c = br.read()) != -1) {
                strBud.append((char) c);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:文件未找到...\n");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("ERROR:输入流出错...");
        }
        return strBud.toString();
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
        StringBuilder str = new StringBuilder("characters: " + charsNum + "\n"
                + "words: " + wordsNum + "\n"
                + "lines: " + linesNum + "\n");
        for (int i = 0; i < highFreqList.size(); i++) {
            Map.Entry<String, Integer> temp = highFreqList.get(i);
            str.append(temp.getKey());
            str.append(": ");
            str.append(temp.getValue());
            str.append("\n");
        }
        BufferedWriter writer = createFileWriter(filePath);
        
        try {
            writer.write(str.toString());
        } catch (IOException e){
            System.out.println("ERROR:写文件出错...\n");
            e.printStackTrace();
        } finally {
            if (writer != null){
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException("ERROR:关闭输出流出错...");
                }
            }
        }
    }

}
