import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author 李星源
 * @date 2021/02/25
 */
public class FileUtil {
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    private static final int IN_BUFFER = 8192;// 输入缓冲区大小，单位：字节
    private static final int OUT_BUFFER = 8192;// 输出缓冲区大小，单位：字节

    /**
     * 文件读入，使用缓存流
     *
     * @param file 输入文件
     * @return 文件内容，如果空则为""
     */
    public static String read(File file){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), ENCODING), IN_BUFFER);
            StringBuilder sb = new StringBuilder();
            String oneLine = in.readLine();
            while (oneLine != null){
                sb.append(oneLine).append("\n");
                oneLine = in.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            System.err.println(ExceptionInfo.READ_FILE_ERROR);
            System.exit(0);
        } finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void write(File file, String content){
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, false), ENCODING), OUT_BUFFER);
            out.write(content);
            out.flush();
        } catch (IOException e) {
            System.err.println(ExceptionInfo.WRITE_FILE_ERROR);
            System.exit(0);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
