import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    private static String DIR = System.getProperty("user.dir");
    //空白行会成为两个\s*的一部分
    private static String LINE_REGEX = "\\s*\\S+\\s*\n";
    private static String WORD_REGEX = "";

    private long charNum = 0;
    private long lineNum = 0;
    private long wordNum = 0;

    public static String readFromFile(String filename){
        BufferedReader reader = null;
        StringBuilder stringBuilder = null;
        int ch = 0;

        try{
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(DIR + "\\" + filename), StandardCharsets.UTF_8));
            stringBuilder = new StringBuilder();

            //利用StringBuilder拼接提速
            while ((ch = reader.read()) != -1){
                stringBuilder.append((char)ch);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("文件不存在！");
            e.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public long charsCount(String str){
        long count = 0;

        //题目中说明了没给汉字,所以其实直接用str.length()也行
        char[] ch = str.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(ch[i] <= 127){
                count++;
            }
        }

        return count;
    }

    public long linesCount(String str){
        long count = 0;

        Matcher matcher = Pattern.compile(LINE_REGEX).matcher(str);
        while(matcher.find()){
            count++;
        }
        return count;
    }

    public static void main(String[] args){
        WordCount wordCount = new WordCount();
        String str = readFromFile("input.txt");
        //System.out.println(wordCount.charsCount(str));
        //System.out.println(wordCount.linesCount(str));
    }
}
