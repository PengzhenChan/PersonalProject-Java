import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static FileWriter fileWriter = null;
    private static Map<String, Integer> hashMap = null;

    public static int getLines(String filePath){
        int lines = 0;
        String fileString = readToString(filePath);
        String[] lineStrings = fileString.split("\\\\r\\\\n|\\\\n");
        for (String lineString:lineStrings){
            if (!lineString.trim().equals("")){
                lines++;
            }
        }
        return lines;
    }

    public static int getCharacters(String filePath){
        int characters = 0;
        String fileString = readToString(filePath);
        String[] lineStrings = fileString.split("\\\\r\\\\n|\\\\n");
        for (String s:lineStrings){
            characters += s.length();
        }
        int cnt1=(fileString.length()-fileString.replaceAll("\\\\n", "").length())/"\\n".length();
        int cnt2=(fileString.length()-fileString.replaceAll("\\\\r\\\\n", "").length())/"\\r\\n".length();
        characters = characters + cnt2 * 2 + (cnt1 - cnt2);
        return characters;
    }

    public static int getWords(String filePath){
        int words = 0;
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ ]");
        String fileString = readToString(filePath);
        String[] lineStrings = fileString.split("\\\\r\\\\n|\\\\n");
        for (String lineString:lineStrings){
            String[] wordStrings = pattern.split(lineString);
            for (String word:wordStrings){
                if (!word.equals("") && judgeWords(word.toLowerCase())){
                    words++;
                    System.out.println(word);
                }
            }
        }
        return words;
    }

    public static void countWordFrequency(String inputFilePath, String outputFilePath){
        hashMap = new HashMap<String,Integer>();
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ ]");
        String fileString = readToString(inputFilePath);
        String[] lineStrings = fileString.split("\\\\r\\\\n|\\\\n");
        for (String lineString:lineStrings){
            String[] wordStrings = pattern.split(lineString);
            for (String word:wordStrings){
                word = word.toLowerCase();
                if (!word.equals("") && judgeWords(word)){
                    Integer count = hashMap.get(word);
                    hashMap.put(word, (count == null ? 1 : ++count));
                }
            }
        }
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);
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

    public static void sortByValue(TreeMap<String,Integer> map, String filePath) {
        List<Map.Entry<String,Integer>> mappingList = null;
        mappingList = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String,Integer>>(){
            public int compare(Map.Entry<String,Integer> mapping1,Map.Entry<String,Integer> mapping2){
                return mapping2.getValue().compareTo(mapping1.getValue());
            }
        });
        int count = 0;
        for(Map.Entry<String,Integer> mapping:mappingList){
            if (++count > 10){
                break;
            }
            System.out.println(mapping.getKey()+":"+mapping.getValue());
            writeToFile(mapping.getKey() + ":" + mapping.getValue(), filePath);
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

    public static String readToString(String filePath) {
        String encoding = "UTF-8";
        File file = new File(filePath);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
