import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lib {
    int charsNumber = 0;
    int wordNumber = 0;
    int lineNumber = 0;
    HashMap<String, Integer> wordsCount;
    String content="";
    String inputFilePath="";
    String outputFilePath="";

    Lib(String inputPath, String outputPath)
    {
        inputFilePath = inputPath;
        outputFilePath = outputPath;
    }
    /**
     * 读取文件并统计字符数
     * @param filePath 文件路径
     * @return 文件内容
     */
    public void readFileByChars() {
        File file = new File(inputFilePath);
        //存储文件内容
        StringBuilder fileContent = new StringBuilder();
        charsNumber = 0;
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            int temp = -1;
            while ((temp = reader.read()) != -1) {
                fileContent.append((char) temp);
                //不考虑汉字
                if(temp < 128) {
                    charsNumber++;
                }
            }
            content = fileContent.toString();
            reader.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * 统计文本中的单词
     * @param str 待处理文本
     * @return 单词的哈希表
     */
    public void countWords()
    {
        String pattern = "(^|[^A-Za-z0-9])([a-zA-Z]{4}[a-zA-Z0-9]*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        wordNumber = 0;
        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        wordsCount = new HashMap<String, Integer>();
        String temp;
        while (m.find( )) {
            wordNumber++;
            temp = m.group(2).toString().toLowerCase();
            wordsCount.merge(temp, 1, Integer::sum);
        }
        wordsCount = wordsCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey
                        , Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public void getValueLines()
    {
        lineNumber = 0;
        Pattern linePattern = Pattern.compile("\\S+");
        Matcher matcher = linePattern.matcher(content);
        while (matcher.find()) {
            lineNumber++;
        }
    }

    public void writeFile()
    {
        try {
            File file = new File(outputFilePath);

            if (!file.exists()) {
                System.out.println("Error!");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("characters: " + charsNumber + '\n');
            bw.write("words: " + wordNumber + '\n');
            bw.write("lines: " + lineNumber + '\n');
            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()){

                bw.write(entry.getKey()+": "+entry.getValue()+'\n');
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFileTest(String filePath, String testStr)
    {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("Error!");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(testStr);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


