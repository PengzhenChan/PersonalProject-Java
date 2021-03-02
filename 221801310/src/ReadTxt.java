import java.io.*;

/*读取目标文件中的内容 存放到String数组中*/
public class ReadTxt{
    public static String readTxt(String fileName){
        //要返回的字符串
        String content = "";

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //每一行
            String oneLine;

            while((oneLine = bufferedReader.readLine())!=null){
                content = content + oneLine + "\n";
            }
            //减去最后一次多余的换行符
            content =  content.substring(0, content.length()-1);
        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        return content;
    }
}
