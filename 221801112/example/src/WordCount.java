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
        //            wc.outPut(wc.count(args[0]),args[1]);
        wc.count(args[0]);
    }

    /**
     * @Description:  进行字符数，单词数，行数等进行统计
     * @Param: [input]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public String count(String input)
    {
        StringBuilder result = new StringBuilder(10);
        //打开输入文件
        File file = new File(input);
        try
        {
            FileInputStream inputStream=new FileInputStream(file);
            result.append(getCharNum(file,inputStream));
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
     * @Description:  统计文件的字符数
     * @Param: [file, inputStream]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public String getCharNum(File file,FileInputStream inputStream)
    {
        return "characters: " + String.valueOf(file.length()) + "\n";
    }


    /**
     * @Description: 将流输出至文件中
     * @Param: [outPut]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public void outPut(String outPut,String outPutPath) throws FileNotFoundException
    {
        System.out.println("输出文件路径:"+outPutPath);
        File file=new File(outPutPath);
        FileOutputStream outputStream=new FileOutputStream(file);
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
}