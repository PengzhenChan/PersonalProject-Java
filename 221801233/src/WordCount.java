import java.io.*;

public class WordCount {
    String outfiletxt;
    String infiletxt;
    String str;
    File infile;
    File outfile;
    BufferedReader br;
    BufferedWriter bw;
    Lib lib;
    public  static void main(String args[])throws Exception{

        if(args.length!=2){
            System.out.println("Required ARGS NUM Error");
        }
        else {
            new WordCount(args[0], args[1]);
        }
    }
    public WordCount(String a,String b) throws IOException {
        long start = System.currentTimeMillis();
        outfiletxt = b;
        infiletxt = a;
        //infile="inputFile.txt";
        run();
        long end = System.currentTimeMillis();
        //System.out.println(end-start);
    }
    public void in() throws IOException {
        try {
            StringBuilder stringBuilder=new StringBuilder();
            String lineword="";
            br = new BufferedReader(new FileReader(infile));
            while((lineword=br.readLine())!=null&&stringBuilder.length()<10000000){
                if(!lineword.trim().isEmpty()) {
                    stringBuilder.append(lineword);
                    stringBuilder.append('\n');
                }
            }
            str=stringBuilder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        finally{
            br.close();
        }
    }
    public void out() throws IOException {
        try{
            bw=new BufferedWriter(new FileWriter(outfile));
            bw.write("characters: "+lib.getChars()+"\n");
            bw.write("words: "+lib.getWordsNum()+"\n");
            bw.write("lines: "+lib.getLines()+"\n");
            bw.write(lib.getTopWords());
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            bw.close();
        }
    }
    public void run() throws IOException {
        try {
            infile = new File(infiletxt);
            outfile = new File(outfiletxt);
            if (!infile.exists()) {
                System.out.println("File Not Exist Error!");
                return;
            }
            else {
                in();
                lib = new Lib(str);
                out();
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("ERROR!");
        }
    }
}
