import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class WordsCount {
	public static String[] words = new String[1000];//单词数组 
	public static int wordsCount(String filename) {
		File file = new File(filename);
		String[] str = new String[1000];//仅包含字母和数字的字符串数组			
		int cnt = 0;//筛选出的字符串个数
		int num = 0;//单词数
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String tempLine;
			while ((tempLine = br.readLine()) != null) {
				if (!tempLine.equals("")) {
					/*String[] newStr = tempLine.split("\\s+|\\W+");//分割成数组
					for(String ss : newStr) {
						if (isLetterDigit(ss)) {
							str[cnt++] = ss;
						}*/
					Pattern pattern = Pattern.compile("\\s+|\\W+");
					String[] newStr = pattern.split(tempLine);
					for(String ss : newStr) {
						if (isLetterDigit(ss)) {
							str[cnt++] = ss;
						}
					}
				}
			}
			for (String ss : str) {
				if (isWord(ss)) {
					words[num++] = ss;
				}
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();//System.out.println("文件不存在！");
        }
		return num;
	}
	
	private static boolean isLetterDigit(String str) {//判断仅包含字母和数字的字符串
	    String regex = "^[a-z0-9A-Z]+$";
	    return str.matches(regex);
	}

	private static boolean isWord(String str) {//判断字符串是否为单词
		if (str != null&&str.length() >= 4) {//此处第一次写因为不知道要判断参数是否为空导致空指针异常，找了许多资料才明白
			char ch1 = str.charAt(0);
			char ch2 = str.charAt(1);
			char ch3 = str.charAt(2);
			char ch4 = str.charAt(3);		
			if (((ch1 >= 'A' && ch1 <= 'Z')||(ch1 >= 'a' && ch1 <= 'z'))
					&&((ch2 >= 'A' && ch2 <= 'Z')||(ch2 >= 'a' && ch2 <= 'z'))
					&&((ch3 >= 'A' && ch3 <= 'Z')||(ch3 >= 'a' && ch3 <= 'z'))
					&&((ch4 >= 'A' && ch4 <= 'Z')||(ch4 >= 'a' && ch4 <= 'z'))) 
				return true;
			else
				return false;
		}
		else 
			return false;
	}
}