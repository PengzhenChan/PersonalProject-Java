public class WordCount {
    public static void main(String[] args) {
//        if(args.length != 2)
//        {
//            System.out.print("程序只接受两个参数");
//            return;  //退出程序
//        }
//
//        String inputFileName = args[0]; //输入文件名
//        String outputFileName = args[1]; //输出文件名
//        Lib wcu=new Lib(inputFileName, outputFileName);
        Lib wcu=new Lib("C:\\Users\\Admin\\Desktop\\3.txt"
                , "C:\\Users\\Admin\\Desktop\\4.txt");
        wcu.readFileByChars();
        wcu.countWords();
        wcu.getValueLines();
        wcu.writeFile();

    }
}