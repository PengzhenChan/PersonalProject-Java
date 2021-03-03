import java.util.ArrayList;
import java.util.List;

public class SplitWord {

    /*获取分隔开的字符串,以字符串数组的形式返回*/
    public static String[] splitWord(String text){
        String[] words;
        String regexForSplit = "[^ A-Za-z0-9_]|\\s+";

        words = text.split(regexForSplit);

        return words;
    }

    /*提取字符串中所有合法单词 以List形式返回 需传入已分隔好的字符串数组*/
    public static  List<String> splitLegalWord(String[] words){
        List<String> legalWords = new ArrayList<String>();
        String regexForMatch = "^[a-zA-Z]{4,}.*";

        for(int i=0 ; i<words.length; i++){
            if(words[i].matches(regexForMatch)){
                legalWords.add(words[i]);
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
