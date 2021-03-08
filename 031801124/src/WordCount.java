import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
public class WordCount {

    public static void main(String[] args) {
        // write your code here
        Lib lib=new Lib();
        String inputfilename="";
        String outputfilename="";
        if (args.length == 2) {
            inputfilename = args[0];
            outputfilename = args[1];
            try {
                //File inputfile = new File(inputfilename);
                File outputfile = new File(outputfilename);
                if (!outputfile.exists()) {
                    outputfile.createNewFile();
                }
                FileWriter fileWritter = new FileWriter(outputfile); //用于清空output文件
                fileWritter.close();
            } catch (Exception e) {
                System.out.println("输入错误，文件打开失败");
                System.exit(0);
            } finally {
                lib.countChars(inputfilename, outputfilename);
                lib.countWords(inputfilename, outputfilename);
                lib.countLines(inputfilename, outputfilename);
                lib.countMost(inputfilename, outputfilename);
            }
        }
         else {
            System.out.println("命令输入错误！");
            System.exit(0);
        }

    }
}