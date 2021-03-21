import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WordCount {
	public static void main(String[] args) {
		int num1 = CharactersCount.charactersCount(args[0]);
		int num2 = WordsCount.wordsCount(args[0]);
		int num3 = LinesCount.linesCount(args[0]);
		FrequentWordsCount.frequentWordsCount();
		File file = new File(args[1]);
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			fw.write("characters��" +num1 + "\n" + 
						"words��" +num2 + "\n" + 
						"lines��" +num3 + "\n");
			for (int i = 0;i<10;i++) {
				if (FrequentWordsCount.frequentWords[i] != null)
					fw.write(FrequentWordsCount.frequentWords[i] + "��" + FrequentWordsCount.num[i] + "\n");
			}			
			fw.flush();
			fw.close();			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}