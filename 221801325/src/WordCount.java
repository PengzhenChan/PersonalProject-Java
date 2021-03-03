
import java.io.*;
import java.util.List;
import java.util.Map;

public class WordCount
{
    public static void main(String[] args) throws IOException
    {   int num_of_line = 0;
        Lib lib = new Lib();
        String root = System.getProperty("user.dir");
        String path = root+File.separator+"src"+File.separator+"input.txt";
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            StringBuffer s =  new StringBuffer();
           // RandomAccessFile randomAccessFile = new RandomAccessFile("input.txt", "r");
            String line=null;
            double endTime1 = System.currentTimeMillis();//获取结束时间
            while ((line = bufferedReader.readLine()) != null)
            {
                num_of_line++;
                s.append(line);
            }
            double endTime2 = System.currentTimeMillis();//获取结束时间

            output("output.txt","characters",lib.charCount(s.toString()));
            output("output.txt","words:",lib.countword(s.toString()));
            output("output.txt","lines:",num_of_line);
            List<Map.Entry<String, Integer>> list = lib.countWord(s.toString());
            double endTime3 = System.currentTimeMillis();//获取结束时间
            double a=(endTime2 - endTime1)/(endTime3 - endTime1);
            double b=(endTime3 - endTime2)/(endTime3 - endTime1);
            System.out.println("读取文件共耗时共耗时" + (endTime2 - endTime1) + "毫秒，占总时长"+(a*100)+"%");//输出程序运行时间
            System.out.println("完成计算" + (endTime3 - endTime2) + "毫秒，占总时长"+(b*100)+"%" );//输出程序运行时间
            for (int i = 0; i < list.size(); i++)
            {
                Map.Entry<String, Integer> mapping = list.get(i);
                if(i<10)
                {
                    output("output.txt",mapping.getKey(),mapping.getValue());
                }
            }
            //System.out.println(lib.charCount(s) + "|"+lib.countword(s) + "|"+num_of_line + "|");
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
    public static void output(String output_file,String style,int num)
    {
        // TODO Auto-generated method stub
        File txt=new File(output_file);
        try
        {
            FileWriter fw = new FileWriter(txt,true);//参数为true代表可以追加写入
            BufferedWriter out = new BufferedWriter(fw);
            out.write(style +":" + num + "\r\n");
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("指定输出文件不存在");
        }
    }
}
