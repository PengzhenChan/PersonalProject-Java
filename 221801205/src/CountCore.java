import java.util.ArrayList;

public class CountCore {
	/*
	 * 功能：统计字符串的字符数
	 * 参数：字符串
	 * 返回值：字符数
	 */
    public int getCharCount(String words) {
    	return words.length();
    }
    
    /*
     * 功能：判断只包含数字和字母的字符串是否为单词
     * 参数：字符串
     * 返回值：单词数
     */
    private boolean isWord(String s) {
    	if (s.length() < 4)
    		return false;
    	char[] c = s.toCharArray();
    	for (int i = 0; i < 4; i ++) {
    		if (!(Character.isUpperCase(c[i]) || Character.isLowerCase(c[i]))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /*
     * 功能：判断x是否为分隔符
     */
    private boolean isDivision(char x) {
    	return !(Character.isUpperCase(x) || Character.isLowerCase(x) || Character.isDigit(x));
    }
    
    /*
     * 功能：将字符串转换为只含数字和字母字符串的数组
     * 参数：字符串
     * 返回值：ArrayList<String>
     */
    private ArrayList<String> getWordList(String s){
    	ArrayList<String> wordList = new ArrayList<String>();
    	char[] c = s.toCharArray();
    	String word = "";
    	boolean canAdd = false;
    	for (char i : c) {
    		if (!isDivision(i)) {
    			word += i;
    			canAdd = true;
    		}
    		else {
    			if (canAdd == true) {
    				wordList.add(word);
    				word = "";
    				canAdd = false;
    			}		
    		}
    	}
    	if (canAdd == true) 
    		wordList.add(word);
    	return wordList;
    }
    
    /*
     * 功能：统计字符数组的字符串的单词总数
     * 参数：字符串
     * 返回值：单词总数
     */
    public int getWordCount(String s) {
    	int cnt = 0;
    	ArrayList<String> wordList = getWordList(s);
    	for (String word : wordList) {
    		if (isWord(word)) {
    			cnt ++;
    		}
    	}
    	return cnt;
    }
}
