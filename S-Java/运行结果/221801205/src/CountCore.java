import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

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
    
    /*
     * 功能：判断字符是否为可显示字符
     * 参数：字符
     * 返回值：boolean
     */
    private boolean canSee(char x) {
    	return (x < 127 && x > 32);
    }
    
    /*
     * 功能：统计字符串行数
     * 参数：字符串
     * 返回值：行数
     */
    public int getRowCount(String s) {
    	boolean isRow = false;
    	int rowCount = 0;
    	char[] c = s.toCharArray();
    	for (int i = 0; i < s.length(); i ++) {
    		if (canSee(c[i])) {
    			isRow = true;
    		}
    		if (c[i] == '\n' && isRow == true) {
    			rowCount ++;
    			isRow = false;
    		}
    	}
    	if (isRow == true)
    		rowCount ++;
    	return rowCount;
    }
    
    /*
     * 功能：将只含数字和字母的数组筛选出单词并转换为小写
     */
    private ArrayList<String> getLowWords(String s){
    	ArrayList<String> ss = getWordList(s);
    	ArrayList<String> lowWords = new ArrayList<String>();
    	for (String word : ss) {
    		if (isWord(word)) {
    			lowWords.add(word.toLowerCase());
    		}
    	}
    	return lowWords;
    }
    
    /*
     * 功能：统计单词出现次数
     */
    private LinkedHashMap<String, Integer> getCountHashMap(String s){
    	ArrayList<String> lowWords = getLowWords(s);
    	LinkedHashMap<String, Integer> countHashMap = new LinkedHashMap<String, Integer>();
    	for (String lowWord : lowWords) {
    		if (countHashMap.containsKey(lowWord)) {
    			int count = countHashMap.get(lowWord);
    			countHashMap.put(lowWord, count + 1);
    		}
    		else {
    			countHashMap.put(lowWord, 1);
    		}
    	}
    	return countHashMap;
    }
    /*
     * 功能：得到频率最高的10个单词
     */
    public LinkedHashMap<String, Integer> getMaxWord(String s){
    	LinkedHashMap<String, Integer> countHashMap = getCountHashMap(s);
    	ArrayList<Map.Entry<String,Integer>> list = 
    	    new ArrayList<Map.Entry<String, Integer>>(countHashMap.entrySet());
    	//排序
    	Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() != o1.getValue() ? 
				    (o2.getValue() - o1.getValue()) : (o1.getKey()).toString().compareTo(o2.getKey());
			}
    		
		});
    	
    	LinkedHashMap<String, Integer> tmp = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < list.size() && i< 10; i++) {
            tmp.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return tmp;

    }
}
