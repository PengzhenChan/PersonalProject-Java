import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*读取目标文件中的内容 存放到String数组中*/
public class Lib {
    public static long wordNum;
    static final int BUFF_SIZE = 0x1000000;
    static final char ofLow = 1<<5;

    /***文件读取
    * @Description: 字节流读取文件
    * @Param: fileName 传入文件名
    * @return: 读取成功则返回读取到的字符串
    * @Author: top
    * @Date: 2021/3/5
    */
    public static String readTxt(File file){
        StringBuffer stringBuffer = new StringBuffer();
        try{
            InputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[BUFF_SIZE];

            int len;        //len用来记录每次从缓冲区读取的长度
            while((len=bufferedInputStream.read(bytes)) != -1){
                stringBuffer.append(new String(bytes, 0,len,StandardCharsets.UTF_8));
            }
            bufferedInputStream.close();
            inputStream.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
        return stringBuffer.toString();
    }

    /*按照要求输出到Txt文件*/
    public static void outputToTxt(long num1, long num2, long line, List<Map.Entry<String, Integer>> list, File outputFile){
        try{
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //输出ascii字符数
            bufferedWriter.write("characters:"+num1+"\r\n");
            bufferedWriter.write("words:"+num2+"\r\n");
            bufferedWriter.write("lines:"+line+"\r\n");
            for(int i=0; i<list.size()&&i<10; i++){
                String key = list.get(i).getKey();
                Integer value = list.get(i).getValue();
                bufferedWriter.write(key+":"+value+"\r\n");
            }
            bufferedWriter.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }

    /*统计文件ascii字符数量 传入参数text为文本内容字符串*/
    public static int countChar(String text){
        return text.length();
    }

//    public static long countLine(String text){
//        int num = 0;
//        String[] lines = text.split("\r\n|\r|\n");
//
//        for(int i=0; i<lines.length; i++){
//            if (!lines[i].trim().isEmpty()){
//                num++;
//            }
//        }
//        return num;
//    }
    public static long countLine(String text){
        long num=0;
        String regex = "(.*)(\\s)";
        Matcher matcher = regexUtils(regex, text);
        while (matcher.find()){
            String tmp = matcher.group(1);
            if(!tmp.trim().isEmpty()){
                num++;
            }
        }
        return num;
    }

    public static List<Map.Entry<String, Integer>> sortHashMap(HashMap<String, Integer> hashMap){
        Set<Map.Entry<String, Integer>> entry = hashMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entry);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue())){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue()-o1.getValue();
            }
        });

        if(list.size()>10) {
            return list.subList(0,10);
        }
        else{
            return list;
        }
    }

    /***
     * @Description: 由于用split方法将String分离耗时过久,用matches匹配也很久,就改为直接对String进行匹配
     * 返回所有合法单词的hashMap，并且value为出现次数
     * @Param: text 传入文本
     * @return: 存放单词和单词频率的HashMap<String, Integer>
     * @Author: top
     * @Date: 2021/3/5
     */
    public static HashMap<String, Integer> findLegal(String text){
        wordNum = 0;
        text = " " + toLowerStr(text);
        String regex = "([^a-z0-9])([a-z]{4}[a-z0-9]*)";
        Matcher matcher = regexUtils(regex, text);
        HashMap<String, Integer> legalWords = new HashMap<String, Integer>();

        //直接把单词和出现频率一起做了，放到hashMap里
        while(matcher.find()){
            wordNum++;
            String tmp = matcher.group(2).trim();
            if(!legalWords.containsKey(tmp)){
                legalWords.put(tmp,1);
            }
            else{
                legalWords.put(tmp, legalWords.get(tmp)+1);
            }
        }

        return legalWords;
    }

    /***
    * @Description:必须在findLegal后执行
    * @Param:
    * @return:
    * @Author: top
    * @Date: 2021/3/5
    */
    public static long countWordNum(){
        return wordNum;
    }

    public static Matcher regexUtils(String regex, String text){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }



    public static String toLowerStr(String str){
        char[] charArray = str.toCharArray();
        for(int i=0; i<charArray.length; i++){
            char tmp = charArray[i];
            if(tmp>='A' && tmp<='Z'){
                charArray[i] = toLowerChar(tmp);
            }
        }
        return new String(charArray, 0, charArray.length);
    }

    private static char toLowerChar(char tmp) {
        return (char) (tmp|ofLow);
    }


}
