
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

            for (int i = 0; i < list.size(); i++)
            {
                Map.Entry<String, Integer> mapping = list.get(i);
                if(i<10)
                {
                    lib.output("output.txt",mapping.getKey(),mapping.getValue());
                }
            }
            
        }






}
