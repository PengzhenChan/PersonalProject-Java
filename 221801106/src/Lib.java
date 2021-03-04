import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
 
public class Lib
{
	public int wordlines;   //用来记录行数
	public int characters;    //用来记录字符数
	public int wordnumbers;    //用来记录单词数
	public String anticle;    //用来保存从文件读入的全部内容
	public String inputTxt;    //写入文章的地址
	public String outputTxt;    //写出文章统计的地址
    public List<Map.Entry<String, Integer>> list;    //用来记录统计出来的频率排行
    public HashMap<String,Integer> hashMap;    //用来记录所有单词及在文章中出现的频率
    public String result;    //用来记录要输出的结果
    
    public Lib(String in,String out) {    //构建函数
		wordlines = 0;
		characters = 0;
		wordnumbers = 0;
		anticle = "";
		inputTxt = in;
		outputTxt = out;
		result = "";
	}
}

