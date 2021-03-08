/**
 * 计算有效行类
 */
public class EffectiveLineCounter {
    /**
     * 已扫描行中非空白行的数量
     */
    public int effectiveLineNumber=0;

    private boolean isNewLine=true;
    private static StringBuilder lineContent;
    private final int INIT_LINE_MAX_LENGTH =200;
    private int test = 0;

    /**
     * 计算非空白行的数量
     *
     * <p>统计任何包含非空白字符的行</p>
     *
     * @param lineChar    the line char 文件读取的字符
     * @param isEndOfFile the is end of file 当前是否读到文件结尾
     */
    public void countEffectiveLine(char lineChar, boolean isEndOfFile) {
        if (isNewLine) {
            isNewLine = false;
            lineContent = new StringBuilder(INIT_LINE_MAX_LENGTH);
        }
        if (lineChar==Config.NEW_LINE_CHAR||isEndOfFile) {
            isNewLine = true;
            if (isEffectiveLine()) {
                effectiveLineNumber++;
            }
        }
        if (!isEndOfFile) {
            lineContent.append(lineChar);
        }
    }

    public boolean isEffectiveLine(){
        return (lineContent.toString().trim().length() != 0);
    }
}
