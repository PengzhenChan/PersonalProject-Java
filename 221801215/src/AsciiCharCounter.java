import java.io.*;

/**
 * 统计文件ASCII码字符数量的类
 *
 * <p>用来统计文件的ASCII字符数，提供统计方法。</p>
 */
public class AsciiCharCounter {
    /**
     * 计算输入文件ASCII字符数
     *
     * @param file the file 要统计的文件
     * @return the int 文件的ASCII字符数
     */
    public int countAsciiChar(File file) {
        int asciiCharNumber =0;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(file),Config.CHARSET))) {
            int currentChar;
            while ((currentChar = bufferedReader.read()) != -1) {
                if (isAsciiChar(currentChar)) {
                    asciiCharNumber++;
                }
            }
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("未找到文件");
            fileNotFoundException.printStackTrace();
        }catch (UnsupportedEncodingException unsupportedEncodingException) {
            System.out.println(Config.UNSUPPORTED_ENCODING_EXCEPTION_TIP);
            unsupportedEncodingException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println(Config.IO_EXCEPTION_TIP);
            ioException.printStackTrace();
        }
        return asciiCharNumber;
    }

    /**
     * 判断ASCII字符
     *
     * @param inputChar the input char 需要判断的字符
     * @return the boolean 是否为ASCII字符
     */
    public boolean isAsciiChar(int inputChar) {
        boolean result = false;
        if (inputChar < 128) {
            result = true;
        }
        return result;
    }
}
