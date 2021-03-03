public class WordCount 
{
    public static void main(String[] args) 
    {
        Lib lib = new Lib(args[0]);
        lib.writeFile(args[1]);
	}
}