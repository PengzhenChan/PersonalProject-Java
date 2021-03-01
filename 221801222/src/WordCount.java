
import java.io.*;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args) throws IOException {

        /*
        输入部分
         */
//        if(args.length != 2)
//        {
//            System.out.print("程序只接受两个参数");
//            return;  //退出程序
//        }
//
//        String inputFileName = args[0]; //输入文件名
//        String outputFileName = args[1]; //输出文件名

        String text = "";  //存放从输入文件输入的数据
        String inputFileName = "input.txt"; //输入文件名
        String outputFileName = "output.txt"; //输出文件名
        FileReader inputFileReader = null;  //读文件Reader
        BufferedReader inputBfd = null;    //缓存Reader 提高效率

        try {
            inputFileReader = new FileReader(inputFileName);   //读文件Reader
            inputBfd = new BufferedReader(inputFileReader);  //缓存Reader 提高效率

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
            if (inputFileReader != null)
                inputFileReader.close();
        }

        /*
        执行计算
         */
        Lib lib = new Lib(text);
        int chars = lib.getChars();  //字符数
        int lines = lib.getLines();  //行数
        int words = lib.getWords();  //单词数
        Map<String, Integer> maxCntWords = lib.getMaxCntWords();  //频率最高10


        /*
        输出部分
         */
        File file = new File(outputFileName);
        PrintWriter output = null;
//        if (file.exists()) { // 检查scores.txt是否存在
//            System.out.println("文件 " + outputFileName + " 已存在");
//            System.exit(1); // 如果存在则退出程序
//        }
        // 如果不存在则创建一个新文件
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
}