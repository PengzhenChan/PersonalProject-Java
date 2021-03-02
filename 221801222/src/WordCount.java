
import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class WordCount
{

    private String text = "";
    private String inputFileName;
    private String outputFileName;
    private int chars;
    private int words;
    private int lines;
    private Map<String, Integer> maxCntWords;

    /*
    将文件读出函数
     */
    private void input() throws IOException {
        BufferedReader inputBfd = null;    //缓存Reader 提高效率

        try {
            inputBfd = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "UTF-8"));  //缓存Reader 提高效率

            int charIndex = 0;
            StringBuilder stringBuilder = new StringBuilder();   //字符串构建器
            while ((charIndex = inputBfd.read()) != -1) {
                stringBuilder.append((char) charIndex);
            }
            text = stringBuilder.toString();   //转换为字符串
        }
        catch (Exception e)  //错误处理
        {
            System.out.print(e.getMessage());
            System.out.print("\n文件读出错！");
        }
        finally
        {
            if (inputBfd != null)
                inputBfd.close();   //关闭文件流
        }

    }


    /*
    计算部分
     */
    private void calculate()
    {
        Lib lib = new Lib(text);
        chars = lib.getChars();  //字符数
        lines = lib.getLines();  //行数
        words = lib.getWords();  //单词数
        maxCntWords = lib.getMaxCntWords();  //频率最高10
    }


    /*
    输出部分
     */
    private void output()
    {
        File file = new File(outputFileName);
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(file);
            output.print("characters:" + chars + '\n');
            output.print("words:" + words + '\n');
            output.print("lines:" + lines + '\n');
            for (Map.Entry<String, Integer> entry : maxCntWords.entrySet())
            {
                output.print(entry.getKey() + ":" + entry.getValue() + '\n');
            }
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
            System.out.print("\n文件写出错！");
        }
        finally
        {
            if(output != null)
                output.close();
        }
    }


    /*
    构造函数，接受两个参数
    输入和输出文件名
     */
    public WordCount(String inputFileName, String outputFileName) throws IOException {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        input();
        calculate();
        output();
    }


    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();  //计算代码运行时间
        if(args.length != 2)
        {
            System.out.print("程序只接受两个参数");
            return;  //退出程序
        }

        String inputFileName = args[0]; //输入文件名
        String outputFileName = args[1]; //输出文件名

        //初始化类
        new WordCount(inputFileName, outputFileName);
        System.out.println("程序运行结束共耗时" + (System.currentTimeMillis() - time) + "毫秒");
    }
}