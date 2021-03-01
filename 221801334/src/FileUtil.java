import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 文件输入输出工具类
 *
 * @author 李星源221801334
 * @date 2021/02/25
 */
public class FileUtil {
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * 文件读入，使用mmap
     *
     * @param file 输入文件
     * @return 文件内容，如果空则为""
     */
    public static String readMMAP(File file){
        RandomAccessFile raf = null;
        MappedByteBuffer mbb = null;
        try {
            raf = new RandomAccessFile(file, "r");
            mbb = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            if (mbb != null){
                return ENCODING.decode(mbb).toString();
            } else {
                return "";
            }
        } catch (IOException e) {
            System.out.println(ExceptionInfo.READ_FILE_ERROR.getMessage());
            System.exit(0);
        } finally {
            if (mbb != null){
                Cleaner var1 = ((DirectBuffer)mbb).cleaner();
                if (var1 != null) {
                    var1.clean();
                }
            }
            if (raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 写入文件
     *
     * @param file 输出文件
     * @param content 内容
     */
    public static void write(File file, String content){
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, false), ENCODING), 1024);
            out.write(content);
            out.flush();
        } catch (IOException e) {
            System.out.println(ExceptionInfo.WRITE_FILE_ERROR);
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
