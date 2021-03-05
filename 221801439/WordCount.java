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
			}			
			System.out.println(lines);
			sb.append("lines:" + lines + "\n");
			
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
	
}
