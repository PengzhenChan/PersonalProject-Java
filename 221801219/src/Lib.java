import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Lib {
    //统计字符数
    private int charsNumber;
    //统计单词数
    private int wordNumber;
    //统计有效行
    private int lineNumber;
    //统计单词及其频率
    private HashMap<String, Integer> wordsCount;
    //输入文件路径
    String inputFilePath;
    //输出文件路径
    String outputFilePath;

    Lib(String inputPath, String outputPath) {
        inputFilePath = inputPath;
        outputFilePath = outputPath;
    }

    public int getCharsNumber() {
        return charsNumber;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public HashMap<String, Integer> getWordsCount() {
        return wordsCount;
    }

    /**
     * 打开文件并统计字符数，单词数，有效行数，并保存单词及其频率
     */
    void readFileByChars() {
        //初始化
        charsNumber = 0;
        wordNumber = 0;
        lineNumber = 0;
        wordsCount = new HashMap<>();
        //暂时存放单词
        StringBuilder tempWord;
        //小写后的单词
        String word;
        //读取文件
        FileReader fr = null;
        //判断有效行
        boolean isValued;
        try {
            //初始化
            tempWord = new StringBuilder();
            isValued = false;

            fr = new FileReader(inputFilePath);
            int ch;
            while ((ch = fr.read()) != -1) {
                charsNumber++;
                //单词判断
                if (isDigitOrLetterFunc(ch)) {
                    if (!isDigitFunc(ch)) {
                        tempWord.append((char) ch);
                    } else {
                        if (tempWord.length() < 4) {
                            tempWord = new StringBuilder();
                        } else {
                            tempWord.append((char) ch);
                        }
                    }
                } else {
                    if (tempWord.length() > 3) {
                        //若没有则添加，若有则加一
                        word = tempWord.toString().toLowerCase();
                        wordsCount.merge(word, 1, Integer::sum);
                        wordNumber++;
                    }
                    tempWord = new StringBuilder();
                }
                //有效行判断
                if (ch == 10) {
                    if (isValued) {
                        lineNumber++;
                    }
                    isValued = false;
                } else {
                    if (!isValued && !isBlankCharFuc(ch)) {
                        isValued = true;
                    }
                }
            }
            //文件末尾处理
            if (tempWord.length() > 3) {
                word = tempWord.toString().toLowerCase();
                wordsCount.merge(word, 1, Integer::sum);
                wordNumber++;
            }
            if (isValued) {
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    /**
     * 排序单词
     */
    public void countWords() {
        //先进行按值排序，再按键的大小排序
        wordsCount = getWordsCount()
                .entrySet()
                .stream()
                .sorted(Map.Entry
                        .<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors
                        .toMap(Map.Entry::getKey
                                , Map.Entry::getValue
                                , (e1, e2) -> e2
                                , LinkedHashMap::new));
    }

    /**
     * 写入文件
     */
    public void writeFile() {
        //构建输出字符串
        StringBuilder outputString = new StringBuilder();
        outputString.append("characters: ")
                .append(getCharsNumber())
                .append('\n')
                .append("words: ")
                .append(getWordNumber())
                .append('\n')
                .append("lines: ")
                .append(getLineNumber())
                .append('\n');

        //这种方法遍历hashmap速度最快
        for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
            outputString.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append('\n');
        }

        FileOutputStream fos;
        BufferedOutputStream bos = null;
        //用BufferedOutputStream输出到文件
        try {
            fos = new FileOutputStream(outputFilePath);
            bos = new BufferedOutputStream(fos);
            //设置编码为utf-8
            byte[] bytes = outputString.toString().getBytes(StandardCharsets.UTF_8);
            bos.write(bytes, 0, bytes.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            try {
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static boolean isDigitOrLetterFunc(int x) {
        return (x > 47 && x < 58) || (x > 64 && x < 91) || (x > 96 && x < 123);
    }

    public static boolean isDigitFunc(int x) {
        return (x > 47 && x < 58);
    }

    public static boolean isBlankCharFuc(int x) {
        return (x == 32 || x == 9 || x == 13);
    }
}