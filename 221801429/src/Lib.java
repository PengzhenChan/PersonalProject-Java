import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public static String txtToString(String filePath){
        File file = new File(filePath);
        String context = "";
        try{
            FileInputStream inputStream = new FileInputStream(file);
            int lenth = inputStream.available();
            byte[] buffer = new byte[lenth];
            inputStream.read(buffer);
            inputStream.close();
            context = new String(buffer, "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("读取文件失败！");
            return null;
        }
        return context;
    }

    /*
      功能：返回String文本字符数
      参数：String
      返回类型：int
     */
    public static int countChar(String context){
        return context.getBytes().length;
    }

    /*
      统计文本非空行数
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
      功能: 返回按单词频率排序从高到低好的List<Map.Entry>，key为单词，value为频率
      参数: String
      返回类型: HashMap<String, Integer>
      备注: 无视单词大小写差异
    */
    public static List<Map.Entry<String,Integer>> staticFrequency(String context){
        HashMap<String, Integer> frequency = new HashMap<>();
        String text = context.toLowerCase();
        Pattern wordPattern = Pattern.compile("([^A-Za-z0-9]+|^)([a-zA-Z]{4,20}[a-zA-Z0-9]*)");
        Matcher matcher = wordPattern.matcher(text);
        while(matcher.find()){
            String word = matcher.group(2);

            //HashMap尚不存在指定的key
            if(!frequency.containsKey(word)){
                frequency.put(word, 1);
            }

            //已经存在该单词的key
            else {
                int countWord = frequency.get(word);
                countWord++;
                frequency.put(word, countWord);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<>();
        list.addAll(frequency.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(!o2.getValue().equals(o1.getValue())) {
                    return o2.getValue().compareTo(o1.getValue());
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        int length = (list.size()>10)? 10 : list.size();
        return list.subList(0,length);

        /*测试排序是否正确
        int sum = 0;
        for(Map.Entry<String, Integer> m:frequency.entrySet())
        {
            sum += m.getValue();
            System.out.println(""+m.getKey()+"出现的次数为："+m.getValue()+"次");
        }
        System.out.println("攻击单词"+sum);
       */
    }

}
