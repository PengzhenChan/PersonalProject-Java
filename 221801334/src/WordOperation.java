import java.util.List;

/**
 * 接口封装，将功能独立出来
 * 计算字符数、计算单词数、计算top10词频、计算全部
 *
 * @author 李星源
 * @date 2021/02/25
 */
public interface WordOperation {

    /**
     * 计算文本的字符数
     *
     * @return 字符数
     */
    int countCharacter();

    /**
     * 统计文本中单词个数
     *
     * @return 单词数
     */
    int countWord();

    /**
     * 统计文本中top10单词及个数
     *
     * @return 单词列表
     */
    List<Word> countTopTenWord();

    /**
     * 统计字符数、单词数、行数、top10单词并输出到文件
     *
     */
    void countAll();
}
