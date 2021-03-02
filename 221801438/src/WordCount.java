import java.io.IOException;
//主函数测试
public class WordCount {
    public static void main(String[] args) throws IOException {
        //创建IO工具类
        IOTool io = new IOTool();
        StringBuffer fileBuffer = io.fileInputToString("input.txt");
        //创建计数类
        CountTool count = new CountTool();
        io.OutputToFile("output.txt",count.characterCount(fileBuffer),count.wordsCount(fileBuffer));
    }
}
