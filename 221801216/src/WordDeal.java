import java.util.*;
import java.util.Map.Entry;

public class WordDeal // 该类用于进行文件中的单词等处理
{
	
	private int charNum; //统计字符数
	String content;//代表文件
	private int wordCount; // 单词总数
	private int validLine;//有效行数

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
	
	//统计单词总数
	public int getWordCount() 
	{
		String s= content;
		//用"\\"来划分词
		String[] sp = s.split("\\s"); 
		for (int i = 0; i < sp.length; i++) 
		{
			// 判断长度是否大于等于4,因为只有大于4的才称为单词
			if (sp[i].length() < 4) 
			{ 
				continue;
			} 
			else 
			{
				// 判断字符串的前四位是否是英文字母
				int flag = 1; 
				char c;
				for (int j = 0; j < 4; j++) 
				{
					c = sp[i].charAt(j);
					if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) 
					{
						flag = 0;
					}
				}
				if (flag == 1) 
				{
					wordCount++;
				}
			}
		}
		return wordCount;
	}
	
	
	// 统计有效行数
	public int getLineCount() 
	{ 
		//将每一行分开放入一个字符串数组
		String[] line = content.split("\n"); 
		// 找出无效行，统计有效行
		for (int i = 0; i < line.length; i++) 
		{ 

			if (line[i].trim().length() == 0)
				continue;
			validLine = validLine + 1;
		}
		return validLine;
	}
}