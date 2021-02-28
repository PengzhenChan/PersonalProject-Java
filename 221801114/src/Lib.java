import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static Map<String, Integer> hashMap = null;

    public static int getLines(String filePath){
        int lines = 0;
        try{
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null){
                if (currentLine.trim().length() != 0){
                    lines++;
                }
                currentLine = bufferedReader.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        }catch (IOException e){
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
                characters += currentLine.length() + 2;
                currentLine = bufferedReader.readLine();
            }
            characters -= 2;
        }catch (FileNotFoundException e){
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        }catch (IOException e){
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
            while (currentLine != null) {
                String[] wordStrings = pattern.split(currentLine);
                for (String str:wordStrings){
                    if (!str.equals("")&&judgeWords(str.toLowerCase())){
                        words++;
                        System.out.println(str.toLowerCase());
                    }
                }
                currentLine = bufferedReader.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            closeInputStream();
        }
        return words;
    }

    public static void getWordFrequency(String filePath){
        hashMap = new HashMap<String,Integer>();
        Pattern pattern = Pattern.compile("[`~!@#$%^&*()_+\\-={}\\\\|:;\"'<,>.?/ ]");
        try{
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] wordStrings = pattern.split(currentLine);
                for (String str:wordStrings){
                    if (!str.equals("")&&judgeWords(str.toLowerCase())){
                        Integer count = hashMap.get(str.toLowerCase());
                        hashMap.put(str.toLowerCase(),((count != null) ? ++count : 1));
                    }
                }
                currentLine = bufferedReader.readLine();
            }
            TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);
            sortByValue(treeMap);
        }catch (FileNotFoundException e){
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        }catch (IOException e){
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

    public static void sortByValue(TreeMap<String,Integer> map) {
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
}
