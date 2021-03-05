import org.junit.Assert;
import org.junit.Test;
import java.util.Map;
import java.util.Random;

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

    @Test
    public void testPerformance(){
        String str = WordCount.readFromFile("10000.txt");
        Lib lib = new Lib(str);
        System.out.println(lib.getResult(10));

        /*String[] strings = {"apple1 ", "apple2 ", "banana3 ", "banana4 ", "golden5 ", "time6 ", "clover7 ", "clover8 ", "clover9 ", "clover10 ", "clover11 "};
        int[] counts = new int[11];
        int x=0;
        int sum=0;
        Random random = new Random(100);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<10000;i++){
            x = random.nextInt(11);
            stringBuffer.append(strings[x]);
            if(x == 3 || x == 5) stringBuffer.append("\n");
            counts[x]++;
        }

        WordCount.writeToFile("10000.txt", stringBuffer.toString());
        for(int j=0;j<11;j++){
            sum += counts[j];
            System.out.println(counts[j]);
        }
        System.out.println(""+sum);*/

    }
}
