import java.util.*;
import java.util.Map.Entry;

public class WordDeal // 该类用于进行文件中的单词等处理
{
	
	private int charNum; //统计字符数
	String content;//代表文件

	//// 统计文件字符数
	public int getCharCount() 
	{
		char c;
		int i=0;
		while (i<(content.length())) 
		{
			c = content.charAt(i);
			if (c >= 32 && c <= 126 || c == '\r' || c == '\n' || c == '\t') 
			{
				charNum++;
			}
			i++;
		}
		return charNum;
	}

}