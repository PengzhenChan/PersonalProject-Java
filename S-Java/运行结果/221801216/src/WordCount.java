import java.io.IOException;
import java.util.*;

public class WordCount
{

	public static void main(String[] args) throws IOException 
	{		
		
		if(args.length != 2)
        {
            System.out.print("����ֻ������������");
            return;  //�˳�����
        }
        
		
		//�����������ݽṹ

		String infile = args[0];
		String outfile = args[1]; 
		FileDeal fd = new FileDeal();
		String[] wFreq;
		List wordFreq;
		
		String content = fd.ReadFile(infile);
		WordDeal wd = new WordDeal(content);
		
		// �������еķ�����ȡ��Ӧ����ֵ
	    int charNum = wd.getCharCount();
		int wordCount = wd.getWordCount();
		int validLine = wd.getLineCount();
		wordFreq = wd.getWordFreq();
		
		wFreq = wd.ListToArray(wordFreq);
		String w = "characters: "+charNum+ "\n" +"words: "+ wordCount + "\n" + "lines: "+validLine + "\n";
		for (int i = 0; i < wFreq.length; i++) 
		{
			w = w + wFreq[i] + "\n";
		}
		System.out.println(w);
		fd.WriteToFile(w,outfile);
	}
}