import lib.service.*;
import lib.tool.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCount
{
    /**
     * @param inputFileName
     * @param outputFileName
     */
    private static String inputFileName;
    private static String outputFileName;
    private static String content = new String();
    private int charCnt = 0;
    private int lineCnt = 0;

    public WordCount(String inputFileName, String outputFileName)
    {
        WordCount.inputFileName = inputFileName;
        WordCount.outputFileName = outputFileName;
    }

    public void Count()
    {
        InputStreamReader isr = null;
        BufferedReader bf = null;
        String line;
        
        try
        {
            isr = new InputStreamReader(new FileInputStream(inputFileName));
            bf = new BufferedReader(isr);
            StringBuffer contents = new StringBuffer();

            line = bf.readLine();
            while (line != null)
            {
                if (!line.equals(""))
                {
                    lineCnt++;
                }
                contents.append(line);
                line = bf.readLine();
                if (line != null)
                {
                    contents.append("\n");
                }
            }
            content = contents.toString();

            charCnt = CharCounter.countChar(content);

            bf.close();
            isr.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void Print()
    {
        FilePrinter.writeFile(charCnt, lineCnt, outputFileName);
    }

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Invalid input");
            return;
        }
        WordCount cmd = new WordCount(args[0], args[1]);
        cmd.Count();
        cmd.Print();
    }
}
