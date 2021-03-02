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

    /* 统计单词数 */
    public int wordsCount(StringBuffer str) {
        //转化小写且以'.'分割的字符串
        String changedStr = this.changeStr(str);
        //分割字符串
        String[] wordStr = changedStr.split(" ");
        //计数
        int sum= 0;
        for (int i = 0; i < wordStr.length; i++) {
            if (wordStr[i].matches("[a-z]{4}[a-z0-9]*")) {
                sum++;
            }
        }
        return sum;
    }

    /* 转化成有效字符串 */
    public String changeStr(StringBuffer str) {
        String result= str.toString().toLowerCase();
        //利用正则表达式筛选出不是数字和字母的符号，转化为'.'
        String regex = "[^a-z0-9]";
        result = result.replaceAll(regex," ");
        return result;
    }
}
