import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LinesCount {
	public static int linesCount(String filename) {
		File file = new File(filename);
		int cnt = 0;//������
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String tempLine;
			while ((tempLine = br.readLine()) != null) {
				cnt++;
				if (tempLine.equals(""))//�ж���Ϊ�հ��ַ�����
					cnt--;
			}
			br.close();
		} catch(Exception e) {
        	System.out.println("�ļ������ڣ�");
        }
		return cnt;
	}
}