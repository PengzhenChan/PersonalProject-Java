import java.io.*;

public class WordCount
{
    public static void main(String[] args) throws IOException
    {
        CountChar countChar = new CountChar();
        Countword countword = new Countword();
        String root = System.getProperty("user.dir");
        String path = root+ File.separator+"src"+File.separator+"input.txt";

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
            String s = "";
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                s = s + line + "\n";
            }
            output("output.txt","characters",countChar.charCount(s));
            output("output.txt","words:",countword.countword(s));

            System.out.println(countChar.charCount(s)+""+countword.countword(s));
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void output(String output_file,String style,int num)
    {
        // TODO Auto-generated method stub
        File txt=new File(output_file);
        try
        {
            FileWriter fw=new FileWriter(txt,true);//参数为true代表可以追加写入
            BufferedWriter out = new BufferedWriter(fw);
            out.write(style+":"+num+"\r\n");
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("指定输出文件不存在");
        }
    }
}
