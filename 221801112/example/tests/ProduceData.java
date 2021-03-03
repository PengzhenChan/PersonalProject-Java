import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProduceData {
    public static void main(String[] args) throws FileNotFoundException {
        ProduceData produceData=new ProduceData();

        produceData.outPut(produceData.produce(50000000,50,8000),"testData/input5.txt");
    }

    public String produce(int cNum,int wNum,int lNum)
    {
        StringBuilder str=new StringBuilder(100);
        char tmp;
        for (int i=1;i<=cNum;i++)
        {
            if (i%wNum==0)
            {
                tmp=' ';
            }
            else if (i%25==0)
            {
                tmp='\n';
            }
            else if(i%2==0)
                tmp= (char) ((int)(Math.random()*25)+'a');
            else
                tmp= (char) ((int)(Math.random()*25)+'A');
            str.append(tmp);
        }
        System.out.println(str.length());
        return str.toString();
    }

    public void outPut(String outPut,String outPutPath) throws FileNotFoundException
    {
        System.out.println("输出文件路径:" + outPutPath);
        File file=new File(outPutPath);
        FileOutputStream outputStream = new FileOutputStream(file);
        try
        {
            outputStream.write(outPut.getBytes());
            outputStream.close();
        }  catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                outputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
