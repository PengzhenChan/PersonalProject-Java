import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import sun.nio.cs.ext.ISCII91;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {

    //文件io工具类
    public static class FileIOUtil {
        static InputStream input = null;
        static OutputStream output = null;

        /**
         * @Description 将msg写入targetPath的文件中
         * @Author Lvv
         * @Date 2021/3/2 15:36
         * @Param [targetPath, msg]
         * @return void
         **/
        public static void writeFile(String targetPath, String msg) {
            File file = new File(targetPath);
            try {
                output = new FileOutputStream(file);
                byte[] bytes = msg.getBytes();
                output.write(bytes,0,bytes.length);
                output.flush();
            } catch (IOException e) {
                System.out.println("写出到文件时发生异常");
                e.printStackTrace();
            } finally {
                if (output != null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        System.out.println("关闭文件输出流失败");
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * @Description 通过文件路径读入文件，返回BufferedReader的流
         * @Author Lvv
         * @Date 2021/3/4 8:11
         * @Param [filePath]
         * @return BufferedReader
         **/
        public static BufferedReader readFile(String filePath) {
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
            }
            return bufferedReader;
        }

    }

    //String处理类
    public static class TextEditor {
        BufferedReader reader = null;
        int lines = 0;
        List<String> strings = new ArrayList<>();
        static final int TOP_NUM = 10;
        HashMap<String,Integer> words = new HashMap<String, Integer>();
        List<Map.Entry<String,Integer>> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();


        public TextEditor(BufferedReader reader) {
            this.reader = reader;
        }


        /**
         * @Description 用BufferedReader的read方法一个字符一个字符地读，能够读到换行符
         * @Author Lvv
         * @Date 2021/3/4 8:06
         * @Param []
         * @return void
         **/
        public void readString() {
            stringBuilder = new StringBuilder();
            int value;
            try {
                while ((value = reader.read()) != -1) {
                    char ch = (char)value;
                    stringBuilder.append(ch);
                    if (ch == '\n') {
                        strings.add(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                }
                if (stringBuilder != null) {
                    strings.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            for (int i = 0; i < strings.size(); i++) {
                sum += strings.get(i).length();
//                for (int j = 0; j < strings.get(i).length(); j++) {
//                    String str = strings.get(i);
//                    char ch = str.charAt(j);
//                    if (ch >= 0 && ch < 128)
//                        sum++;
//                }
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
        public static boolean validate(String word) {
            boolean flag = true;
            final int MIN_LENGTH = 4;
            if (word.length() < MIN_LENGTH) {
//                System.out.println("单词长度小于" + MIN_LENGTH);
                return false;
            }
            String str = word.substring(0,4);
            Pattern p = Pattern.compile(".*\\d+.*");
            Matcher m = p.matcher(str);
            if (m.matches()) {
                flag = false;   //若有数字则是无效单词
            }
//            System.out.println(str + "是否是有效单词" + flag);
            return flag;
        }

        /**
         * @Description 统计有效单词总数
         * @Author Lvv
         * @Date 2021/3/1 21:13
         * @Param []
         * @return int
         **/
        public int countWords() {

            String word;
            int linesSum = 0;
            int wordsSum = 0;
            for (int i = 0; i < strings.size(); i++) {
//              \w :匹配包括下划线的任何单词字符,等价于 [A-Z a-z 0-9_]
//              \W :匹配任何非单词字符,等价于 [^A-Z a-z 0-9_]
                String str = strings.get(i);
                String[] arr = str.split("\\W");
                for (String s : arr) {
                    if (validate(s)) {
                        wordsSum++;
                    }
                }
                //string.replace用string.trim替换提升效率20%
                if (!str.trim().isEmpty())
                    linesSum++;
            }
//            //测试用输出
//            System.out.println("linesSum:" + linesSum);
            lines = linesSum;
            return wordsSum;
        }

        /**
         * @Description 统计词频并返回Top10的单词
         * @Author Lvv
         * @Date 2021/3/2 15:43
         * @Param []
         * @return String Top10单词的一个排名String
         **/
        public String countTopWords() {
            String word;
            int sum = 0;
            for (int i = 0; i < strings.size(); i++) {
//              \w :匹配包括下划线的任何单词字符,等价于 [A-Z a-z 0-9_]
//              \W :匹配任何非单词字符,等价于 [^A-Z a-z 0-9_]
                String[] arr = strings.get(i).split("\\W");
                for (String str : arr) {
                    word = str.toLowerCase();   //转小写
                    if (validate(word)) {
                        if (!words.containsKey(word)) {
                            words.put(word, 1);
                        } else {
                            int times = words.get(word) + 1;
                            words.remove(word);
                            words.put(word, times);
                        }
                    }
                }
            }
            for (Map.Entry<String,Integer> entry : words.entrySet()) {
                list.add(entry);
            }
            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if (o2.getValue().compareTo(o1.getValue()) == 0) {
                        return o2.getKey().compareTo(o1.getKey()) * -1;
                    } else  {
                        return o2.getValue() - o1.getValue();
                    }
                }
            });
            //输出TOP10字符串的过程
            stringBuilder.delete(0, stringBuilder.length());
            for (int i = 0; i < Math.min(TOP_NUM, list.size()); i++) {
                stringBuilder.append(list.get(i).getKey()).append(": ").append(list.get(i).getValue()).append('\n');
            }
            return stringBuilder.toString();
        }

        /**
         * @Description 统计文件中非空行数
         * @Author Lvv
         * @Date 2021/3/1 21:49
         * @Param []
         * @return int
         **/
        public int countLines() {
            return this.lines;
        }
    }

}
