package WordCount;

public class WordCount 
{
	public static void main(String args[])
	{
		
		if(args.length<2)
		{
			System.out.println("需要两个参数。");
			return;
		}
		Lib lib=new Lib();
		
		String inputFileName=args[0];
		String outputFileName=args[1];
		
		lib.OutputFile(inputFileName, outputFileName);
	}

}
