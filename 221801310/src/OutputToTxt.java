import javax.annotation.processing.Filer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputToTxt {

    /*按照要求输出到Txt文件*/
    public static void outputToTxt(int num1, int num2, int line, List<Map.Entry<String, Integer>> list, String outputFileName){
        File outputFile = new File(outputFileName);

        try{
            FileWriter fileWriter = new FileWriter(outputFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //输出ascii字符数
            bufferedWriter.write("characters:"+num1+"\r\n");
            bufferedWriter.write("words:"+num2+"\r\n");
            bufferedWriter.write("lines:"+line+"\r\n");
            for(int i=0; i<list.size()&&i<10; i++){
                String key = list.get(i).getKey();
                Integer value = list.get(i).getValue();
                bufferedWriter.write(key+":"+value+"\r\n");
            }
            bufferedWriter.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }



    }

}
