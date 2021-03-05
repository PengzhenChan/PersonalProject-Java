import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lib {
    //统计字符数
    int charsNumber = 0;
    //统计单词数
    int wordNumber = 0;
    //统计有效行
    int lineNumber = 0;
    //统计单词及其频率
    HashMap<String, Integer> wordsCount;
    //记录文本内容
    String content = "";
    //输入文件路径
    String inputFilePath = "";
    //输出文件路径
    String outputFilePath = "";

    Lib(String inputPath, String outputPath) {
        inputFilePath = inputPath;
        outputFilePath = outputPath;
    }

    /**
     * 打开文件并统计字符数
     */
    void readFileByChars() {
        File file = new File(inputFilePath);
        if (!file.exists()) {
            System.out.print("输入文件不存在，程序关闭。。。");
            System.exit(0);
        }
        //存储文件内容
        StringBuilder fileContent = new StringBuilder();
        charsNumber = 0;
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            //存储读入字符
            int temp = -1;
            while ((temp = reader.read()) != -1) {
                fileContent.append((char) temp);
                //不考虑汉字
                if (temp < 128) {
                    charsNumber++;
                }
            }
            content = fileContent.toString();
            reader.close();
        } catch (Exception e) {
            System.out.print("e.getMessage()");
            System.out.print("发生错误，程序关闭。。。");
            System.exit(0);
        }
    }

    /**
     * 统计单词数量并排序
     */
    public void countWords() {
        //匹配单词
        String pattern = "(^|[^A-Za-z0-9])([a-zA-Z]{4}[a-zA-Z0-9]*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);

        wordNumber = 0;
        wordsCount = new HashMap<String, Integer>();
        //存储小写后的单词
        String temp;
        while (m.find()) {
            wordNumber++;
            //变为小写
            temp = m.group(2).toLowerCase();
            //若没有则添加，若有则加一
            wordsCount.merge(temp, 1, Integer::sum);
        }
        //先进行按值排序，再按键的大小排序
        wordsCount = wordsCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10).collect(Collectors.toMap(Map.Entry::getKey
                        , Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    /**
     * 统计有效行
     */
    public void getValueLines() {
        lineNumber = 0;
        //匹配有效行
        Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
        Matcher matcher = linePattern.matcher(content);
        while (matcher.find()) {
            lineNumber++;
        }
    }

    /**
     * 写入文件
     */
    public void writeFile() {
        //打开文件
        File file = new File(outputFilePath);
        if (!file.exists()) {
            System.out.print("输入文件不存在，程序关闭。。。");
            System.exit(0);
        }
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            //输出统计数据
            bw.write("characters: " + charsNumber + '\n'
            + "words: " + wordNumber + '\n'
            + "lines: " + lineNumber + '\n');
            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue() + '\n');
            }
            bw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
            System.out.print("发生错误，程序关闭。。。");
            System.exit(0);
        }
    }
}


