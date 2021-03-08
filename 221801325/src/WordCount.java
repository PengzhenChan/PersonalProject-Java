
import javax.sound.sampled.Line;
import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args)
    {
            Lib lib = new Lib();
            String string = lib.readFile(args[0]);
        try {
            lib.output(args[1],"characters",lib.charCount(string));
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        lib.output(args[1],"words",lib.countword(string));
            lib.output(args[1],"lines",lib.lineCount(string));
            List<Map.Entry<String, Integer>> list = lib.countmostWord();
            for (int i = 0; i < list.size(); i++)
            {
                Map.Entry<String, Integer> mapping = list.get(i);
                if(i<10)
                {
                    lib.output(args[1],mapping.getKey(),mapping.getValue());
                }
            }
            
        }






}
