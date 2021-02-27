import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class WordCount{
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static Map<String, Integer> hashMap = null;

    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("命令行参数错误，需要两个文件名！");
            System.exit(0);
        }
        System.out.println("characters:"+getCharacters(args[0]));
        System.out.println("lines:"+getLines(args[0]));
        System.out.println("words:"+getWords(args[0]));
        getWordFrequency(args[0]);
    }

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
                    if (!str.equals("")){
                        words++;
                        System.out.println(str);
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
                    if (!str.equals("")){
                        Integer count = hashMap.get(str);
                        hashMap.put(str,((count != null) ? ++count : 1));
                    }
                }
                currentLine = bufferedReader.readLine();
            }
            int count = 0;
            TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);
            Set<String> keys = treeMap.keySet();
            for (String s : keys) {
                if (++count > 10){
                    break;
                }
                System.out.println(s + ":" + treeMap.get(s));
            }
        }catch (FileNotFoundException e){
            System.out.println("未找到文件：" + filePath);
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            closeInputStream();
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
