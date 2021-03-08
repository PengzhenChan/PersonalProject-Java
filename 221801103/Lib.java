import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
	
	//统计文件的字符个数，包括空格，水平制表符，换行符
	public static int CharsCount(String fileName)
	{
		int count=0;
		BufferedReader targetFile=GetFileInputStream(fileName);
		
		int ch;
		try
		{
			while((ch=targetFile.read())!=-1)
			{
				if(ch>=0&&ch<=127)
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
	
	//统计文章单词数量
	public static List<Map.Entry<String, Integer>> WordCount(String fileName)
	{
		HashMap<String,Integer> wordHash=new HashMap<String,Integer>();
		BufferedReader targetFile=GetFileInputStream(fileName);
			
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
			word=matcher.group(2).toLowerCase();//转化为小写
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
		
		//java8特性，利用stream.sorted()进行排序
		wordHash = wordHash.entrySet().stream()
	            .sorted(
	                Map.Entry.<String, Integer>comparingByValue()
	                .reversed()
	                .thenComparing(Map.Entry.comparingByKey())).limit(10)
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		//将map.entrySet()转换成list
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordHash.entrySet());
		/*Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() 
		{
			//降序排序
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
			{
				//return o1.getValue().compareTo(o2.getValue());
				return o2.getValue().compareTo(o1.getValue());
			}
		});*/
		return list;	
	}
	
	//统计文件的行数
	public static int LineCount(String fileName)
	{
		int count=0;
		BufferedReader targetFile=GetFileInputStream(fileName);
		
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
	
	//输出文件
	public static void OutputFile(String inputFileName,String outputFileName)
	{
		int wordSum=0;
		
		List<Map.Entry<String, Integer>> list=WordCount(inputFileName);
		for(Entry<String, Integer> listItem:list)
		{
			wordSum+=listItem.getValue();
		}
		
		try
		{
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(outputFileName));
			bufferedWriter.write("characters: "+CharsCount(inputFileName)+"\n");
			bufferedWriter.write("words: "+wordSum+"\n");
			bufferedWriter.write("Lines: "+LineCount(inputFileName)+"\n");
			for(Entry<String, Integer> listItem:list)
			{
				bufferedWriter.write(listItem.getKey()+":  "+listItem.getValue()+"\n");
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
