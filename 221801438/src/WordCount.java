import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//主函数测试
public class WordCount {

    public static void main(String[] args) throws IOException {
        //创建IO工具类
        IOTool io = new IOTool();
        StringBuilder fileBuilder = io.fileInputToString(args[0]);

        //创建计数类
        CountTool count = new CountTool();
        int characters = count.characterCount(fileBuilder);
        int words = count.wordsCount(fileBuilder);
        int lines = count.invaluableLines(args[0]);
        HashMap<String,Integer> map = count.wordsSortCount(fileBuilder);
        List<Map.Entry<String,Integer>> list = count.sortMap(map);

        //输出到文件
        io.OutputToFile(args[1],characters,words,lines,list);
    }
}
