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
