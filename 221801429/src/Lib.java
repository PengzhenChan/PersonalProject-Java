import sun.nio.cs.ext.MacHebrew;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/*
    作者：陈耀
    功能：文本内容转换为字符串、统计字符串文本字符数、统计字符串文本单词数量、统计字符串文本有效行数、
          统计字符串前10单词频数。
*/
public class Lib {

    /*
      txt文件的内容转为String类型返回
      参数：String 文件的的相对路径
      返回类型：String
      异常处理：IO异常
     */
    public static String txtToString(String filePath) throws IOException {
        System.out.println("读取文件！");
        File file = new File(filePath);
        String context = "";
        try{
            FileInputStream inputStream = new FileInputStream(file);
            int lenth = inputStream.available();
            byte[] buffer = new byte[lenth];
            inputStream.read(buffer);
            inputStream.close();
            context = new String(buffer, "UTF-8");
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("读取文件失败！");
            return null;
        }
            System.out.println(context);
            return context;
    }

    /*
      功能：返回String文本字符数
      参数：String
      返回类型：int
     */
    public static int countChar(String context){
        return context.length();
    }

    /*
      统计文本非空函数
     */
    public static int countLine(String context){
        int count = 0;
        Pattern nonblankPattern = Pattern.compile("(\n|^)\\s*\\S+");
        Matcher matcher = nonblankPattern.matcher(context);
        while (matcher.find()){
            count++;
        }
        return count;
    }

    /*
      功能: 返回字符串有效单词个数
      参数: String
      返回类型: int
      备注: 单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
      英文字母： A-Z，a-z
      字母数字符号：A-Z， a-z，0-9
      分割符：空格，非字母数字符号
     */
    public static int countWord(String context){
        int count = 0;
        Pattern wordPattern = Pattern.compile("([^A-Za-z0-9]+|^)([a-zA-Z]{4,20}[a-zA-Z0-9]*)");
        Matcher matcher = wordPattern.matcher(context);
        while (matcher.find()){
            count++;
        }
        return count;
    }

    /*
      未实现代码

    public static void staticWork(String context){

    }
    */
}
