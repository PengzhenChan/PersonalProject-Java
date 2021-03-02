import java.io.IOException;
import java.util.*;

public class WordCount
{

	public static void main(String[] args) throws IOException 
	{
		//测试用
		System.out.println("请输入文件名字");
		
		//定义所需数据结构
		Scanner sc = new Scanner(System.in);
		String file = sc.next();
		FileDeal fd = new FileDeal();
		String[] wFreq;
		List wordFreq;
		
		String content = fd.ReadFile(file);
		WordDeal wd = new WordDeal(content);
		
		// 调用类中的方法获取相应的数值
	    int charNum = wd.getCharCount();
		int wordCount = wd.getWordCount();
		int validLine = wd.getLineCount();
		wordFreq = wd.getWordFreq();
		
	}
}