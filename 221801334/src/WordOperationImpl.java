import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李星源
 * @date 2021/02/25
 */
public class WordOperationImpl implements WordOperation {
    // 输出文件
    private File outputFile;
    // 文本内容
    private String content;
    // 字符数
    private AtomicInteger characterNum;
    // 有效行数
    private int lineNum;
    // 单词数
    private int wordNum;

    public WordOperationImpl(File inputFile, File outputFile){
        this.outputFile = outputFile;
        content = FileUtil.read(inputFile);
        characterNum = new AtomicInteger(0);
        wordNum = 0;
    }

    /**
     * 计算文本的字符数
     *
     * @return 字符数
     */
    @Override
    public int countCharacter() {
        characterNum = new AtomicInteger(0);
        countCharacter(0, content.length());
        return characterNum.get();
    }

    /**
     * 统计文本中单词个数
     *
     * @return 单词数
     */
    @Override
    public int countWord() {
        wordNum = 0;
        String[] temp = content.split("[^a-z0-9]+");
        for (String word : temp){
            if (word.matches("^[a-z]{4}[a-z0-9]*$")){
                ++wordNum;
            }
        }
        return wordNum;
    }

    /**
     * 计算一段文本的字符数
     *
     * @param index 起始位置
     * @param len 长度
     */
    private void countCharacter(int index, int len){
        int count = len;
        int end = index + len;
        for (int i = index;i < end;++i){
            if (content.charAt(i) > 127){
                --count;
            }
        }
        characterNum.getAndAdd(count);
    }

    /**
     * 统计文本的有效行数
     *
     */
    private void countLine(){
        lineNum = content.split("\n+").length;
        if ('\n' == (content.charAt(0))){
            --lineNum;
        }
    }

    public int getLineNum() {
        countLine();
        return lineNum;
    }
}
