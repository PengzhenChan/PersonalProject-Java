import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @Description: 对输入文件进行字符数，单词数，行数等进行统计
 * @Author: 曹鑫
 * @Date: 2021/2/28
 */
class WordCount
{
    public static void main(String[] args)
    {
        WordCount wc = new WordCount();
        try {
            wc.outPut(wc.count(args[0]),args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件");
            e.printStackTrace();
        }
    }

    /**
     * @Description:  进行字符数，单词数，行数等进行统计
     * @Param: [input]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public String count(String input)
    {
        Lib lib = new Lib();
        StringBuilder result = new StringBuilder(10);
        //打开输入文件
        File file = new File(input);
        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            result.append(lib.getCharNum(file,inputStream));
            List<StringBuilder> wordNumList=getWordNum(file,inputStream);
            result.append(wordNumList.get(0));
            result.append("lines: " + lib.getVaildLineNum(file) + "\n");
            result.append(wordNumList.get(1));
            System.out.println("程序计算的答案" + result);
            inputStream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return result.toString();
    }


    /**
     * @Description: 将流输出至文件中
     * @Param: [outPut]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public void outPut(String outPut,String outPutPath) throws FileNotFoundException
    {
        System.out.println("输出文件路径:" + outPutPath);
        File file=new File(outPutPath);
        FileOutputStream outputStream = new FileOutputStream(file);
        try
        {
            outputStream.write(outPut.getBytes());
            outputStream.close();
        }  catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                outputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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
        List<Map.Entry<String,Integer>> list=sortMapByValue(map);
        StringBuilder str = new StringBuilder();
        int total = 0,index = 0;

        for (Map.Entry<String,Integer> entry:list)
        {
            total += entry.getValue();
            //只统计频率较高的
            if (index++<10)
            {
                //System.out.println(entry.getKey()+"  ");
                str.append(entry.getKey()+": " + entry.getValue()+"\n");
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
        StringBuilder str = new StringBuilder(3);
        Boolean isWord = true;
        char tmp='\n';

        for (int i = 0;i <= file.length();i++)
        {
            try
            {
                if (i<file.length())
                {
                    tmp = (char)(inputStream.read());
                }
                else
                {
                    tmp = '\n';                }
//                System.out.print(tmp+"  ");
                if (Character.isUpperCase(tmp))
                {
                    tmp = Character.toLowerCase(tmp);
                    str.append(tmp);
                }
                else if (Character.isLowerCase(tmp))
                {
                    str.append(tmp);
                }
                else if (Character.isDigit(tmp))
                {
                    if (str.length() < 4)
                    {
                        isWord = false;
                    }
                    str.append(tmp);
                }
                else if (!(Character.isDigit(tmp) || Character.isLowerCase(tmp)) && str != null&&str.length() >= 4)
                {
//                    System.out.println("单词统计中1:"+str.toString());
                    if (isWord)
                    {
                        if (map.containsKey(str.toString()))
                        {
//                        System.out.println("单词统计中:"+str.toString());
                            map.replace(str.toString(),map.get(str.toString()) + 1);
                        }
                        else
                        {
                            map.put(str.toString(),1);
                        }
                    }
                    else
                    {
                        System.out.println(str.toString() + "不是单词，不进行统计");
                    }
                    isWord = true;
                    str = new StringBuilder(3);
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return map;
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
}