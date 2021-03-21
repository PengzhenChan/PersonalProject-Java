import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {

    private String inputFile;
    private String outputFile;
    private int charNumber;                   //字符计数
    private int wordNumber;                   //单词计数
    private int lineNumber;                   //行数计数
    private Map<String,Integer> wordsMap;     //存取单词名及其个数
    List<Map.Entry<String,Integer>> list;

    public Lib() {
    }

    public Lib(String inputFile, String outputFile) {
        charNumber = 0;
        wordNumber = 0;
        lineNumber = 0;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        wordsMap = new HashMap<String,Integer>();
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public int getCharNumber() {
        return charNumber;
    }

    public void setCharNumber(int charNumber) {
        this.charNumber = charNumber;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Map<String, Integer> getWordsMap() {
        return wordsMap;
    }

    public void setWordsMap(Map<String, Integer> wordsMap) {
        this.wordsMap = wordsMap;
    }

    //读取文件数据
    public String readFile() {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            int ch;
            while ((ch = reader.read()) != -1){
                builder.append((char)ch);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("文件打开失败！");
            e.printStackTrace();
        }
        return builder.toString();
    }

    //计算字符数
    public void charNumCount(){
        String str = readFile();
        charNumber = str.length();
    }

    //计算单词数，并存入Map
    public void wordNumCount(){
        String str = readFile();

        String[] words = str.split("[^a-zA-Z0-9]");
        Pattern wordPattern = Pattern.compile("([a-zA-Z]{4}[a-zA-Z0-9]*)");
        for (String word:words){
            Matcher matcher = wordPattern.matcher(word);
            if(matcher.matches()){
                wordNumber++;
                String w = word.toLowerCase();
                Integer count = wordsMap.get(w);
                if(count == null){
                    count = 0;
                }
                wordsMap.put(w,count+1);
            }
        }

        list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        
        //对Map的里的单词进行排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() == o2.getValue()){
                    return o1.getKey().compareTo(o2.getKey());
                }
                    return o2.getValue().compareTo(o1.getValue());
            }
        });
    }

    //计算行数
    public void lineNumCount(){
        String str = readFile();
        String[] lines = str.split("\n");
        lineNumber = lines.length;
        Pattern linePattern = Pattern.compile("\\s*");
        for(String line : lines){
            Matcher matcher = linePattern.matcher(line);
            if (matcher.matches()){
                lineNumber--;
            }
        }
    }

    //输出数据到文件中
    public void writeFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            charNumCount();
            wordNumCount();
            lineNumCount();
            writer.write("characters: "+charNumber+"\n");
            writer.write("words: "+wordNumber+"\n");
            writer.write("lines: "+lineNumber+"\n");
            int i = 0;
            for (Map.Entry<String, Integer> entry : list) {
                if(i >= 10){
                    break;
                }
                String s = entry.getKey();
                Integer integer = entry.getValue();
                writer.write(s + ": " + integer + "\n");
                i++;
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("文件打开失败！");
            e.printStackTrace();
        }
        System.out.println("输出完毕！");
    }
}
