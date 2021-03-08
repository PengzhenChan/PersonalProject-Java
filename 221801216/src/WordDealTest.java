import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class WordDealTest 
{

	

	@Before
	public void setUp() throws Exception 
	{
		
	}

	//ͳ���ַ���������
	@Test
	public void testGetCharCount() throws IOException 
	{
		String textString="";
		String text1 = "abcd8.+*1\n";	
		int loop=100;
		for(int i=0;i<loop;i++)
			textString+=text1;
		WordDeal wd1 = new WordDeal(textString);
		int cn1 = wd1.getCharCount();
		assertEquals(1000, cn1);
	}
	
	//ͳ�Ƶ�����������
	@Test
	public void testGetWordCount() throws IOException 
	{
		String text1 = "";
		String str[]={"word1","word2","file","���","123file","file123","fil123"};
		//���Դ��ı�����
		for(int j=0;j<10000;j++)
		{
			for(int i=0;i<str.length;i++)
			{
				text1+=str[i]+" ";
			}
		}
		WordDeal wd1 = new WordDeal(text1);
		int wn1 = wd1.getWordCount();
		assertEquals(40000, wn1);
	}


	//ͳ����Ч��������
	@Test
	public void testGetLineCount() throws IOException
	{
		String text1="abcd\r\n222\r\n\r\n\r\n\r\n3123\r\n\r\n";
		WordDeal wd1 = new WordDeal(text1);
	    int wn2 = wd1.getLineCount();
	    assertEquals(3, wn2);
	}

	//ͳ����ߴ�Ƶ
	@Test
	public void testListToArray() throws IOException 
	{
		String text1="";
		String strs[]= {"word1","word2","word3","word4","word5","word5","word6","word7",
				"word8","word9","word0"};
		for(int i=0;i<strs.length;i++)
		{
			text1+=strs[i]+" ";
		}
		WordDeal wd1 = new WordDeal(text1);
		List wf1 = wd1.getWordFreq();
		String[] s1 = wd1.ListToArray(wf1);
	}
	
	
}