import java.io.File;
import java.io.FileInputStream;

public class WordCount {

	/*
	 * ���ܣ���ȡ�ļ�����
	 * ������Դ�ļ�·��
	 * ����ֵ���ļ�����
	 */
	public static String readFile(String sourceFile) {
		String encoding = "UTF-8";
		File file = new File(sourceFile);
		long fileLength = file.length();
		System.out.print(fileLength);
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
