import java.util.*;
import java.io.*;

public class Function {
    
    public Function()
    {
    }
    
    public boolean IsEmptyLine(String wordLine)
    {
        if(wordLine.replaceAll("\\s*", "").equals(""))   //替换掉输入行的空格、制表、换页符后是否为空
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
