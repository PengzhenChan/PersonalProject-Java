public class WordCount {
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("命令行参数应至少为两个");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        Lib lib = new Lib(inputFile,outputFile);
        lib.writeFile();
    }
}
