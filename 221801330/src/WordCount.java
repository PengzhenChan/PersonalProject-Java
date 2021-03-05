import java.io.*;
import java.util.*;

public class WordCount
{
    public static void main(String[] args){
		    File f1;
		    File f2;
		    if (args.length < 2) {
            f1 = new File("F:/input.txt");
            f2 = new File("F:/output.txt");
        }
		    else {
            f1 = new File(args[0]);
            f2 = new File(args[1]);
        }
		}    
}
