import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CharactersCount {
	public static int charactersCount(String filename) {
        File file = new File(filename);
        int cnt = 0;//总行数
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int temp;
            while ((temp = br.read()) != -1) {
            	cnt ++;
            }
            br.close();
        } catch(Exception e) {
        	System.out.println("文件不存在！");
        }
        return cnt;
    }
}