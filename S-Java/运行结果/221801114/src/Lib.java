import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private static FileWriter fileWriter = null;
    private static Map<String, Long> hashMap = null;

    public static long getLines(String filePath){
        long lines = 0;
        String fileString = readToString(filePath);
        String[] lineStrings = fileString.split("\n|\r\n");
        for (String lineString : lineStrings){
            if (!lineString.trim().equals("")){
                lines ++;
            }
        }
        return lines;
    }

    public static long getCharacters(String filePath){
        String fileString = readToString(filePath);
        return fileString.length();
    }

    public static long getWords(String filePath){
        long words = 0;
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ \t\\[\\]]");
        String fileString = readToString(filePath);
        String[] lineStrings = fileString.split("\n|\r\n");
        for (String lineString : lineStrings){
            String[] wordStrings = pattern.split(lineString);
            for (String word : wordStrings){
                if (!word.equals("") && judgeWords(word.toLowerCase())){
                    words ++;
                }
            }
        }
        return words;
    }

    public static void countWordFrequency(String inputFilePath, String outputFilePath){
        hashMap = new HashMap<String,Long>();
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ \t\\[\\]]");
        String fileString = readToString(inputFilePath);
        String[] lineStrings = fileString.split("\n|\r\n");
        for (String lineString : lineStrings){
            String[] wordStrings = pattern.split(lineString);
            for (String word : wordStrings){
                word = word.toLowerCase();
                if (!word.equals("") && judgeWords(word)){
                    Long count = hashMap.get(word);
                    hashMap.put(word, (count == null ? 1 : ++ count));
                }
            }
        }
        TreeMap<String, Long> treeMap = new TreeMap<String, Long>(hashMap);
        sortByValue(treeMap, outputFilePath);
    }

    private static boolean judgeWords(String word){
        if (word.length() < 4){
            return false;
        }
        Pattern pattern = Pattern.compile("^[a-z]{4}");
        Matcher matcher = pattern.matcher(word);
        if (!matcher.find()){
            return false;
        }
        return true;
    }

    public static void sortByValue(TreeMap<String, Long> map, String filePath) {
        List<Map.Entry<String,Long>> mappingList = null;
        mappingList = new ArrayList<Map.Entry<String,Long>>(map.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String,Long>>(){
            public int compare(Map.Entry<String,Long> mapping1,Map.Entry<String,Long> mapping2){
                return mapping2.getValue().compareTo(mapping1.getValue());
            }
        });
        int count = 0;
        for(Map.Entry<String,Long> mapping:mappingList){
            if (++ count > 10){
                break;
            }
            writeToFile(mapping.getKey() + ": " + mapping.getValue(), filePath);
        }
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            System.err.println("未找到文件：" + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static void writeToFile(String content, String filePath){
        try{
            if (fileWriter == null) {
                fileWriter = new FileWriter(filePath ,false);
            }else {
                fileWriter = new FileWriter(filePath, true);
            }
            fileWriter.write(content + "\n");
        }catch (FileNotFoundException e){
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeOutputStream();
        }
    }

    private static void closeOutputStream(){
        try{
            if (fileWriter != null){
                fileWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
