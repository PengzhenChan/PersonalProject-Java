package wordcount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*统计词频
 * 要求
 	* 1. 统计文件的字符数（对应输出第一行）：
 	* • 只需要统计Ascii码，汉字不需考虑
 	* • 空格，水平制表符，换行符，均算字符
 	* 2. 统计文件的单词总数（对应输出第二行），单词：至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写。
 	* • 英文字母： A-Z，a-z
 	* • 字母数字符号：A-Z， a-z，0-9
 	* • 分割符：空格，非字母数字符号
 	* • 例：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词
 	* 3. 统计文件的有效行数（对应输出第三行）：任何包含非空白字符的行，都需要统计。
 	* 4. 统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个。
 	* • 频率相同的单词，优先输出字典序靠前的单词。
 	* 例如，windows95，windows98和windows2000同时出现时，则先输出windows2000
 	* • 输出的单词统一为小写格式
*/
public class Count_Word{
	// 使用HashMap来存储单词的频率
	Map<String, Integer> wordCount = new HashMap<>();
       
	public static void main(String[] args) throws Exception {
		//用户输入要读取的文件路径（docin），如：E://1.txt
		//用户输入要输出的文件路径（docout），如：E://2.txt
		Scanner in = new Scanner(System.in);	
		String docin = in.nextLine();
		String docout = in.nextLine();
		
		//需要读取的文件和输出流的初始化
	        File file = new File(docin);
	        BufferedWriter out = new BufferedWriter(new FileWriter(docout));
		
		//统计字符数和单词数
		FileReader fr = new FileReader(file);   
	        BufferedReader bfr = new BufferedReader(fr);
	 		char ch;
	 		char fch='A';
	 		//字符数
	 		int countc = 0;
	 		//英文单词数
	 		int countw = 0;
	 		//按字符读取文本内容
	 		while((ch = (char) bfr.read()) != (char)-1)
	 		{
	 			//统计文本中字符数
	 			if(ch != '\n' && ch != '\r') 
	 				//累计字符数
	 				countc++;
	 			if(!(ch>='a'&&ch<='z')&&!(ch>='A'&&ch<='Z')&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z')))
	 			{
	 				//累计单词数
	 				countw++;
	 			}
	 			fch=ch;
	 		}
                       
		        /*然后将统计结果输出到指定文件，输出的格式如下；
	 		 * 其中word1和word2 对应具体的单词，number为统计出的个数；换行使用'\n'，编码统一使用UTF-8。
	 		 * characters: number
	 		 * words: number
	 		 * lines: number
	 		 * word1: number
	 		 * word2: number
	 		 * ...
                         */
	 		out.write("characters:  "+countc);
	 		out.newLine();
	 		out.write("words: "+countw);
	 		out.newLine();
	 		//System.out.println("characters:  "+countc);
	 		//System.out.println("words: "+countw);
		         
		         //统计文件的行数
	 		FileReader fr1 = new FileReader(file);
	 		LineNumberReader lnr = new LineNumberReader(fr1);
	 		int linenumber = 0;
	 		while (lnr.readLine() != null){
        	        linenumber++;
	                }
	 		
	 		out.write("lines:  " + linenumber);
	 		out.newLine();
	 		//System.out.println("Total number of lines : " + linenumber);
	 		lnr.close();
		  
                        //把文件中所有大写字母转换为小写
                        toLower(docin);
                        HashMap<String, Integer> map = (HashMap<String, Integer>) new Count_Word()
				.wordCount(docin);
		
			// 将单词按照次数从高到低排序
			List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>();
			list.addAll(map.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			// 从高往低排序
			public int compare(Map.Entry obj1, Map.Entry obj2) {
				if (Integer.parseInt(obj1.getValue().toString()) < Integer
					.parseInt(obj2.getValue().toString()))
					return 1;
				if (Integer.parseInt(obj1.getValue().toString()) == Integer
					.parseInt(obj2.getValue().toString()))
					return 0;
				else
					return -1;
				}
			});
                      
			// 将出现频率最高的前十个单词写入文件
			int i = 0;
			for (Iterator<Map.Entry<String, Integer>> ite = list.iterator(); i < 10; i++) {
				Map.Entry<String, Integer> maps = ite.next();
				out.write(maps.getKey() + ": " + maps.getValue());
				out.newLine();
				//System.out.println(maps.getKey() + "\t" + maps.getValue());
			}
			out.close();
			//打印出所有信息 
			//for (Iterator<Map.Entry<String, Integer>>ite =list.iterator(); ite .hasNext();)
			//{ 
			//	Map.Entry<String, Integer> maps = ite.next(); 
			//  System.out.println(maps.getKey() + "\t" +maps.getValue()); 
			//} 
			}

		//把文件中所有单词转化为小写的函数
		public static void toLower(String file) throws Exception{
			Reader myReader = new FileReader(file);
			Reader myBufferedReader = new BufferedReader(myReader);
			CharArrayWriter  tempStream = new CharArrayWriter();
			int i = -1;
			do {
			tempStream.write(i);
			i = myBufferedReader.read();
			if(i >= 65 && i <= 90){
				i += 32;
			}
			}while(i != -1);
			myBufferedReader.close();
			Writer myWriter = new FileWriter(file);
			tempStream.writeTo(myWriter);
			tempStream.flush();
			tempStream.close();
			myWriter.close();
		}

		//统计单词频率
		public Map<String, Integer> wordCount(String fileName) throws IOException {
		// 打开文件
			File file = new File(fileName);
			FileInputStream fis = null;		
			fis = new FileInputStream(file);
			// 英文单词以空格为分隔符，将单词分隔，并将所有大写字母转换为小写
			BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
			String s = null;
		
			while ((s = bufr.readLine()) != null) {
				// 移除字符串的前导空白和后尾部空白
				s = s.trim();
				// 正则表达式：以非字母或者是数字为分隔符，进行分割
				String[] str = s.split("(\\s+\\W+)|[\\s+\\W+]");
				for (int i = 0; i < str.length; i++) {
					// 如果HashMap中已有该值,将值加1
					if (wordCount.containsKey(str[i])) {
						wordCount.put(str[i], wordCount.get(str[i]) + 1);
					} else {
						// 默认初始化该单词的出现次数为1
						wordCount.put(str[i], 1);
					}
				}
			}		
			// 移除HashMap中的""空字符串
			wordCount.remove("");
			return wordCount;
		}
	}
