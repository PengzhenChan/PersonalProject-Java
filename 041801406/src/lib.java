import java.io.*;

public class lib {
    static int countChar(File file)
    {
        int myCountChar = 0;
        if(!file.exists())
        {
            return -1;
        }
        else
        {
            try
            {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while(br.read() != 1)
                {
                    String s = br.readLine();
                    myCountChar += s.length();
                }
                return myCountChar;
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                return myCountChar;
            }
        }
    }
    static int countLine(File file)
    {
        int myCountLine=0;
        if(!file.exists())
        {
            return -1;
        }
        else
        {
            try
            {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while(br.readLine()!=null)
                {
                    myCountLine++;
                }
                return myCountLine;
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                return myCountLine;
            }
        }
    }
}
