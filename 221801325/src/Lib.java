import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lib
{
    Map<String, Integer> map = new HashMap<String, Integer>();
    public List<Map.Entry<String, Integer>> countmostWord( )
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
        String regex = "([^a-zA-Z0-9])([a-zA-Z]{4}[a-zA-Z0-9]*)";//正则匹配
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while(matcher.find())
        {   String temp=matcher.group(2).trim().toLowerCase();//
            num++;
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);//
            } else {
                map.put(temp, 1);
            }
        }
        return num;

    }
    public long charCount(String string) throws IOException
    {
        long characters = string.length();
//        String regex = "\\p{ASCII}";//正则表达式判断是否为合法字符
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(string);
//        while (matcher.find()) {
//            characters++;
//        }
        return characters-1;
    }
    public  long lineCount(String string)
    {
        long num_of_line=0;
        String regex = "(.*)(\\S)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find())
        {
      //      if (matcher.group(1)!=null&&!matcher.group(1).equals('\t')&&!matcher.group(1).equals('\n')&&!matcher.group(1).equals('\40'))
            num_of_line++;
        }
        return num_of_line;
    }




    public  void output(String output_file,String style,long num)
    {

        File txt=new File(output_file);
        try
        {
            FileWriter fw = new FileWriter(txt,true);//参数为true代表可以追加写入d
            BufferedWriter out = new BufferedWriter(fw);//
            out.write(style +":" + num + "\r\n");
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("Can't find file");
        }
    }


        public  String readFile(String filePath) {
            try {
                File file = new File(filePath);
                FileInputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                String s = new String( );
                s="\t";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                baos.write(s.getBytes());
                int cnt = 0;
                while ((cnt = inputStream.read(buffer)) > -1)
                {
                    baos.write(buffer, 0, cnt);
                }
                baos.flush();
                inputStream.close();
                baos.close();
                return baos.toString();
            }
            catch (Exception e)
            {
                System.out.println("Can't find file");
            }
            return "";
        }



}
