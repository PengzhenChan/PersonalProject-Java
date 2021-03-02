
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
    public boolean isWord(String s) {
    	char[] c = s.toCharArray();
    	for (int i = 0 ; i < 4 ; i ++) {
    		if (!(Character.isUpperCase(c[i]) || Character.isLowerCase(c[i]))) {
    			return false;
    		}
    	}
    	return true;
    }
}
