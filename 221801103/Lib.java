import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib
{
	public static BufferedReader GetFileInputStream(String fileName)
	{
		BufferedReader bufferedReader=null;
		try 
		{
			FileInputStream fileInputStream=new FileInputStream(fileName);
			InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
			bufferedReader=new BufferedReader(inputStreamReader);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return bufferedReader;
	}
	
	//ͳ���ļ����ַ������������ո�ˮƽ�Ʊ�������з�
	public static int CharsCount(String fileName)
	{
		int count=0;
		BufferedReader targetFile=GetFileInputStream(fileName);
		
		if(targetFile==null)
		{
			System.out.println("�Ҳ������ļ�Ŷ~");
			return 0;
		}
		
		int ch;
		try
		{
			while((ch=targetFile.read())!=-1)
			{
				if((ch>=65&&ch<=122)||ch==32||ch==10)
				{
					count++;
				}
			}
			targetFile.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;	
	}
	
	//ͳ�����µ�������
	public static List<Map.Entry<String, Integer>> WordCount(String fileName)
	{
		HashMap<String,Integer> wordHash=new HashMap<String,Integer>();
		BufferedReader targetFile=GetFileInputStream(fileName);
		
		if(targetFile==null)
		{
			System.out.println("�Ҳ������ļ�Ŷ~");
			return null;
		}
		
		StringBuilder builder = new StringBuilder();
	    try 
	    {
	        int c;
	        while ((c = targetFile.read()) != -1) 
	        {
	            builder.append((char) c);
	        }
	        targetFile.close();
	    } 
	    catch (IOException e)
	    {
	        e.printStackTrace();;
	    }
	    
	    String word;
	    String str=builder.toString();
		Pattern wordPattern = Pattern.compile("(^|[^A-Za-z0-9])([A-Za-z]{4}[A-Za-z0-9]*)");
		Matcher matcher=wordPattern.matcher(str);
		
		while(matcher.find())
		{
			word=matcher.group().toLowerCase();//ת��ΪСд
			if(wordHash.containsKey(word))
			{
				int num=wordHash.get(word);
				num++;
				wordHash.put(word, (Integer)num);
			}
			else
			{
				wordHash.put(word, 1);
			}
		}
		
		//��map.entrySet()ת����list
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordHash.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() 
		{
			//��������
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
			{
				//return o1.getValue().compareTo(o2.getValue());
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list;	
	}
	
	//ͳ���ļ�������
	public static int LineCount(String fileName)
	{
		int count=0;
		BufferedReader targetFile=GetFileInputStream(fileName);
		
		if(targetFile==null)
		{
			System.out.println("�Ҳ������ļ�Ŷ~");
			return 0;
		}
		
	    StringBuilder builder = new StringBuilder();
	    try 
	    {
	        int c;
	        while ((c = targetFile.read()) != -1) 
	        {
	            builder.append((char) c);
	        }
	        targetFile.close();
	    } 
	    catch (IOException e)
	    {
	        e.printStackTrace();;
	    }
	    
	    String str=builder.toString();
	    Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
	    Matcher matcher = linePattern.matcher(str);
	    while (matcher.find()) 
	    {
            count++;
        }
	    return count;
	}
	
	//����ļ�
	public static void OutputFile(String inputFileName,String outputFileName)
	{
		
		List<Map.Entry<String, Integer>> list=WordCount(inputFileName);
		
		try
		{
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(outputFileName));
			bufferedWriter.write("characters: "+CharsCount(inputFileName)+"\n");
			bufferedWriter.write("words: "+list.size()+"\n");
			bufferedWriter.write("Lines: "+LineCount(inputFileName)+"\n");
			for(Entry<String, Integer> listItem:list)
			{
				bufferedWriter.write(listItem.getKey()+":  "+listItem.getValue()+"\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
