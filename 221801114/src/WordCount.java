import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordCount{
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static Map<String, Integer> hashMap = new HashMap<String, Integer>();

    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("命令行参数错误，需要两个文件名！");
            System.exit(0);
        }
        System.out.println("characters:"+getCharacters(args[0]));
        System.out.println("lines:"+getLines(args[0]));
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
