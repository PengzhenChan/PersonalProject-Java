
import javax.sound.sampled.Line;
import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args) throws IOException
    {
            Lib lib = new Lib();
            String string = lib.openFile("input.txt");

            double endTime2 = System.currentTimeMillis();//获取结束时间
            lib.output("output.txt","characters",lib.charCount(string));
            lib.output("output.txt","words:",lib.countword(string));
            lib.output("output.txt","lines:",lib.lineCount(string));
            List<Map.Entry<String, Integer>> list = lib.countmostWord(string);
//            double endTime3 = System.currentTimeMillis();//获取结束时间
//            double a=(endTime2 - endTime1)/(endTime3 - endTime1);
//            double b=(endTime3 - endTime2)/(endTime3 - endTime1);
            //System.out.println("读取文件共耗时共耗时" + (endTime2 - endTime1) + "毫秒，占总时长"+(a*100)+"%");//输出程序运行时间
            //System.out.println("完成计算" + (endTime3 - endTime2) + "毫秒，占总时长"+(b*100)+"%" );//输出程序运行时间
            for (int i = 0; i < list.size(); i++)
            {
                Map.Entry<String, Integer> mapping = list.get(i);
                if(i<10)
                {
                    lib.output("output.txt",mapping.getKey(),mapping.getValue());
                }
            }
           // System.out.println(lib.charCount(s.toString()) + "|"+lib.countword(s.toString()) + "|"+lib.lineCount(s.toString())+ "|");
            
        }






}
