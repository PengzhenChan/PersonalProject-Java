import java.io.*;

/*读取目标文件中的内容 存放到String数组中*/
public class ReadTxt{
    public static String readTxt(String fileName){
        //要返回的字符串
        String content = "";
//        鉴于键盘输入的换行是\r\n 所以一行一行读取自己加\n是错误的
        try{
            InputStream is = new FileInputStream(fileName);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];

            System.out.println(is.read(bytes));
            content = new String(bytes);

            is.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return content;
    }
}
