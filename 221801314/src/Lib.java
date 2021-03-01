import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lib {
    //文件io工具类
    public static class FileIOUtil {
        /**
         * @Description 通过文件路径读入文件，返回BufferedReader的流
         * @Author Lvv
         * @Date 2021/2/28 15:11
         * @Param [filePath]
         * @return int
         **/
        public BufferedReader readFile(String filePath) {
            File file = new File(filePath);
            FileInputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                inputStream = new FileInputStream(file);
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (FileNotFoundException e) {
                System.out.println("打开inputStream时 找不到文件路径");
                System.out.println("当前路径"+System.getProperty("user.dir"));
                e.printStackTrace();
            } finally {
//                try {
//                    bufferedReader.close();
//                    inputStreamReader.close();
//                    inputStream.close();
//                } catch (IOException e) {
//                    System.out.println("关闭inputStream时 异常");
//                    e.printStackTrace();
//                }
            }
            return bufferedReader;
        }
    }

    //String处理类
    public static class TextEditor{
        BufferedReader reader = null;
        public TextEditor(BufferedReader reader){
            this.reader = reader;
        }

        private List<String> readString() {
            String content = null;
            List<String> arr = new ArrayList<>();
            if (reader == null){
                System.out.println("Reader为空");
                return null;
            }
            try {
                while ((content = reader.readLine()) != null) {
                    //测试输出读取内容
//                    content += '\n';
                    System.out.println(content);
                    arr.add(content);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            return arr;
        }
        /**
         * @Description 统计String的Ascii字符，返回Ascii字符数
         * @Author Lvv
         * @Date 2021/2/28 16:54
         * @Param [str]
         * @return int
         **/
        public int countAscii() {
            int sum = 0;
            List<String> arr = readString();
            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < arr.get(i).length(); j++) {
                    String str = arr.get(i);
                    char ch = str.charAt(j);
                    if (ch >= 0 && ch < 128)
                        sum++;
                }
            }
            //测试输出统计的总数
            System.out.println("ASCII sums:" + sum);
            return sum;
        }
//        public int countWords(String str) {
//
//        }
//        public int countRows(String str) {
//
//        }
    }

}
