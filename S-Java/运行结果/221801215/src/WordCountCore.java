import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 单词处理程序的核心模块
 */
public class WordCountCore {
    /**
     * 统计文件字符数
     *
     * @param inputFile the input file 需要统计的文件
     * @return the int 文件的ASCII字符数
     */
    public int countChar(File inputFile) {
        AsciiCharCounter asciiCharCounter = new AsciiCharCounter();
        return asciiCharCounter.countAsciiChar(inputFile);
    }

    /**
     * 统计单词数
     *
     * @param inputFile the input file 需要统计的文件
     * @return the int 文件的合法单词数
     */
    public int countWord(File inputFile) {
        WordProcessor wordProcessor = new WordProcessor();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(inputFile),Config.CHARSET))) {
            int currentChar;
            while ((currentChar = bufferedReader.read()) != -1) {
                if (wordProcessor.buildPossibleWord((char)currentChar,false)) {
                    if (wordProcessor.allWordSumUp()) {
                        wordProcessor.individualWordSumUp(wordProcessor.possibleWord.toString());
                    }
                }
            }
            if (wordProcessor.buildPossibleWord((char)currentChar,true)) {
                if (wordProcessor.allWordSumUp()) {
                    wordProcessor.individualWordSumUp(wordProcessor.possibleWord.toString());
                }
            }
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
        return wordProcessor.getWordSum();
    }

    /**
     * 统计最多的10个单词及其词频
     *
     * @param inputFile the input file 需要统计的文件
     * @return the list 包含最多10个单词及其词频
     */
    public List<Map.Entry<String,Integer> > countTopTenWordWithSum(File inputFile) {
        WordProcessor wordProcessor = new WordProcessor();
        List<Map.Entry<String, Integer>> sortedWordCountList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(inputFile),Config.CHARSET))) {
            int currentChar;
            while ((currentChar = bufferedReader.read()) != -1) {
                if (wordProcessor.buildPossibleWord((char)currentChar,false)) {
                    if (wordProcessor.allWordSumUp()) {
                        wordProcessor.individualWordSumUp(wordProcessor.possibleWord.toString());
                    }
                }
            }
            if (wordProcessor.buildPossibleWord((char)currentChar,true)) {
                if (wordProcessor.allWordSumUp()) {
                    wordProcessor.individualWordSumUp(wordProcessor.possibleWord.toString());
                }
            }
            sortedWordCountList= wordProcessor.getSortedWordCountList();
            sortedWordCountList = sortedWordCountList.size()>10?sortedWordCountList.subList(0,10)
                    : sortedWordCountList;
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
        return sortedWordCountList;
    }
}
