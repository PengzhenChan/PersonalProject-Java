import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private static BufferedReader bufferedReader = null;
    private static FileWriter fileWriter = null;
    private static Map<String, Integer> hashMap = null;

    public static int getLines(String filePath){
        int lines = 0;
        try{
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null){
                String[] lineStrings = currentLine.split("\\\\r\\\\n|\\\\n");
                for (String lineString:lineStrings){
                    if (!lineString.trim().equals("")){
                        lines++;
                    }
                }
                currentLine = bufferedReader.readLine();
            }
        }catch (FileNotFoundException e) {
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeInputStream();
        }
        return lines;
    }

    public static int getCharacters(String filePath){
        int characters = 0;
        try{
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null){
                String[] lineStrings = currentLine.split("\\\\r\\\\n|\\\\n");
                for (String s:lineStrings){
                    characters += s.length();
                }
                int cnt1=(currentLine.length()-currentLine.replaceAll("\\\\n", "").length())/"\\n".length();
                int cnt2=(currentLine.length()-currentLine.replaceAll("\\\\r\\\\n", "").length())/"\\r\\n".length();
                characters = characters + cnt2 * 2 + (cnt1 - cnt2);
                currentLine = bufferedReader.readLine();
            }
        }catch (FileNotFoundException e) {
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeInputStream();
        }
        return characters;
    }

    public static int getWords(String filePath){
        int words = 0;
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ ]");
        try{
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null){
                String[] lineStrings = currentLine.split("\\\\r\\\\n|\\\\n");
                for (String lineString:lineStrings){
                    String[] wordStrings = pattern.split(lineString);
                    for (String word:wordStrings){
                        if (!word.equals("") && judgeWords(word.toLowerCase())){
                            words++;
                        }
                    }
                }
                currentLine = bufferedReader.readLine();
            }
        }catch (FileNotFoundException e) {
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeInputStream();
        }
        return words;
    }

    public static void countWordFrequency(String inputFilePath, String outputFilePath){
        hashMap = new HashMap<String,Integer>();
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ ]");
        try{
            bufferedReader = new BufferedReader(new FileReader(inputFilePath));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null){
                String[] lineStrings = currentLine.split("\\\\r\\\\n|\\\\n");
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
                currentLine = bufferedReader.readLine();
            }
            TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);
            sortByValue(treeMap, outputFilePath);
        }catch (FileNotFoundException e) {
            System.out.println("未找到文件：" + inputFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeInputStream();
        }
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

    private static void closeInputStream(){
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
        }catch (IOException e){
            e.printStackTrace();
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
