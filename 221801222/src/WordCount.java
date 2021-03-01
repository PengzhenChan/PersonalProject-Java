
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount
{
    public static void main(String[] args) throws IOException
    {
        if(args.length != 2)
        {
            System.out.print("程序只接受两个参数");
            return;  //退出程序
        }

        String inputFileName = args[0]; //输入文件名
        String outputFileName = args[1]; //输出文件名

//        String inputFileName = "input.txt"; //输入文件名
//        String outputFileName = "input.txt"; //输出文件名



        String text;
        FileReader inputFileReader = null;  //读文件Reader
        BufferedReader inputBfd = null;    //缓存Reader 提高效率

        try
        {
            inputFileReader = new FileReader(inputFileName);   //读文件Reader
            inputBfd = new BufferedReader(inputFileReader);  //缓存Reader 提高效率

            int charIndex = 0;
            StringBuilder stringBuilder = new StringBuilder();   //字符串构建器
            while((charIndex = inputBfd.read()) != -1)
            {
                stringBuilder.append((char)charIndex);
            }
            text = stringBuilder.toString();   //转换为字符串

            System.out.print(text);
            
            inputBfd.close();  //关闭文件流
            inputFileReader.close();
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
            System.out.print("\n文件读出错！");
            if(inputBfd != null)
                inputBfd.close();   //关闭文件流
            if(inputFileReader != null)
                inputFileReader.close();
        }


    }
}