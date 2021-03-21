import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount{
    int charsNum, wordsNum, linesNum;
    String topsStr = "";

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];


        WordCount wordCount = new WordCount();
        Long startTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = Lib.FileIOUtil.readFile(inputPath);
        Lib.TextEditor textEditor = new Lib.TextEditor(bufferedReader);

//        try {
//            Lib.FileIOUtil.bigDateTest();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        textEditor.readString();

        Thread charThread, wordsThread, topThread;
        charThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                wordCount.charsNum = textEditor.countAscii();
                wordCount.charsNum = Lib.Core.getCharsNum(textEditor);
            }
        });
        wordsThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                wordCount.wordsNum = textEditor.countWords();
                wordCount.wordsNum = Lib.Core.getWordsNum(textEditor);
                wordCount.linesNum = textEditor.countLines();
//                wordCount.linesNum = Lib.Core.getLinesNum(textEditor);
            }
        });
        topThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                wordCount.topsStr = textEditor.countTopWords();
                wordCount.topsStr = Lib.Core.getTopWords(textEditor);
            }
        });
        charThread.start();
        wordsThread.start();
        topThread.start();
        try {
            charThread.join();
            wordsThread.join();
            topThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stringBuilder.append("characters: ").append(wordCount.charsNum).append('\n');
        stringBuilder.append("words: ").append(wordCount.wordsNum).append('\n');
        stringBuilder.append("lines: ").append(wordCount.linesNum).append('\n');
        stringBuilder.append(wordCount.topsStr);
        Lib.FileIOUtil.writeFile(outputPath,stringBuilder.toString());
        Long endTime = System.currentTimeMillis();
        System.out.println("运行用时: " + (endTime - startTime) + "ms");


    }
}