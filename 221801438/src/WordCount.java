import java.io.IOException;
//主函数测试
public class WordCount {
    public static void main(String[] args) throws IOException {
        //创建IO工具类
        IOTool io = new IOTool();
        StringBuffer fileBuffer = io.fileInputToString("input.txt");
        //创建计数类
        CountTool count = new CountTool();
        int characters = count.characterCount(fileBuffer);
        int words = count.wordsCount(fileBuffer);
        int lines = count.invaluableLines("input.txt");
        io.OutputToFile("output.txt",characters,words,lines);
    }
}
