import java.io.*;
import java.util.List;
import java.util.Map;

/*读取目标文件中的内容 存放到String数组中*/
public class ReadTxt{
    public static String readTxt(String fileName){
//        String rootDir = System.getProperty("user.dir");
//        String filePath = rootDir + File.separator + "src" + File.separator + fileName;
        String content = "";     //要返回的字符串
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String oneLine;     //每一行
            while((oneLine = bufferedReader.readLine())!=null){
                content = content + oneLine +"\n";
            }
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
        return content;
    }
}
