import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class CountWord {
    String fileName;
    int count;
    CountWord(String fileName){
        this.fileName = fileName;
        Charset c = Charset.forName("UTF-8");
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName,c);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                arrayList.add(str);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = arrayList.size();
        int count=0;
        int length_str=0;
        String[] strArrayTrue = (String[]) arrayList.toArray(new String[0]);
        for(String str : strArrayTrue) {
            //t转换成空格来计算
            str = str.replaceAll("\t"," ");
            count+=str.length();
            count++;
        }
        count--;
        this.count = count;
    }
    int getCount(){
        return count;
    }
}
