import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class OperateFile {
	/*
	 * 功能：读取文件内容
	 * 参数：源文件路径
	 * 返回值：文件内容
	 */
	public String readFile(String sourceFile) {
		String encoding = "UTF-8";
		File file = new File(sourceFile);
		long fileLength = file.length();
		byte[] fileContent = new byte[(int)fileLength];
		try {
			FileInputStream in = new FileInputStream(file);
		    in.read(fileContent);
		    in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return new String(fileContent,encoding);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 统计信息并写入文件
	 */
	public void statisticsAndWrite(String targetFile, String words) {
		File file = new File(targetFile);
		CountCore cc = new CountCore();
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file),
					Charset.forName("UTF-8").newEncoder());
			writer.write("characters: " + cc.getCharCount(words) + "\n");
			writer.write("words: " + cc.getWordCount(words) + "\n");
			writer.write("lines: " + cc.getRowCount(words) + "\n");
			LinkedHashMap<String, Integer> list = new CountCore().getMaxWord(words);
			Set<Map.Entry<String, Integer>> set = list.entrySet();
			Iterator<Entry<String, Integer>> it = set.iterator();
			while (it.hasNext()) {
				Map.Entry<String, Integer> entry= (Map.Entry<String, Integer>)it.next();
				writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
				
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
