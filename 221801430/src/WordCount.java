public class WordCount {
    public static void main(String[] ages){
        Lib lib = new Lib();
        int n;

        System.out.println(lib.CharCount(lib.ReadFile
                ("D:\\PersonalProject-Java" +
                        "\\221801430\\input.txt")));

        n = (int)lib.WordsCount(lib.ReadFile
                ("D:\\PersonalProject-Java" +
                        "\\221801430\\input.txt"));
        System.out.println(n);

        System.out.println(lib.FileLines(lib.ReadFile
                ("D:\\PersonalProject-Java" +
                        "\\221801430\\input.txt")));
        lib.WordsNum(lib.ReadFile
                ("D:\\PersonalProject-Java" +
                        "\\221801430\\input.txt"),n);
    }
}
