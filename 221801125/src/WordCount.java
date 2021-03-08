import java.io.File;
import java.io.IOException;
import workFunc.working_set;

public class WordCount {
	public static void main(String[] args) throws IOException {
		//调用统计单词数的函数
		working_set.inPath = new File("input.txt").getAbsolutePath();
		working_set.outPath = new File("output.txt").getAbsolutePath();
		working_set.getNumberOfWords();
	}
}
