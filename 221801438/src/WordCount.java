import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//主函数测试
public class WordCount {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();    //获取开始时间

        //创建IO工具类
        IOTool io = new IOTool();
        StringBuilder fileBuilder = io.fileInputToString("input.txt");
        //创建计数类
        CountTool count = new CountTool();
        int characters = count.characterCount(fileBuilder);
        int words = count.wordsCount(fileBuilder);
        int lines = count.invaluableLines("input.txt");
        HashMap map = count.wordsSortCount(fileBuilder);
        List<Map.Entry<String,Integer>> list = count.sortMap(map);
        //输出到文件
        io.OutputToFile("output.txt",characters,words,lines,list);

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("测试程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}
