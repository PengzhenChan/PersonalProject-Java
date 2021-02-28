import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class CountWordRate {
    public static String[] countWordRate(String path){
        Map<String,Integer> dictionary = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            System.out.println("错误位于countWordRate方法,原因可能是未找到目标文件\n请重新确认文件位置\n" + "当前文件位置" + path);
        }
        String str = null;

        while(true){
            try {
                if (!((str= br.readLine())!=null)) break;
            } catch (IOException e) {
                System.out.println("错误位于countWordRate方法,原因可能是文件读出现问题");
            }
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
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("错误位于countWordRate法,原因可能是文件流未能正确的关闭");
        }

        SortedMap<String,Integer> sortedMap = new TreeMap<String, Integer>();
        sortedMap.putAll(dictionary);
        List<Map.Entry<String,Integer>> mapList = new ArrayList<>(sortedMap.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });

        String[] mostWord = new String[10];
        for(int i = 0;i < 10;i++){
            if(i>mapList.size()-1)
                break;
            mostWord[i] = mapList.get(i).toString().replace('=',':')+"\n";
        }

        return mostWord;

    }

    public static boolean isThatWord(String content){
        String pattern = "[a-z]{4}.*";
        boolean isMatch = Pattern.matches(pattern,content);
        return isMatch;
    }

}
