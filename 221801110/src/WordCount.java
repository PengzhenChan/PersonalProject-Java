import java.io.*;

public class WordCount {

    public static void main(String[] args){
        /*
        int a = Lib.numOfChar("d:\\1.txt");
        System.out.println("characters: "+a);

        int b =Lib.numOfWord("d:\\1.txt");
        System.out.println("words: "+b);

        int c = Lib.numOfLine("d:\\1.txt");
        System.out.println("lines: "+c);

        Frequency d = new Frequency("d:\\1.txt");

        */

        if (args.length == 0 || args.length == 1){
            System.err.println("��������д�");
        }

        String input = args[0];
        String output = args[1];

        int charNum = Lib.numOfChar(input);
        int wordNum =Lib.numOfWord(input);
        int lineNum = Lib.numOfLine(input);

        String frequencyOfWords = new Frequency(input).result;
        File outputFile = new File(output);
        //FileOutputStream fileOutputStream = null;
        try {
            //fileOutputStream = new FileOutputStream(outputFile);
            FileWriter fileWriter = new FileWriter(output,true);
            fileWriter.write("characters: " + charNum + '\n');
            fileWriter.write("words: " + wordNum + '\n');
            fileWriter.write("lines: " + lineNum + '\n');
            fileWriter.write(frequencyOfWords);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e) {
            System.err.println("�ļ�" + output + "�Ҳ���");
        }
    }
}
