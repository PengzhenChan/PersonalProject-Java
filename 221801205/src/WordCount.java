import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.FileWriter;

public class WordCount {

	public static void main(String[] args) {
		OperateFile of = new OperateFile();
		of.statisticsAndWrite(args[1], of.readFile(args[0]));
	}
}
