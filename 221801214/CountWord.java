import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CountWord {
    String fileName;
    int count;
    CountWord(String fileName){
        this.fileName = fileName;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
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
            //将\n和\t转换成空格来计算
            str = str.replaceAll("\\\\n", " ");
            str = str.replaceAll("\\\\t"," ");
            count+=str.length();
        }
        this.count = count;
    }
    int getCount(){
        return count;
    }
}
