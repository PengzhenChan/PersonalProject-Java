package test;

import java.io.*;
import java.util.regex.Matcher; //正则表达式
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;

public class tt{
	
	public static void main(String[] args) throws IOException {
		int characters=0;  
		int lines = 0;    
		int word = 0;
		long startTime=System.currentTimeMillis();
		File file = new File("src/test/input.txt");
		BufferedReader reader = null;
		try{
			InputStreamReader read = new InputStreamReader( new FileInputStream(file),"utf-8");
			reader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String str = null;
			while((str = reader.readLine())!=null){
				if(!str.equals(""))
				{
					lines++;			
				}	
				characters += countCharacters(str);  //对字符统计数累加
				word += countWords(str);  //对单词统计数累加
			}	
			System.out.println(characters);
			sb.append("characters:" + characters + "\n");
			System.out.println(lines);
			sb.append("lines:" + lines + "\n");
			System.out.println(word);
			sb.append("word:" + word + "\n");
			
			reader.close();
			FileWriter fw = new FileWriter("src/test/output.txt");  //写入文件
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
		long Time=endTime-startTime;
		System.out.println("耗时:"+Time+"毫秒");
	}   
	
	/**
	 * 统计字符数
	 * @param str
	 * @return count
	 */
    public static int countCharacters(String str) 
    {
        int count = 0;
        Pattern p = Pattern.compile("[\\x00-\\x7F]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }
    
    /**
  	 * 统计单词数
  	 * @param str
  	 * @return count
  	 */
      public static int countWords(String str)
      {
      	 int count = 0;
      	 String ss = str.toLowerCase();
      	 Pattern p = Pattern.compile("[a-z]{4}[0-9a-z]*");    	 
           Matcher m = p.matcher(ss);
           while(m.find())
           {
               count++;
           }
  		return count;
      }
	
}
