public class WordCount 
{
    public static void main(String[] args) 
    {
        if (args.length != 2)
        {
            System.out.println("命令行参数错误！");
        }
        else
        {
            Lib lib = new Lib(args[0]);
            lib.writeFile(args[1]);
        }
	}
}