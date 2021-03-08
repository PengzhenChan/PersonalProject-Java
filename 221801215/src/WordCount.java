import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Word count.
 */
class  WordCount{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            processFile(new File(args[0]),new File(args[1]));
        }else {
            System.out.println(Config.COMMANDLINE_HELP);
        }
    }

    /**
     * 将统计结果输出到文件
     *
     * @param characters    the characters 文件的字符数
     * @param words         the words 单词总数
     * @param lines         the lines 有效行数
     * @param wordWithCount the word with count 最多的10个单词及其词频
     * @param outputFile    the output file 要输出的文件
     */
    private static void generateOutputFile(int characters, int words, int lines
            , List<Map.Entry<String,Integer> > wordWithCount, File outputFile) {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile,false),Config.CHARSET)))) {
            outputWriter.println("characters: " + characters);
            outputWriter.println("words: " + words);
            outputWriter.println("lines: " + lines);
            for (Map.Entry<String,Integer> wordPair : wordWithCount) {
                outputWriter.println(wordPair.getKey() + ": " + wordPair.getValue());
            }
            outputWriter.flush();
            System.out.println(Config.COMMANDLINE_SUCCESS_TIP);
        } catch (IOException ioException) {
            System.out.println(Config.IO_EXCEPTION_TIP);
            ioException.printStackTrace();
        }
    }
    public static void processFile(File inputFile,File outputFile) {
        int asciiCharCount = 0;
        AsciiCharCounter asciiCharCounter = new AsciiCharCounter();
        WordProcessor wordProcessor = new WordProcessor();
        EffectiveLineCounter effectiveLineCounter = new EffectiveLineCounter();
        List<Map.Entry<String, Integer>> sortedWordCountList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(inputFile),Config.CHARSET))) {
            int currentChar;
            while ((currentChar = bufferedReader.read()) != -1) {
                if (asciiCharCounter.isAsciiChar(currentChar)) {
                    asciiCharCount++;
                }
                effectiveLineCounter.countEffectiveLine((char)currentChar,false);
                if (wordProcessor.buildPossibleWord((char)currentChar,false)) {
                    if (wordProcessor.allWordSumUp()) {
                        wordProcessor.individualWordSumUp(wordProcessor.possibleWord.toString());
                    }
                }
            }
            effectiveLineCounter.countEffectiveLine((char)currentChar,true);
            if (wordProcessor.buildPossibleWord((char)currentChar,true)) {
                if (wordProcessor.allWordSumUp()) {
                    wordProcessor.individualWordSumUp(wordProcessor.possibleWord.toString());
                }
            }
            sortedWordCountList= wordProcessor.getSortedWordCountList();
            sortedWordCountList = sortedWordCountList.size()>10?sortedWordCountList.subList(0,10)
                    : sortedWordCountList;
            generateOutputFile(asciiCharCount,wordProcessor.getWordSum(),effectiveLineCounter.effectiveLineNumber
                    ,sortedWordCountList,outputFile);
        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println(Config.FILE_NOT_FOUND_EXCEPTION_TIP);
            fileNotFoundException.printStackTrace();
        }catch (UnsupportedEncodingException unsupportedEncodingException) {
            System.out.println(Config.UNSUPPORTED_ENCODING_EXCEPTION_TIP);
            unsupportedEncodingException.printStackTrace();
        } catch (IOException ioException) {
            System.out.println(Config.IO_EXCEPTION_TIP);
            ioException.printStackTrace();
        }
    }
}