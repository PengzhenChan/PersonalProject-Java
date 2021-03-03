import java.io.*;

public class WordCount {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inFileName = br.readLine();
            String outFileName = br.readLine();
            br.close();
            BufferedReader inputFile = new BufferedReader(new FileReader(inFileName));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(outFileName,false));
            int b = 0;
            //变量i统计字符数量
            int i;
            for (i=0; b!=-1; i++) {
                b = inputFile.read();
                if (b == -1) {
                    break;
                }
                System.out.print((char)b);
            }
            String str = "characters:" + i +"\n";
            outputFile.write(str);
            inputFile.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}