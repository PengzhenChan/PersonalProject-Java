import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {

    private String inputFile;
    private String outputFile;
    int charNumber;
    int wordNumber;
    int lineNumber;

    public Lib() {
    }

    public Lib(String inputFile, String outputFile) {
        charNumber = 0;
        wordNumber = 0;
        lineNumber = 0;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String readFile() {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            int ch;
            while ((ch = reader.read()) != -1){
                builder.append((char)ch);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void charNumCount(){
        String str = readFile();
        charNumber = str.length();
    }

    public void wordNumCount(){
        String str = readFile();
        String[] words = str.split("[^(a-zA-Z0-9)]");
        Pattern wordPattern = Pattern.compile("[a-zA-Z]{4}[a-zA-Z0-9]*");
        for (String word:words){
            Matcher matcher = wordPattern.matcher(word);
            if(matcher.matches()){
                wordNumber++;
            }
        }
    }

    public void lineNumCount(){
        String str = readFile();
        String[] lines = str.split("\n");
        lineNumber = lines.length;
        Pattern linePattern = Pattern.compile("\\s*");
        for(String line : lines){
            Matcher matcher = linePattern.matcher(line);
            if (matcher.matches()){
                lineNumber--;
            }
        }
    }
}
