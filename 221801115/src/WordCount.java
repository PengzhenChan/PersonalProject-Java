import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WordCount {
    private static String inputPath;/*输入路径*/
    private static String outputPath;/*输出路径*/
	public static void main(String[] args) {
        inputPath = "C:\\Users\\Lin Minghao\\Desktop\\" + args[0];
        outputPath = "C:\\Users\\Lin Minghao\\Desktop\\" + args[1];
        countCharacters(inputPath);
        writeFile(outputPath);
	}

    public static int countCharacters(String inputPath) {
        int characters = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            while ((br.read()) != -1) {
                characters++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return characters;
    }

    public static void writeFile(String outputPath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));
            bw.write("characters: " + countCharacters(inputPath));
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}