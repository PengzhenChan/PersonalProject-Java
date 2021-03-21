import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FrequentWordsCount {
	public static String[] frequentWords = new String[10];
	public static int[] num = new int[10];
	public static void frequentWordsCount() {
		int size = WordsCount.words.length;
		Map<String,Integer> map = new HashMap<String,Integer>();//创建map,key保存字符串,value保存出现的次数
		int cnt = 0;
		String[] words = new String[size];//待统计的单词数组
		System.arraycopy(WordsCount.words, 0, words, 0, size);
		for (int i = 0; i < size; i++) {
			if (words[i] != null)
			words[i] = words[i].toLowerCase();
		}
		for (int i = 0; i < size; i++) {//遍历words数组
			if (map.containsKey(words[i])) {
				map.put(words[i],map.get(words[i])+1);
			}
			else
				map.put(words[i],1);
		}
		map = sortByValueDescending(map);
		for(Entry<String, Integer> vo : map.entrySet()) {
			if (vo.getKey() !=null) {
				frequentWords[cnt] = vo.getKey();
				num[cnt++] = vo.getValue();
			}
		}
	}

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = o1.getValue().compareTo(o2.getValue());
                return -compare;
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}