import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class WordCount{

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];

        Long startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        Lib.FileIOUtil fileIOUtil = new Lib.FileIOUtil();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Lib.TextEditor textEditor = new Lib.TextEditor(bufferedReader);
//        Lib.TextEditor textEditor = new Lib.TextEditor(fileIOUtil.openReadStream(inputPath));

        textEditor.readString();
        stringBuilder.append("characters: ").append(textEditor.countAscii()).append('\n')
                .append("words: ").append(textEditor.countWords()).append('\n')
                .append("lines: ").append(textEditor.countRows()).append('\n');
        stringBuilder.append(textEditor.countTopWords());
        fileIOUtil.writeFile(outputPath,stringBuilder.toString());
        Long endTime = System.currentTimeMillis();
        System.out.println("运行用时: " + (endTime - startTime) + "ms");

//        fileIOUtil.closeRead/Stream();
    }
}