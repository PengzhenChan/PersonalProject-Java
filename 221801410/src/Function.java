import java.util.*;
import java.io.*;

public class Function {
    
    public Function()
    {
    }
    
    public boolean IsEmptyLine(String wordLine)
    {
        if(wordLine.replaceAll("\\s*", "").equals(""))   //�滻�������еĿո��Ʊ���ҳ�����Ƿ�Ϊ��
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
