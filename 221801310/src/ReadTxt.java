import java.io.*;

/*读取目标文件中的内容 存放到String数组中*/
public class ReadTxt{
    public static String readTxt(String fileName){
        //要返回的字符串
        String content = "";

        try{
            InputStream inputStream = new FileInputStream(fileName);
            int iAvail = inputStream.available();
            byte[] bytes = new byte[iAvail];

            inputStream.read(bytes);
            content = new String(bytes);
            inputStream.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        return content;
    }
}
