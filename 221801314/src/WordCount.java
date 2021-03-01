public class WordCount{

    public static void main(String[] args) {
        String path = "./input.txt";
        Lib.FileIOUtil fileIOUtil = new Lib.FileIOUtil();
        Lib.TextEditor textEditor = new Lib.TextEditor(fileIOUtil.readFile(path));

        textEditor.countAscii();
        //System.out.println(fileIOUtil.readFile("./input.txt"));
    }
}