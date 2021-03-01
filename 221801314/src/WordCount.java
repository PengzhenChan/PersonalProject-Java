public class WordCount{

    public static void main(String[] args) {
        String path = "./input.txt";
        StringBuilder stringBuilder = new StringBuilder();
        Lib.FileIOUtil fileIOUtil = new Lib.FileIOUtil();
        Lib.TextEditor textEditor = new Lib.TextEditor(fileIOUtil.openStream(path));

        stringBuilder.append("characters: ").append(textEditor.countAscii()).append('\n')
                .append("words: ").append(textEditor.countWords());
        System.out.println(stringBuilder.toString());

        fileIOUtil.closeStream();
        //System.out.println(fileIOUtil.readFile("./input.txt"));
    }
}