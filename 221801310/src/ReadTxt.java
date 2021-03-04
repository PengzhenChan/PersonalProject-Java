import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*读取目标文件中的内容 存放到String数组中*/
public class ReadTxt{
    static int BUFF_SIZE = 0x1000000;
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public static String readTxt(String fileName){
        //要返回的字符串
        StringBuffer stringBuffer = new StringBuffer();
        //缓冲区大小为10m
        try{
            InputStream inputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[BUFF_SIZE];

            int len;        //len用来记录每次从缓冲区读取的长度
            while((len=bufferedInputStream.read(bytes)) != -1){
                stringBuffer.append(new String(bytes, 0,len,StandardCharsets.UTF_8));
            }

            bufferedInputStream.close();
            inputStream.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        return stringBuffer.toString();
    }

    //nio读取文件
    public static String readText1(String fileName){
        try{
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(0x300000);
            StringBuffer stringBuffer = new StringBuffer();

            int bytesRead = fileChannel.read(byteBuffer);
            while ((bytesRead != -1)) {
                System.out.println("Read " + bytesRead);
                byteBuffer.flip();      //flip将byteBuffer由写模式转换为读模式
                while (byteBuffer.hasRemaining()) {
                    stringBuffer.append(ENCODING.decode(byteBuffer).toString());
                }
                byteBuffer.clear();     //清空缓冲区（只会清空已读的）
                bytesRead = fileChannel.read(byteBuffer);
            }
            return stringBuffer.toString();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "";
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

    public static String readMMAP(String fileName){
        File file = new File(fileName);
        RandomAccessFile randomAccessFile = null;
        MappedByteBuffer mappedByteBuffer = null;
        try{
            randomAccessFile = new RandomAccessFile(file,"r");
            mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            byte[] bytes = new byte[BUFF_SIZE];
            if(mappedByteBuffer!=null){
                return ENCODING.decode(mappedByteBuffer).toString();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
