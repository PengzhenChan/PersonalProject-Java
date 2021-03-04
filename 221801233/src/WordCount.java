import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    String outfile;
    String str;
    File file;
    BufferedReader br;
    public  static void main(String args[])throws Exception{
        new WordCount(args[0],"a");
    }
    public WordCount(String a,String b)throws Exception{
        outfile=b;
        //infile="inputFile.txt";
        file = new File(a);
        in();
        Lib lib =new Lib(str);
        System.out.println(str);
        System.out.println(lib.getLines());
        System.out.println(lib.getChars());
        System.out.println(lib.getWordsNum());
        lib.getTopWords();
    }
    public void in() throws IOException {
        try {
            StringBuilder stringBuilder=new StringBuilder();
            String lineword="";
            br = new BufferedReader(new FileReader(file));
            while((lineword=br.readLine())!=null){
                stringBuilder.append(lineword);
                stringBuilder.append('\n');
            }
            str=stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            br.close();
        }
    }

}
