
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class WordCount {
	static Reader char_read;
	static Writer char_writer;
	public static void main(String[] args) throws Exception {
		
		File inputfile=new File(args[0]);
		File outfile = new File(args[1]);
		if(!inputfile.exists()) {
			inputfile.createNewFile();
		}
		if(!outfile.exists()) {
			outfile.createNewFile();
		}
		char_read=new FileReader(inputfile);
	    char_writer = new FileWriter(outfile);
	    StringBuilder res = new StringBuilder();
		int c;
		while((c = char_read.read()) != -1) {
			res.append((char)c); //获取文本内容
		}
		Calcurate.CharCount(res.toString().toLowerCase(), char_writer);
		Calcurate.WordsCount(res.toString().toLowerCase(), char_writer);
		Calcurate.LineCount(res.toString().toLowerCase(), char_writer);
		Calcurate.WordSort(res.toString().toLowerCase(), char_writer);
		char_writer.close();
	}
	
}
