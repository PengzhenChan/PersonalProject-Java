public class WordCount {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.print("该程序只接受两个参数，待读取文件与待写入文件,程序关闭。。。");
            System.exit(0);
        }
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        //创建工具类执行方法
        Lib wcu = new Lib(inputFilePath, outputFilePath);
        wcu.readFileByChars();
        wcu.countWords();
        wcu.getValueLines();
        wcu.writeFile();
    }
}