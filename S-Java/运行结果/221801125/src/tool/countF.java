package tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import workFunc.working_set;
import basic.basic;

public class countF {	
	/**
	 * 
	 * 作为计数的核心代码
	 * 
	 * 1.返回字符数
	 * 2.统计行数
	 * 3.逐行传递map，统计单词数量
	 * 4.词频最多的单词
	 * 
	 */
	
	//用于返回字符数
	public static int countCh(String str){
		return str.length();
	}
	public static int allCh(int a,int n){
		return a+2*n;
	}
	
	//统计行数
	public static void countR(String[] s1){
		if(s1.length>0){
			if(basic.haveEnter(s1))
				working_set.sumR++;
			working_set.numN++;
		}
	}

	//逐行传递map
	public static HashMap<String,Integer> inLineFirst(String[] s) {
		//将传入的字符串数组排序，并返还一个合并好的map数组
		//统计单词总数
		HashMap<String,Integer> m = new HashMap<>();
		Arrays.sort(s);
		//将每一个数字都转为小写
		for(int i=0;i<s.length;i++)
			s[i] = s[i].toLowerCase();
		for(int i=0;i<s.length;i++){
			int count=1;
			//判断是否为单词
			if(judgeF.isWord(s[i])) {
				working_set.sumW++;
				while(i+count<s.length)
				{
					if(s[i].equals(s[i+count])) {
						count++;
						working_set.sumW++;
					}
					else
						break;
				}
				m.put(s[i], count);
				i = i + count - 1;
			}
		}
		return m;
	}
	
	//其余行map处理
	public static void addInMap(String[] s){
		//将传入字符串数组先转换为map，再同原来的大map合并
		//转换map
		Map<String,Integer> m = new TreeMap<String,Integer>();
		m.putAll(countF.inLineFirst(s));

		//统计，相同合并，不同加入
		for (String key : m.keySet()) {
			if(working_set.wordSet.containsKey(key)){
				int or = working_set.wordSet.get(key);
				int now = m.get(key);
				int n = or+now;
				working_set.wordSet.put(key, n);
	        }
	        else{
	        	working_set.wordSet.put(key, m.get(key));
	        }
	    }
	}
		

	//词频前十的单词（排序）
	public static ArrayList<Map.Entry<String, Integer>> sortMap(){
		//创建一个list用于存储map当中的内容
		ArrayList<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(working_set.wordSet.entrySet());
		//创建比较器
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
				return (o2.getValue() - o1.getValue()); 
			}
		}); 	
		return infoIds;
	}

}
