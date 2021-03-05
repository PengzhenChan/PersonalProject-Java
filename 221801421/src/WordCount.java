import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordCount {
    //获得目录
    private static String DIR = System.getProperty("user.dir");

    /**
     * 根据文件名读取文件, 要求文件与该文件同一目录
     * @param filename 读入的文件名，
     * @return 文件内容
     */
    public static String readFromFile(String filename) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = null;
        int ch = 0;

        try{
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(DIR + "\\" + filename), StandardCharsets.UTF_8));
            stringBuilder = new StringBuilder();

            //利用StringBuilder拼接提速
            while ((ch = reader.read()) != -1){
                stringBuilder.append((char)ch);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("文件不存在！");
            e.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static void writeToFile(String filename, String str) {
        BufferedWriter writer = null;

        try{
            //FileOutputStream若没有目标文件则会自己创建, 没有FileNotFound异常
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(DIR + "\\" + filename), StandardCharsets.UTF_8));

            writer.write(str);
            writer.flush();
            writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //String str = readFromFile(args[0]);
        String str = readFromFile("input.txt");
        Lib lib = new Lib(str);
        //writeToFile(args[1], lib.getResult(10));
        writeToFile("output.txt", lib.getResult(10));
    }
}