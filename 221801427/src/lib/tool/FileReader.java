package lib.tool;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

public class FileReader
{
    /**
     * @param inputFileName
     * @return 读取出的字符串
     */
    @SuppressWarnings("resource")
    public static String readFile(String inputFileName)
    {
        //通过MappedByteBuffer读取文件
        File file = new File(inputFileName);
        long len = file.length();
        MappedByteBuffer mappedByteBuffer = null;

        try
        {
            mappedByteBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, len);
            // 通过RandomAccessFile获取FileChannel，并通过FileChannel.map方法，把文件映射到虚拟内存，返回逻辑地址。
            if (mappedByteBuffer != null)
            {
                return StandardCharsets.UTF_8.decode(mappedByteBuffer).toString().toLowerCase(); 
            }
            else
            {
                return "";// 空白文件则返回空
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Error Reading File");
            e.printStackTrace();
        }
        finally
        {
            if (mappedByteBuffer != null)
            {
                // 垃圾回收
                Cleaner cleaner = ((DirectBuffer) mappedByteBuffer).cleaner();
                if (cleaner != null)
                {
                    cleaner.clean();
                }
            }
        }
        return "";// 未读取到则返回空
    }
}
