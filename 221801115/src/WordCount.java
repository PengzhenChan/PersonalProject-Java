import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount 
{
    private static String inputPath;/*输入路径*/
    private static String outputPath;/*输出路径*/
	public static void main(String[] args) 
    {
        inputPath = "C:\\Users\\Lin Minghao\\Desktop\\" + args[0];
        outputPath = "C:\\Users\\Lin Minghao\\Desktop\\" + args[1];

        writeFile(outputPath);
	}

    public static int countCharacters(String inputPath) 
    {
        int characters = 0;

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));

            while ((br.read()) != -1) 
            {
                characters++;
            }

            br.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return characters;
    }

    public static int countNumberOfWords(String inputPath)
    {
        int num = 0;

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            String text = "";

            while ((tmp = br.readLine()) != null)
            {
                text += tmp + "\n";
            }

            String regex = "(?<=[^a-zA-Z0-9])[a-zA-Z]{4}[a-zA-Z0-9]{0,}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find())
            {
                num++;
            }

            br.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return num;
    }

    public static int countLines(String inputPath) 
    {
        int lines = 0;

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            
            while ((tmp = br.readLine()) != null) 
            {
                if (!tmp.equals("")) 
                {
                    lines++;
                }
            } 

            br.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeFile(String outputPath) 
    {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
            bw.write("characters: " + countCharacters(inputPath));
            bw.write("\nwords: " + countNumberOfWords(inputPath));
            bw.write("\nlines: " + countLines(inputPath));
            bw.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}