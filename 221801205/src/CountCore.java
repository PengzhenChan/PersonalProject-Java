
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
