/*主函数,通过命令行调用，读取和输出的文件名将在命令行参数中给出*/
public class WordCount{
    public static void main(String[] args){
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        String content;

        content = ReadTxt.readTxt(inputFile);
        System.out.print(content);
    }
}