import java.io.*;
import java.util.*;

public class WordCount {
    private static int countChars = 0;
    private static int countWords = 0;
    private static int countLines = 0;
    private static Map<String,Integer> map;
    private static List<Map.Entry<String,Integer>> lstEntry; 
    private String text;
	
    public WordCount(String fileName) throws IOException {
        map = new HashMap<String, Integer>();
        text = readFile(fileName);
        countChars = Lib.countChars(text);
        countWords = Lib.countWords(text, map);
        countLines = Lib.countLines(text);
	lstEntry = new ArrayList<>(map.entrySet());
	lstEntry=Lib.getSortedList(map);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2){
	    System.out.println("wrong");
	    return;
	}
	WordCount wordCount = new WordCount(args[0]);
	    writeFile(args[1]); 
	}
	
    public static String readFile(String path) throws IOException {
	BufferedReader br = null;
	StringBuffer textBuffer = new StringBuffer();
	String content = null;
	int s;
	try {
	    br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
	    while ((s = br.read()) != -1)         
	        textBuffer.append((char)s);			
		content = textBuffer.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	    	br.close();
	    }
	    return content;
	}

    public static void writeFile(String Path) throws IOException {
	BufferedWriter writer = new BufferedWriter(new FileWriter(Path));
	StringBuilder str = new StringBuilder("characters: " + countChars + '\n' + "words: " + countWords + '\n'
	               + "lines: " + countLines + '\n');
	for(Map.Entry<String, Integer> m:lstEntry)
            str.append(m.getKey()).append(": ").append(m.getValue()).append("\n");
	writer.write(str.toString());
	writer.close();
    }
	
}
