import java.awt.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class WordCount {
	static Reader char_read;
	static Writer char_writer;
	public static void main(String[] args) throws Exception {
		
		File inputfile=new File("input.txt");
		File outfile = new File("output.txt");
		if(!inputfile.exists()) {
			inputfile.createNewFile();
		}
		if(!outfile.exists()) {
			outfile.createNewFile();
		}
		char_read=new FileReader(inputfile);
	    char_writer = new FileWriter(outfile);
		char[] readbuf = new char[1024];
				int c;
				int index = 0;
				while((c = char_read.read()) != -1) {
					readbuf[index++] = (char)c;
					
				}
	    String res = String.valueOf(readbuf).trim();
		CharCount(res);
		WordsCount(res);
		LineCount(res);
		WordSort(res);
		char_writer.close();
	}
	public static void CharCount(String res) throws IOException{
		char_writer.write("characters:" + res.length() + "\n");
		//char_writer.close();
	}
	public static void WordsCount(String res) throws IOException {
		String[] words = res.split("[^\\w\\d]+");
		char_writer.write("words:" + words.length + "\n");
    }
    public static void LineCount(String res) throws IOException {
    	String[] lines = res.split("\n");
    	char_writer.write("lines:" + lines.length + "\n");
    }
    public static void WordSort(String res) throws IOException {
    	String[] words = res.split("[^\\w\\d]+");
    	HashMap<String, Integer> map = new HashMap<String, Integer>();
    	for(int i = 0; i < words.length; i++) {
    		if(!map.containsKey(words[i])) map.put(words[i], 1);
    		else {
    			int value = map.get(words[i]) + 1;
        		map.put(words[i], value);
    		}
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
            char_writer.write(key + ":" + value + "\n");
            if(count <= 0) break;
            else count--;
          }
    }
    static Comparator<Map.Entry<String, Integer>> cmp = new Comparator<Map.Entry<String, Integer>>() {
    	 @Override
    	    public int compare(Map.Entry<String, Integer> item1, Map.Entry<String, Integer> item2){
    	            return item2.getValue().compareTo(item1.getValue());    //Ωµ–Ú≈≈–Ú
    	    }
    };
}
