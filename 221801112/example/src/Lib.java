import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    /**
     * @Description: 统计文件的有效行数
     * @Param: [file]
     * @return: int
     * @Date: 2021/3/2
     */
    public int getVaildLineNum(File file)
    {
        int lineNum = 0;
        if (file.exists())
        {
            try
            {
                FileReader fr = new FileReader(file);
                //利用缓冲区提升读取性能
                BufferedReader br = new BufferedReader(fr);
                LineNumberReader lnr = new LineNumberReader(br);
                String str;
                while ((str = lnr.readLine()) != null)
                {
                    //统计包含非空白字符的行
                    if (!(isBlankString(str)))
                        lineNum++;
                }
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return lineNum;
    }

    /**
     * @Description:  统计文件的字符数
     * @Param: [file, inputStream]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public String getCharNum(File file)
    {
        return "characters: " + String.valueOf(file.length()) + "\n";
    }

    /**
     * @Description:  统计文件单词总数已及数量最多的十个单词
     * @Param: [file, inputStream]
     * @return: java.lang.String
     * @Date: 2021/3/2
     */
    public List<StringBuilder> getWordNum(File file, FileInputStream inputStream)
    {
        Map<String,Integer> map = getMapWordNum(file,inputStream);
        List<Map.Entry<String,Integer>> list = sortMapByValue(map);
        StringBuilder str = new StringBuilder();
        int total = 0,index = 0;

        for (Map.Entry<String,Integer> entry:list)
        {
            total += entry.getValue();
            //只统计频率较高的
            if (index++ < 10)
            {
                str.append(entry.getKey()+": " + entry.getValue() + "\n");
            }
        }
        List<StringBuilder> reslutList = new ArrayList<>();
        //统计单词总数
        reslutList.add(new StringBuilder("words: " + total + "\n"));
        //统计单词频数
        reslutList.add(str);
        return reslutList;
    }

    /**
     * @Description: 对文件内容分析获得以单词为key,数量为value 的map结果
     * @Param: [file, inputStream]
     * @return: java.util.Map<java.lang.String,java.lang.Integer>
     * @Date: 2021/3/2
     */
    private Map<String,Integer> getMapWordNum(File file,FileInputStream inputStream)
    {
        Map<String,Integer> map = new HashMap<>();
        String s=new String(readFile(file));
        String[] strs = s.split("[^a-zA-Z0-9]");
        Pattern pattern = Pattern.compile("^[a-z]{4}[a-z0-9]*");
        Matcher matcher = null;
        int cnt = 0;

        for (String str : strs)
        {
            str = str.toLowerCase();
            matcher = pattern.matcher(str);

            if (!str.isEmpty()&&matcher.find())
            {
                cnt++;
                Integer t = map.get(str);
                if (t == null)
                    t = 0;
                map.put(str,1+t);
            }
        }
        return map;
    }

    /**
    * @Description:  读取文件内容获取String
    * @Param: [file]
    * @return: java.lang.String
    * @Date: 2021/3/3
    */
    private static StringBuilder readFile(File file)
    {
        BufferedReader reader = null;
        StringBuilder str = new StringBuilder(1024);
        int c;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((c = reader.read())!=-1)
            {
                str.append((char) c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在:" + file.getPath());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * @Description: 对map进行按value大小，及key字典序降序排序
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Integer>
     * @Date: 2021/3/2
     */
    private List<Map.Entry<String,Integer>> sortMapByValue(Map<String,Integer> map)
    {
        //将hashMap转化为list
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        //进行排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                if (o1.getValue() == o2.getValue())
                {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    /**
    * @Description:   判断字符串是否为空白字符串
    * @Param: [str]
    * @return: boolean
    * @Date: 2021/3/3
    */
    boolean isBlankString(String str)
    {
        return str==null||str.trim().isEmpty();
    }

}
