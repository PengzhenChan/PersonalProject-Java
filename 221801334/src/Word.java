/**
 * @author 李星源
 * @date 2021/02/25
 */
public class Word implements Comparable<Word> {
    // 单词的拼写
    private String spell;
    // 单词出现的次数
    private int count;

    public Word(String spell, int count){
        this.spell = spell;
        this.count = count;
    }

    @Override
    public int compareTo(Word o) {
        if (count != o.count){
            return count - o.count;
        }
        return o.spell.compareTo(spell);
    }

    public String getSpell() {
        return spell;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Word{" +
            "spell='" + spell + '\'' +
            ", count=" + count +
            '}';
    }
}
