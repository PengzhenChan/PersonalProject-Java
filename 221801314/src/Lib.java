import sun.nio.cs.ext.ISCII91;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    //文件io工具类
    public static class FileIOUtil {
        InputStream input = null;
        /**
         * @Description 创建一个打开文件的InputStream
         * @Author Lvv
         * @Date 2021/3/1 20:05
         * @Param [filePath]
         * @return java.io.InputStream
         **/
        public InputStream openStream(String filePath) {
            File file = new File(filePath);
            try {
                input = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.out.println("文件未找到");
                e.printStackTrace();
            }
            return input;
        }

        /**
         * @Description 关闭流
         * @Author Lvv
         * @Date 2021/3/1 20:06
         * @Param []
         * @return boolean
         **/
        public boolean closeStream() {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("关闭流出现异常");
                e.printStackTrace();
                return false;
            }
            return true;
        }

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
                System.out.println("找不到文件路径");
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
        InputStream input = null;
        List<String> strings = new ArrayList<>();
        public TextEditor(BufferedReader reader) {
            this.reader = reader;
        }
        public TextEditor(InputStream input) {
            this.input = input;
        }

        /**
         * @Description 用Scanner读出文件内容
         * @Author Lvv
         * @Date 2021/3/1 20:06
         * @Param []
         * @return java.util.List<java.lang.String>
         **/
        private void scanString() {
            Scanner scanner = new Scanner(input);
            String content;
            List<String> arr = new ArrayList<>();
            if (scanner == null) {
                System.out.println("Reader为空");
                return ;
            }
            while(scanner.hasNext()) {
                content = scanner.nextLine();
//                System.out.println(content + "|" + content.length());
                arr.add(content);
            }
            strings = arr;
        }

        /**
         * @Description 用BufferedReader
         * @Author Lvv
         * @Date 2021/3/1 20:07
         * @Param []
         * @return java.util.List<java.lang.String>
         **/
        private List<String> readString() {
            String content = null;
            List<String> arr = new ArrayList<>();
            if (reader == null) {
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
            } catch (IOException e) {
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
//            List<String> arr = readString();    //bufferedReader
//            List<String> arr = scanString();    //scanner
            scanString();
            for (int i = 0; i < strings.size(); i++) {
                for (int j = 0; j < strings.get(i).length(); j++) {
                    String str = strings.get(i);
                    char ch = str.charAt(j);
                    if (ch >= 0 && ch < 128)
                        sum++;
                }
            }
//            //测试输出统计的总数
//            System.out.println("ASCII sums:" + sum);
            return sum;
        }

        /**
         * @Description 判断是否是有效单词
         * @Author Lvv
         * @Date 2021/3/1 20:08
         * @Param [word]
         * @return boolean
         **/
        private boolean validate(String word) {
            boolean flag = true;
            final int MIN_LENGTH = 4;
            if (word.length() < MIN_LENGTH) {
//                System.out.println("单词长度小于" + MIN_LENGTH);
                return false;
            }
            String str = word.substring(0,4);
            Pattern p = Pattern.compile(".*\\d+.*");
            Matcher m = p.matcher(str);
            if (m.matches()){
                flag = false;   //若有数字则是无效单词
            }
//            System.out.println(str + "是否是有效单词" + flag);
            return flag;
        }

        /**
         * @Description 统计单词
         * @Author Lvv
         * @Date 2021/3/1 21:13
         * @Param []
         * @return int
         **/
        public int countWords() {
            String word;
            int sum = 0;
            for (int i = 0; i < strings.size(); i++) {
                String[] arr = strings.get(i).split("\\s+");
                for (String str : arr) {
                    if (validate(str))
                        sum++;
                }
            }
//            //测试用输出
//            System.out.println("sum:" + sum);
            return sum;
        }
//        public int countRows(String str) {
//            Scanner scanner = new Scanner(input);
//            while (scanner.hasNext()){
//
//            }
//        }
    }

}
