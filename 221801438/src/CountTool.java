/* 用于字符统计 */
public class CountTool {

    /* 统计总字符数 */
    public int characterCount(StringBuffer str) {
        //总字符数
        int sum = 0;
        String result = str.toString();
        char[] charArrary = result.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            sum++;
        }
        return sum;
    }
}
