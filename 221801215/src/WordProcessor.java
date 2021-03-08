import java.util.*;

/**
 * 对单词校验、统计的类
 */
public class WordProcessor {
    private boolean isInPossibleWord = false;
    private final int INIT_WORD_MAX_LENGTH = 200;
    private boolean isPossibleWordAvailable;
    private Map<String,Integer> wordSumMap = new HashMap<>();

    /**
     * 单词-频数对比较器
     *
     * 降序
     * 比较标准：
     * 1.频数大的靠前
     * 2.单词字典序小的靠前
     */
    private final Comparator<Map.Entry<String,Integer> > wordComparator = new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {//s<t1返回正
            int result = t1.getValue() - stringIntegerEntry.getValue();
            if (result ==0 ) {
                result = stringIntegerEntry.getKey().compareTo(t1.getKey());
            }
            return result;
        }
    };

    /**
     * The Possible word.
     */
    public StringBuilder possibleWord;
    /**
     * The Word sum.
     */
    private int wordSum = 0;

    /**
     * 判断字符串是否符合单词要求
     *
     * <p>单词的判断标准：
     * 1.至少以4个英文字母开头
     * 2.跟上字母数字符号
     * 3.英文字母： A-Z，a-z，字母数字符号：A-Z， a-z，0-9
     * eg.
     * file123是一个单词， 123file不是一个单词。</p>
     *
     * @param stringBuilderToBeExam the stringBuilder 需要检测的字符串
     * @return the boolean 字符串检测的结果
     */
    public boolean isLegalWord(StringBuilder stringBuilderToBeExam) {
        boolean isLegal = true;
        int wordLength = stringBuilderToBeExam.length();
        if(wordLength>3) {
            int i = 0;
            for ( ; i < 4; i++) {
                if (!(isLetterChar(stringBuilderToBeExam.charAt(i)))) {
                    isLegal = false;
                    break;
                }
            }
            for ( ; i < wordLength && isLegal; i++) {
                char currentChar = stringBuilderToBeExam.charAt(i);
                if ((!isLetterChar(currentChar)) && (!isDigitChar(currentChar))) {
                    isLegal = false;
                    break;
                }
            }
        }else {
            isLegal = false;
        }
        return isLegal;
    }

    /**
     * 将读入的字符组成可能的单词
     *
     * <p>可能的单词为仅由字母和数字组成的字符串</p>
     *
     * @param scanChar    the scan char 读入的字符
     * @param isEndOfFile the is end of file 是否读到文件结尾
     * @return the boolean
     */
    public boolean buildPossibleWord(char scanChar,boolean isEndOfFile) {
        isPossibleWordAvailable = false;
        if (!isEndOfFile) {
            if (isLetterChar(scanChar) || isDigitChar(scanChar)) {
                if (!isInPossibleWord) {
                    isInPossibleWord = true;
                    possibleWord = new StringBuilder(INIT_WORD_MAX_LENGTH);
                }
                possibleWord.append(scanChar);
            } else {
                if (isInPossibleWord) {
                    isInPossibleWord = false;
                    isPossibleWordAvailable = true;
                }
            }
        }else {
            isPossibleWordAvailable = isInPossibleWord;
        }
        return isPossibleWordAvailable;
    }

    /**
     * 统计各个单词独立的出现频数（不区分大小写）
     *
     * <p>按照传入的单词统计各自出现的次数，返回该传入的单词是否已统计过<em>单词不区分大小写</em></p>
     *
     * @param legalWord the legal 要统计的合法单词
     * @return the boolean 传入的单词是否已统计过
     */
    public boolean individualWordSumUp(String legalWord) {
        boolean isCounted = false;
        legalWord = legalWord.toLowerCase();
        if (wordSumMap.containsKey(legalWord)) {
            wordSumMap.replace(legalWord,wordSumMap.get(legalWord) + 1);
            isCounted = true;
        }else {
            wordSumMap.put(legalWord,1);
        }
        return isCounted;
    }

    /**
     * 统计所有合法单词频数（忽略大小写）
     *
     * <p>
     * 统计合法单词的出现次数总和，字母全部转换为<em>小写</em>，不区分大小写
     * <em>请在{@link #buildPossibleWord(char scanChar, boolean isEndOfFile) buildPossibleWord} 返回true时调用</em>
     * </p>
     *
     * @return the boolean
     */
    public boolean allWordSumUp(){
        boolean isLegal = false;
        if (isLegalWord(possibleWord)) {
            wordSum++;
            isLegal = true;
        }
        return isLegal;
    }


    /**
     * 获取排序后的单词频数对数组
     * <p>
     * 使用{@link WordProcessor#wordComparator}比较器对数组进行排序</p>
     *
     * @return the sorted word count list
     */
    public List<Map.Entry<String,Integer> > getSortedWordCountList() {
        List<Map.Entry<String,Integer> > wordList = new ArrayList<>(wordSumMap.entrySet());
        wordList.sort(wordComparator);
        return wordList;
    }

    /**
     * 获取已统计单词总数
     *
     * @return the word sum 当前已统计单词总数
     */
    public int getWordSum() {
        return wordSum;
    }

    private boolean isLetterChar(char charToExam) {
        return (charToExam >= 'a' && charToExam <='z') || (charToExam >= 'A' && charToExam <= 'Z');
    }
    private boolean isDigitChar(char charToExam) {
        return (charToExam >= '0' && charToExam <= '9');
    }
}
