import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitWord{
    public static long wordNum;

    /*
    * 由于用split方法将String分离耗时过久
    * 用matches匹配也很久
    * 就改为直接对String进行匹配
    * 返回所有合法单词的hashMap，并且value为出现次数
    * */
    public static HashMap<String, Integer> findLegal(String text){
        wordNum = 0;
        text = " "+text.toLowerCase();
        String regex = "([^a-z0-9])([a-z]{4}[a-z0-9]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        HashMap<String, Integer> legalWords = new HashMap<String, Integer>();

        //直接把单词和出现频率一起做了，放到hashMap里
        while(matcher.find()){
            wordNum++;
            String tmp = matcher.group(2).trim();
            if(!legalWords.containsKey(tmp)){
                legalWords.put(tmp,1);
            }
            else{
                legalWords.put(tmp, legalWords.get(tmp)+1);
            }
        }

        return legalWords;
    }

    public static long countWordNum(){
        return wordNum;
    }

    /*获取分隔开的字符串,以字符串数组的形式返回*/
    public static String[] splitWord(String text){
        String[] words;
        String regexForSplit = "[^ A-Za-z0-9_]|\\s+";

        words = text.split(regexForSplit);

        return words;
    }

    /*提取字符串中所有合法单词 以List形式返回(小写) 需传入已分隔好的字符串数组*/
    public static  List<String> splitLegalWord(String[] words){
        List<String> legalWords = new ArrayList<String>();
        String regexForMatch = "^[a-zA-Z]{4,}.*";
//        Pattern pattern = Pattern.compile(regexForMatch);
//        Matcher matcher = pattern.matcher();

        for(int i=0 ; i<words.length; i++){
            //matches效率极低
            if(words[i].matches(regexForMatch)){
                legalWords.add(words[i].toLowerCase());
            }
        }

        return legalWords;
    }

    /*集成上面两个方法，传入文本就可以得到合法单词的List*/
    public static List<String> textToLegalWord(String text){
        String[] words = splitWord(text);
        return splitLegalWord(words);
    }


}
