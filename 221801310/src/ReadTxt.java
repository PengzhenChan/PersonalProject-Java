import java.io.*;
import java.io.FileReader;

/*读取目标文件中的内容 存放到String数组中*/
public class ReadTxt{
    public static String readTxt(String fileName){
        //要返回的字符串
        String content = new String("");
        //缓冲区大小为3m
        final int BUFF_SIZE = 0x300000;

        try{
            InputStream inputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[BUFF_SIZE];

            while(bufferedInputStream.read(bytes)!=-1){
                content += new String(bytes,"UTF-8");
            }

            bufferedInputStream.close();
            inputStream.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        return content;
    }

    public static String openFile(String fileName){
        StringBuffer s =  new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            int li=0;
            double endTime1 = System.currentTimeMillis();//获取结束时间
            while ((li=bufferedReader.read())!= -1)
            {
                char c= (char)li;
                s.append(c);
            }
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return s.toString();
    }
}
