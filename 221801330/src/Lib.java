import java.io.*;
import java.util.*;

public class Lib {
	private int charSum;
	private int wordSum;
	private int lineSum;
	private HashMap<String, Integer> wordFrequency;
	
	Lib(){
		charSum = 0;
		wordSum = 0;
		lineSum = 0;
		wordFrequency = new HashMap<String,Integer>();
	}
	
	void SetCharSum(int n) {
	    charSum = n;
	}
	
	int GetCharSum() {
		return charSum;
	}
	
	void SetWordSum(int n) {
		wordSum += n;
	}

	int GetWordSum() {
		return wordSum;
	}
	
	void SetLineSum() {
	    lineSum++;
	}

	void SetLineSum(int n) {
		lineSum += n;
	}

	int GetLineSum() {
		return lineSum;
	}
	
	void SetWordFrequency(HashMap<String, Integer> hash) {
		for (String s : hash.keySet()) {
			if (wordFrequency.containsKey(s)) {
				wordFrequency.put(s, wordFrequency.get(s) + hash.get(s));
			}
			else {
				wordFrequency.put(s, 1);
			}
		}
	}

	List<Map.Entry<String, Integer>> GetWordFrequency() {
		List<Map.Entry<String, Integer>> list = null;
		list = new ArrayList<Map.Entry<String, Integer>>(wordFrequency.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (o1.getValue() != o2.getValue()) {
					return o2.getValue().compareTo(o1.getValue());
				}
				else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
		return list;
	}
}
