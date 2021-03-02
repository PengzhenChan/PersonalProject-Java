import sun.nio.cs.ext.ISCII91;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    //文件io工具类
    public static class FileIOUtil {
        InputStream input = null;
        OutputStream output = null;
        /**
         * @Description 创建一个打开文件的InputStream
         * @Author Lvv
         * @Date 2021/3/1 20:05
         * @Param [filePath]
         * @return java.io.InputStream
         **/
        public InputStream openReadStream(String filePath) {
            File file = new File(filePath);
            try {
                input = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.out.println("文件未找到");
                e.printStackTrace();
            }
            return input;
        }

        public void writeFile(String targetPath, String msg) {
            File file = new File(targetPath);
            try {
                output = new FileOutputStream(file);
                byte[] bytes = msg.getBytes();
                output.write(bytes,0,bytes.length);
                output.flush();
            } catch (FileNotFoundException e) {
                System.out.println("文件未找到");
                e.printStackTrace();
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
         * @Description 关闭流
         * @Author Lvv
         * @Date 2021/3/1 20:06
         * @Param []
         * @return boolean
         **/
        public boolean closeReadStream() {
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
        int addCharNum = 0;
        static final int TOP_NUM = 10;
        HashMap<String,Integer> words = new HashMap<String, Integer>();
        List<Map.Entry<String,Integer>> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();


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
        public void scanString() {
            Scanner scanner = new Scanner(input);
            String content;
            char c;
            List<String> arr = new ArrayList<>();
            if (scanner == null) {
                System.out.println("Reader为空");
                return ;
            }
            while(scanner.hasNext()) {
//                content = scanner.nextLine();
                content = scanner.next();
                System.out.println(content + "|" + content.length());
                arr.add(content);
            }
            strings = arr;
        }

        /**
         * @Description 用BufferedReader的read方法一个字符一个字符地读，能够读到换行符
         * @Author Lvv
         * @Date 2021/3/2 15:06
         * @Param []
         * @return java.util.List<java.lang.String>
         **/
        public void readString() {
            String content = "";
            int value;
            List<String> arr = new ArrayList<>();
            if (reader == null) {
                System.out.println("Reader为空");
                return ;
            }
            try {
                while ((value = reader.read()) != -1) {
                    //测试输出读取内容
//                    content += '\n';
                    String c = String.valueOf((char)value);
                    content += c;
                    if ('\n' == c.charAt(0)) {
                        strings.add(content);
                        System.out.println(content + "|" + content.length());
                        content = "";
                    }
                }
                if (!content.equals(null)) {
                    strings.add(content);
                    content = "";
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
//            List<String> arr = readString();    //bufferedReader
//            List<String> arr = scanString();    //scanner
//            scanString();
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
         * @Description 统计有效单词总数
         * @Author Lvv
         * @Date 2021/3/1 21:13
         * @Param []
         * @return int
         **/
        public int countWords() {
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
                            words.put(word,0);
                        } else {
                            continue;
//                            int times = words.get(word) + 1;
//                            words.remove(word);
//                            words.put(word,times);
                        }
                    }
                }
            }
//            //测试用输出
//            System.out.println("sum:" + sum);
            return words.size();
        }

        /**
         * @Description 统计词频
         * @Author Lvv
         * @Date 2021/3/1 23:43
         * @Param []
         * @return void
         **/
        public void countTopWords() {
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
                    if (o2.getValue() == o1.getValue()) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    return o2.getValue() - o1.getValue();
                }
            });
        }

        /**
         * @Description 返回top的词频
         * @Author Lvv
         * @Date 2021/3/1 23:43
         * @Param []
         * @return String
         **/
        public String getTops() {
            for (int i = 0; i < Math.min(TOP_NUM, list.size()); i++) {
                stringBuilder.append("word" + i +": ").append(list.get(i).getKey()).append('\n');
//                        .append("\t\tfrequency: ").append(list.get(i).getValue()).append('\n');   //测试用
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
        public int countRows() {
            int sum = 0;
            for (int i = 0; i < strings.size(); i++) {
                if (!strings.get(i).isEmpty())
                    sum++;
            }
//            System.out.println("行数: " + sum);
            return sum;
        }
    }

}
