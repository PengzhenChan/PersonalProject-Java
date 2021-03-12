import lib.service.*;
import lib.tool.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WordCount
{
    /**
     * @param inputFileName
     * @param outputFileName
     */
    private static String inputFileName;
    private static String outputFileName;

    public WordCount(String inputFileName, String outputFileName)
    {
        WordCount.inputFileName = inputFileName;
        WordCount.outputFileName = outputFileName;
    }

    public void Count()
    {
        final String content = FileReader.readFile(inputFileName);// 从文件读取字符串
        final HashMap<String, Integer> words = StringAnalyser.analyseString(content);// 从字符串拆分有效单词，统计入HashMap

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> charCnt = executor.submit(new Callable<Integer>()
        {
            // 统计字符数
            public Integer call()
            {
                return CharAndWordCounter.countChar(content);
            }
        });

        Future<Integer> lineCnt = executor.submit(new Callable<Integer>()
        {
            // 统计有效行数
            public Integer call()
            {
                return LineCounter.countLine(content);
            }
        });

        Future<Integer> wordCnt = executor.submit(new Callable<Integer>()
        {
            // 统计有效单词数
            public Integer call()
            {
                return CharAndWordCounter.countWord(words);
            }
        });

        Future<ArrayList<HashMap.Entry<String, Integer>>> freqList = executor
                .submit(new Callable<ArrayList<HashMap.Entry<String, Integer>>>()
                {
                    // 排序词频前10单词
                    public ArrayList<HashMap.Entry<String, Integer>> call()
                    {
                        return FrequencySorter.sortFrequency(words);
                    }
                });

        try
        {
            FilePrinter.writeFile(charCnt.get(), wordCnt.get(), lineCnt.get(), freqList.get(), outputFileName);// 打印结果
            executor.shutdown();
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        WordCount cmd;

        if (args.length != 2)
        {
            System.out.println("Invalid input");
            return;
        }
        cmd = new WordCount(args[0], args[1]);// 传入参数（输入输出文件名）
        // cmd = new WordCount("src/input.txt", "src/output.txt");
        cmd.Count();// 统计
    }
}
