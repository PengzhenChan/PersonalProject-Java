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
    
    public int isWord(String word) {    //用来判断这个字符串是否为单词
		if (word.length()<4)
			return -1;
		for (int i=0;i<4;i++){
			char c = word.charAt(i);
			if (!(c>='a'&&c<='z'||c>='A'&&c<='Z'))
				return -1;
		}
		return 1;
	}
    
    public void mapValueSort(HashMap<String, Integer> map) {    //将hashMap转化为list，使用其封装的sort函数进行排序
        list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2){   //重写compare函数，复杂度为nlog2（n），先按照value从大到小，
                if (o1.getValue()<o2.getValue())                                           //value相等的情况下，再按照key从小到大。
                	return 1;
                else if (o1.getValue()>o2.getValue())
                	return -1;
                else {
                	return o1.getKey().compareTo(o2.getKey());
                }
            }
        });
    }
    
    public void calculate() throws FileNotFoundException    //进行文章统计计算,得出单词统计的hashMap，并进行排序
	{
		File file=new File(inputTxt);
		if(!file.exists())
		{
			System.out.println("文件不存在");
			return;
		}
		Scanner scanner = new Scanner(file);
		hashMap = new HashMap<String, Integer>();
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			if (!line.matches("\\s*"))
				wordlines++;
			characters += line.length();
			characters ++;  //多加换行符一个字符
			anticle += line;
			//\w+ : 匹配所有的单词
			//\W+ : 匹配所有非单词
			String[] lineWords=line.split("\\W+");    //用非单词符来做分割,网上找的知识点	
			Set<String> wordSet=hashMap.keySet();
			
			for (int i=0;i<lineWords.length;i++) {    //将不是英文的字符串替换成特定字符串，并将所以字段转化为小写
				lineWords[i] = lineWords[i].toLowerCase();
				//System.out.println(lineWords[i]);
				if (isWord(lineWords[i])==-1)
					lineWords[i] = "221801106";
			}  
			
			for(int i=0;i<lineWords.length;i++){
				if(wordSet.contains(lineWords[i])){    //如果已经有这个单词了
					Integer number=hashMap.get(lineWords[i]);
					number++;
					hashMap.put(lineWords[i], number);
				}
				else{    //如果没有包括这个单词
					hashMap.put(lineWords[i], 1);  
				}
			}		
		}
		
		hashMap.remove("");    //将字段里面的空字符串去掉
		hashMap.remove("221801106");    //将不是单词的字符段去掉
		mapValueSort(hashMap);
		scanner.close();
	}
}

