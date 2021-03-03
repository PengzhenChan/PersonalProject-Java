import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CountCore {
	/*
	 * ���ܣ�ͳ���ַ������ַ���
	 * �������ַ���
	 * ����ֵ���ַ���
	 */
    public int getCharCount(String words) {
    	return words.length();
    }
    
    /*
     * ���ܣ��ж�ֻ�������ֺ���ĸ���ַ����Ƿ�Ϊ����
     * �������ַ���
     * ����ֵ��������
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
     * ���ܣ��ж�x�Ƿ�Ϊ�ָ���
     */
    private boolean isDivision(char x) {
    	return !(Character.isUpperCase(x) || Character.isLowerCase(x) || Character.isDigit(x));
    }
    
    /*
     * ���ܣ����ַ���ת��Ϊֻ�����ֺ���ĸ�ַ���������
     * �������ַ���
     * ����ֵ��ArrayList<String>
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
     * ���ܣ�ͳ���ַ�������ַ����ĵ�������
     * �������ַ���
     * ����ֵ����������
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
     * ���ܣ��ж��ַ��Ƿ�Ϊ����ʾ�ַ�
     * �������ַ�
     * ����ֵ��boolean
     */
    private boolean canSee(char x) {
    	return (x < 127 && x > 32);
    }
    
    /*
     * ���ܣ�ͳ���ַ�������
     * �������ַ���
     * ����ֵ������
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
     * ���ܣ���ֻ�����ֺ���ĸ������ɸѡ�����ʲ�ת��ΪСд
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
     * ���ܣ�ͳ�Ƶ��ʳ��ִ���
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
     * ���ܣ��õ�Ƶ����ߵ�10������
     */
    public LinkedHashMap<String, Integer> getMaxWord(String s){
    	LinkedHashMap<String, Integer> countHashMap = getCountHashMap(s);
    	ArrayList<Map.Entry<String,Integer>> list = 
    	    new ArrayList<Map.Entry<String, Integer>>(countHashMap.entrySet());
    	//����
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
