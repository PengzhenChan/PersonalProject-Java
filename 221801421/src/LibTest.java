import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

public class LibTest {

    /**
     * 测试charsCount方法
     */
    @Test
    public void testCharsCount() {
        String str = WordCount.readFromFile("input.txt");
        Lib lib = new Lib(str);
        Assert.assertEquals(250, lib.charsCount(str));
    }

    /**
     * 测试linesCount方法
     */
    @Test
    public void testLinesCount() {
        String str = WordCount.readFromFile("input.txt");
        Lib lib = new Lib(str);
        Assert.assertEquals(20, lib.linesCount(str));
    }

    /**
     * 测试wordsCount方法
     */
    @Test
    public void testWordsCount() {
        String str = WordCount.readFromFile("input.txt");
        Lib lib = new Lib(str);
        Assert.assertEquals(30, lib.wordsCount(str));
    }

    /**
     * 测试getTopK方法
     */
    @Test
    public void testGetTopK() {
        String str = WordCount.readFromFile("input.txt");
        Lib lib = new Lib(str);
        Map<String, Integer> map = lib.getTopK(10);
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    public void testGetResult() {
        String str = WordCount.readFromFile("input.txt");
        Lib lib = new Lib(str);
        System.out.println(lib.getResult(10));
    }
}
