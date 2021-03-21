package workFunc;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import basic.basic;
import tool.IOF;
import tool.countF;

public class working_set {
	
	//getNumberOfWords()所需的所有变量
	
	public static Map<String,Integer> wordSet = new TreeMap<String,Integer>();
	public static String outPath;//输出文件位置
	public static String inPath;//输入文件位置
	public static int sumC = 0;//用于统计字符数
	public static int sumW = 0;//用于统计单词数
	public static int sumR = 0;//用于统计有效行数
	public static int numN = 0;//统计\n个数
	public static boolean openFileFalse = false;
	
	//其他函数所需变量

	public static void getNumberOfWords() throws IOException {
		/**
		 * 
		 * 函数功能
		 * 统计排名前十的单词数量
		 * 单词:至少四位的以数字结尾，字母开头的，由字母数字组成的
		 */
//			basic.scanIn();
			//文件读入
			IOF.fileRead();
			//最后一行是否有换行符或回车
			if(IOF.hasEnterLastLine())
				numN++;
			//重新统计char数量，将换行符也算入
			sumC = countF.allCh(sumC, numN);
			//打印输出
			IOF.print();
		}
}
