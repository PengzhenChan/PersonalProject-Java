
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Calcurate {
	
	//计算字符数
	public static void CharCount(String res, Writer char_writer) throws IOException{
		char_writer.write("characters: " + res.length() + "\n");
	}
	
	//计算单词数
	public static void WordsCount(String res, Writer char_writer) throws IOException {
		String[] words = res.split(" ");
		int count = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].matches("[a-z]{4}[a-z0-9]*")) count++;
		}
		char_writer.write("words: " + count + "\n");
    }
	
	//计算行数
    public static void LineCount(String res,  Writer char_writer) throws IOException {
    	String[] lines = res.split("\n");
    	char_writer.write("lines: " + lines.length + "\n");
    }
    
    //显示高频词汇
    public static void WordSort(String res, Writer char_writer) throws IOException {
    	String[] words = res.split("[^\\w\\d]+");
    	HashMap<String, Integer> map = new HashMap<String, Integer>();
    	for(int i = 0; i < words.length; i++) {
    		if(words[i].matches("[a-z]{4}[a-z0-9]*")) {
    			if(!map.containsKey(words[i])) map.put(words[i], 1);
        		else {
        			int value = map.get(words[i]) + 1;
            		map.put(words[i], value);
        		}
    		}
    		else continue;
    	}
    	 Set<Map.Entry<String, Integer>> entrySet = map.entrySet(); 
    	 PriorityQueue<Map.Entry<String, Integer>> list = new PriorityQueue<>(cmp); 
    	 for(Map.Entry<String, Integer> item: entrySet){
             list.add(item);
           }
    	 int count = 9;
    	while(!list.isEmpty()){
    		Map.Entry<String, Integer> item = list.poll();
            String key = item.getKey();
            Integer value = item.getValue();
            char_writer.write(key.toLowerCase() + ": " + value + "\n");
            if(count <= 0) break;
            else count--;
          }
    }
    static Comparator<Map.Entry<String, Integer>> cmp = new Comparator<Map.Entry<String, Integer>>() {
    	 @Override
    	    public int compare(Map.Entry<String, Integer> item1, Map.Entry<String, Integer> item2){
    		       if(item2.getValue().compareTo(item1.getValue()) == 0) {
    		    	   return item2.getKey().compareTo(item1.getKey());
    		       }
    	            return item2.getValue().compareTo(item1.getValue());    //降序排序
    	    }
    };
}
