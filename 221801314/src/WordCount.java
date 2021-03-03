import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount{

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];

        Long startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = Lib.FileIOUtil.readFile(inputPath);
        Lib.TextEditor textEditor = new Lib.TextEditor(bufferedReader);

        textEditor.readString();
        stringBuilder.append("characters: ").append(textEditor.countAscii()).append('\n');
        stringBuilder.append("words: ").append(textEditor.countWords()).append('\n');
        stringBuilder.append("lines: ").append(textEditor.countRows()).append('\n');
        stringBuilder.append(textEditor.countTopWords());
        Lib.FileIOUtil.writeFile(outputPath,stringBuilder.toString());
        Long endTime = System.currentTimeMillis();
        System.out.println("运行用时: " + (endTime - startTime) + "ms");

//        try {
//            Lib.FileIOUtil.bigDateTest("input.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}