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
        else
        new WordCount(args[0],args[1]);
    }
    public WordCount(String a,String b)throws Exception{
        outfiletxt=b;
        infiletxt=a;
        //infile="inputFile.txt";
        infile = new File(infiletxt);
        outfile =new File(outfiletxt);
        in();
        lib =new Lib(str);
        //System.out.println(str);
        System.out.println(lib.getLines());
        System.out.println(lib.getChars());
        System.out.println(lib.getWordsNum());
        lib.getTopWords();
        out();
    }
    public void in() throws IOException {
        try {
            StringBuilder stringBuilder=new StringBuilder();
            String lineword="";
            br = new BufferedReader(new FileReader(infile));
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
    public void out() throws IOException {
        try{
            bw=new BufferedWriter(new FileWriter(outfile));
            bw.write("characters:"+lib.getChars()+"\n");
            bw.write("Words:"+lib.getWordsNum()+"\n");
            bw.write("lines:"+lib.getLines()+"\n");
            bw.write(lib.getTopWords());
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            bw.close();
        }
    }

}
