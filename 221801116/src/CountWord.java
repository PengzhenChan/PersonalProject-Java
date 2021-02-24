import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class CountWord {
    public static void CountWord(String path) throws IOException {
        Map<String,Integer> dictionary = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str = null;

        while((str= br.readLine())!=null){
            String[] splitStr = str.split(" ");
            for(int i=0;i<splitStr.length;i++){
                if(dictionary.get(splitStr[i])==null){
                    dictionary.put(splitStr[i],1);
                }else{
                    dictionary.put(splitStr[i],dictionary.get(splitStr[i])+1);
                }
            }
        }
        br.close();
        System.out.println(dictionary);
    }
}
