import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCountMethods {
    //У�����ĵ�������ʽ
    private static String REGEX = "[\\u4e00-\\u9fa5]";
    
    /**
     * ���˵�����
     * @param str ���������ĵ��ַ���
     * @return ���˵����ĺ��ַ���
     */
    public static String filterChinese(String str) {
        String result = str;
        //�ж��ַ������Ƿ��������
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(str);
        
        if (m.find()) {
            StringBuffer sb = new StringBuffer();
            // ����У���Ƿ�Ϊ����
            boolean flag = false;
            char chinese = 0;
            char[] charArray = str.toCharArray();
            // �������ļ������ַ�
            for (int i = 0; i < charArray.length; i++) {
                chinese = charArray[i];
                flag = isChineseChar(chinese);
                if (!flag) {
                    sb.append(chinese);
                }
            }
            result = sb.toString();
        }
        return result;
    }
    
    /**
     * У��һ���ַ��Ƿ��Ǻ���
     * @param c ��У����ַ�
     * @return true�����Ǻ���
     */
    public static boolean isChineseChar(char c) {
        try {
            return String.valueOf(c).getBytes("UTF-8").length > 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * ͳ���ļ����ַ���
     * @param str ��У����ַ���
     * @return int �ļ����ַ���
     */
    public static int countChars(String str){
        int count = 0;
        char[] achar = str.toCharArray();
        
        for (int i = 0;i<achar.length;i++) {
            //ͳ��ascii code
            if (33 <= achar[i] && achar[i] <= 126) {
                count++;
            }
            //ͳ�ƿո�ˮƽ�Ʊ�������з�
            if(achar[i] == 32 || achar[i] == 9 || achar[i] == 10) {
                count++;
            }
        }
        return count;
    }


    

}
