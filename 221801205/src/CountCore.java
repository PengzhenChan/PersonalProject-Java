import java.util.ArrayList;

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
}
