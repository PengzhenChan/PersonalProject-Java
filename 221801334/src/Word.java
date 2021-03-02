/**
 * 单词类
 * 包括单词的拼写和个数
 *
 * @author 李星源221801334
 * @date 2021/02/25
 */
public class Word {
    // 单词的拼写
    private String spell;
    // 单词出现的次数
    private int count;

    public Word(String spell, int count){
        this.spell = spell;
        this.count = count;
    }

    public String getSpell() {
        return spell;
    }

    public int getCount() {
        return count;
    }

}
