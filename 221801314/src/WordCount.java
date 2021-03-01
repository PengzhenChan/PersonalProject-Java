public class WordCount{

    public static void main(String[] args) {
        String path = "./input.txt";
        StringBuilder stringBuilder = new StringBuilder();
        Lib.FileIOUtil fileIOUtil = new Lib.FileIOUtil();
        Lib.TextEditor textEditor = new Lib.TextEditor(fileIOUtil.openReadStream(path));

        stringBuilder.append("characters: ").append(textEditor.countAscii()).append('\n')
                .append("words: ").append(textEditor.countWords()).append('\n')
                .append("lines: ").append(textEditor.countRows()).append('\n');
        System.out.println(stringBuilder.toString());
        textEditor.countTopWords();
        textEditor.printTops();

        fileIOUtil.closeReadStream();
        //System.out.println(fileIOUtil.readFile("./input.txt"));
    }
}