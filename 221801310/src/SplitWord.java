public class SplitWord {

    /*获取分隔开的字符串,以字符串数组的形式返回*/
    public static String[] splitWord(String text){
        String[] words;
        String regexForSplit = "[^ A-Za-z0-9_]|\\s+";

        words = text.split(regexForSplit);

        return words;
    }
}
