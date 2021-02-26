import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.regex.Pattern;

public class CountWordRate {
    public static void countWordRate(String path) throws IOException {
        Map<String,Integer> dictionary = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str = null;

        while((str= br.readLine())!=null){
            String[] splitStr = str.split("[^0-9a-zA-Z]");
            for(int i=0;i<splitStr.length;i++){
                splitStr[i] = splitStr[i].toLowerCase();
                if(isThatWord(splitStr[i])){
                    if(dictionary.get(splitStr[i])==null){
                        dictionary.put(splitStr[i],1);
                    }else{
                        dictionary.put(splitStr[i],dictionary.get(splitStr[i])+1);
                    }
                }
            }
        }
        br.close();
        System.out.println(dictionary);
    }

    public static boolean isThatWord(String content){
        String pattern = "[a-zA-Z]{4}.*";
        boolean isMatch = Pattern.matches(pattern,content);
        return isMatch;
    }

}
