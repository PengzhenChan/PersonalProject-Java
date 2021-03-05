import java.io.IOException;
import java.util.*;

public class WordCount
{

	public static void main(String[] args) throws IOException 
	{		
		
		if(args.length != 2)
        {
            System.out.print("程序只接受两个参数");
            return;  //退出程序
        }
        
		
		//定义所需数据结构

		String infile = args[0];
		String outfile = args[1]; 
		FileDeal fd = new FileDeal();
		String[] wFreq;
		List wordFreq;
		
		String content = fd.ReadFile(infile);
		WordDeal wd = new WordDeal(content);
		
		// 调用类中的方法获取相应的数值
	    int charNum = wd.getCharCount();
		int wordCount = wd.getWordCount();
		int validLine = wd.getLineCount();
		wordFreq = wd.getWordFreq();
		
		wFreq = wd.ListToArray(wordFreq);
		String w = "characters:"+charNum+ "\n" +"words:"+ wordCount + "\n" + "lines:"+validLine + "\n";
		for (int i = 0; i < wFreq.length; i++) 
		{
			w = w + wFreq[i] + "\n";
		}
		System.out.println(w);
		fd.WriteToFile(w,outfile);
	}
}