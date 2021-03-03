import java.io.*;

public class Lib {
    /**
     * @Description: 统计文件的有效行数
     * @Param: [file]
     * @return: int
     * @Date: 2021/3/2
     */
    public int getVaildLineNum(File file)
    {
        int lineNum = 0;
        if (file.exists())
        {
            try
            {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
                String str;
                while ((str=lnr.readLine())!=null)
                {
                    //统计包含非空白字符的行
                    if (!(str.isBlank()))
                        lineNum++;
                }
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return lineNum;
    }

    /**
     * @Description:  统计文件的字符数
     * @Param: [file, inputStream]
     * @return: java.lang.String
     * @Date: 2021/2/28
     */
    public String getCharNum(File file,FileInputStream inputStream)
    {
        return "characters: " + String.valueOf(file.length()) + "\n";
    }
}
