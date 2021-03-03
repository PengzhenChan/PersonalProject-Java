public class WordCount {
    public static void main(String[] ages){
        Lib lib = new Lib();
        System.out.println(lib.CharCount(lib.ReadFile
                ("D:\\PersonalProject-Java" +
                        "\\221801430\\input.txt")));
        System.out.println(lib.WordsCount(lib.ReadFile
                ("D:\\PersonalProject-Java" +
                        "\\221801430\\input.txt")));
    }
}
