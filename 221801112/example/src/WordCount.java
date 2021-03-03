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
            List<StringBuilder> wordNumList=lib.getWordNum(file,inputStream);
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
}