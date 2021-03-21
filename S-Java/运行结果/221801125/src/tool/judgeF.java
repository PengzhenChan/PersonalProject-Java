package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class judgeF {
	/**
	 * 有关判断的函数
	 * @param str
	 * @return
	 */
	
	//是否是单词
	public static boolean isWord(String str) {
		if(str.length()<4)
			return false;//字符数小于4
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(str);
		if(m.find())
			return false;//包含特殊字符
		if(Character.isDigit(str.charAt(0)))
			return false;//以数字开头不是
		if(Character.isLowerCase(str.charAt(str.length()-1))||Character.isUpperCase(str.charAt(str.length()-1)))
			return false;//以字母结尾不是
		for(int i=1;i<3;i++){
			if (Character.isLowerCase(str.charAt(i))||Character.isUpperCase(str.charAt(i))){
			    continue;  
			}
			else
				return false;//至少前四位数都是字母
		}
		if(Character.isDigit(str.charAt(str.length()-1)))
			return true;//满足以上条件且最后一位是数字，就是单词
		else
			return false;//常理走不到这里，但是需要满足函数规范
	}
	
}
