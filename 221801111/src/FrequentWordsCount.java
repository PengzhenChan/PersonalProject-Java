import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FrequentWordsCount {
	public static void frequentWordsCount() {
		int size = WordsCount.words.length;
		Map<String,Integer> map = new HashMap<String,Integer>();//����map,key�����ַ���,value������ֵĴ���
		int cnt = 0;
		String[] words = new String[size];//��ͳ�Ƶĵ�������
		System.arraycopy(WordsCount.words, 0, words, 0, size);
		for (int i = 0; i < size; i++) {
			if (words[i] != null)
			words[i] = words[i].toLowerCase();
		}
		for (int i = 0; i < size; i++) {//����words����
			if (map.containsKey(words[i])) {
				map.put(words[i],map.get(words[i])+1);
			}
			else
				map.put(words[i],1);
		}		
	}    
}