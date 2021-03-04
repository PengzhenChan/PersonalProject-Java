import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lib
{
    Map<String, Integer> map = new HashMap<String, Integer>();
    public List<Map.Entry<String, Integer>> countmostWord(String str)
    {


        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> mapping2, Map.Entry<String, Integer> mapping1)
            {
                int result = mapping1.getValue().compareTo(mapping2.getValue());
                if(result!=0)
                {
                    return result;//即两个Value不相同，就按照Value倒序输出
                }
                else
                    {
                    return mapping2.getKey().compareTo(mapping1.getKey());
                    }//若两个Value相同，就按照Key倒序输出
            }
        });

        return list;
    }
    public long  countword(String string)
    {
        long num = 0;
        double straTime1 = System.currentTimeMillis();//获取结束时间
        string=string.toLowerCase();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" ");
        stringBuffer.append(string);
        String regex = "([^a-z0-9])([a-z]{4}[a-z0-9]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringBuffer.toString());
        while(matcher.find())
        {   String temp=matcher.group(2).trim();
            num++;
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);
            } else {
                map.put(temp, 1);
            }
        }
        double endTime1 = System.currentTimeMillis();//获取结束时间
        System.out.println("计算单词数量耗时"+(endTime1 - straTime1) +"毫秒");
        return num;

    }
    public long charCount(String string) throws IOException
    {
        long characters = string.length();
//        String regex = "\\p{ASCII}";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(string);
//        while (matcher.find()) {
//            characters++;
//        }
        return characters;
    }
    public  long lineCount(String string)
    {
        long num_of_line=0;

        String regex = "(.*)(\\S)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        double star3 = System.currentTimeMillis();//获取结束时间
        while (matcher.find())
        {
            if (matcher.group(1)!=null&&!matcher.group(1).equals("")&&!matcher.group(2).equals('\t'))
            num_of_line++;
        }
        double end3 = System.currentTimeMillis();//获取结束时间
        System.out.println("计算行数共耗时" + (end3 - star3) + "毫秒");//输出程序运行时间
//
//        String[] line = string.split("\r\n|\r|\n");
//        for (int i = 0;i<line.length;i++)
//        {
//            if(!line[i].equals("")&&line[i]!=null)
//            {
//                num_of_line++;
//            }
//
//        }
        return num_of_line;
    }
    public String openFile(String path) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuffer s =  new StringBuffer();
        int li=0;
        double endTime1 = System.currentTimeMillis();//获取结束时间
        while ((li=bufferedReader.read())!= -1)
        {

            char c= (char)li;
            s.append(c);
        }
        bufferedReader.close();
        return s.toString();
    }
    public  void output(String output_file,String style,long num)
    {
        // TODO Auto-generated method stub
        File txt=new File(output_file);
        try
        {
            FileWriter fw = new FileWriter(txt,true);//参数为true代表可以追加写入
            BufferedWriter out = new BufferedWriter(fw);
            out.write(style +":" + num + "\r\n");
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("指定输出文件不存在");
        }
    }
}
