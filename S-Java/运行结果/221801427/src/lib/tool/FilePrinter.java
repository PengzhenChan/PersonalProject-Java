package lib.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FilePrinter
{
    /**
     * @param charCnt
     * @param wordCnt
     * @param lineCnt
     * @param freqList
     * @param outputFileName
     */
    public static void writeFile(int charCnt, int wordCnt, int lineCnt,
            ArrayList<HashMap.Entry<String, Integer>> freqList, String outputFileName)
    {
        // 接收需要统计的数值并打印
        File file = new File(outputFileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try
        {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);// 使用BufferedWriter提高性能

            bufferedWriter.write("characters: " + charCnt + "\n");
            bufferedWriter.write("words: " + wordCnt + "\n");
            bufferedWriter.write("lines: " + lineCnt + "\n");
            for (HashMap.Entry<String, Integer> map : freqList)
            {
                bufferedWriter.write(map.getKey() + ": " + map.getValue() + "\n");// 打印HashMap的键与对应值
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Error Writing File");
            e.printStackTrace();
        }
    }
}

