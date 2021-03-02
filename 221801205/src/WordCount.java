import java.io.File;
import java.io.FileInputStream;

public class WordCount {

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
	
	public static void main(String[] args) {
		
		
	}

}
