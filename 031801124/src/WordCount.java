import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
public class WordCount {

    public static void main(String[] args) {
        // write your code here
        Lib lib=new Lib();
        //Scanner scan = new Scanner(System.in);
        String inputfilename="";
        String outputfilename="";
        if (args.length == 2) {
            inputfilename = args[0];
            outputfilename = args[1];
        }

            //System.out.println("测试用，请输入输入文件和输出文件。");
            //String inputfilename = scan.nextLine();
            //String outputfilename = scan.nextLine();
            try {
                File inputfile = new File(inputfilename);
                File outputfile = new File(outputfilename);
                if (!outputfile.exists()) {
                    outputfile.createNewFile();
                }
                FileWriter fileWritter = new FileWriter(outputfile);
                fileWritter.close();
            } catch (Exception e) {
                System.out.println("文件打开失败");
                System.exit(0);
            } finally {
                //if file doesnt exists, then create it

                lib.countChars(inputfilename,outputfilename);
                lib.countWords(inputfilename,outputfilename);
                lib.countMost(inputfilename,outputfilename);
            }

        //} else {
            //System.out.println("命令输入错误！");
            //System.exit(0);
        }

    }
//}