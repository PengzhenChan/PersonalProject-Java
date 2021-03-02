import javafx.css.Match;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountWord {
    String fileName;
    ArrayList<String> arrayList;
    int char_count;
    int word_count;
    CountWord(String fileName){
        this.fileName = fileName;
        arrayList = new ArrayList<>();
/*        CalCharCount();*/
        CalWordCount();
    }
    void CalCharCount(){
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
        int count=0;
        String[] strArrayTrue = (String[]) arrayList.toArray(new String[0]);
        for(String str : strArrayTrue) {
            //t转换成空格来计算
            str = str.replaceAll("\t"," ");
            count+=str.length();
            count++;
        }
        count--;
        this.char_count = count;
    }
    int getChar_count(){
        return char_count;
    }
    void CalWordCount(){
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
        int count=0;
        String[] strArrayTrue = (String[]) arrayList.toArray(new String[0]);
        for(String str : strArrayTrue) {
            //利用正则表达式分割每一行的单词
            String[] parts=str.split("\\W");
            for (int i = 0;i<parts.length;i++){
                if (ifTrueWold(parts[i])){
                    this.arrayList.add(parts[i]);
                    count++;
                }
            }
        }
        word_count=count;
/*        System.out.println(word_count);*/
    }
    Boolean ifTrueWold(String wold){
        String regex = "[a-zA-Z]{4}([a-zA-Z0-9])*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(wold);
        if (matcher.find()){
            return true;
        }
        return false;
    }
    int getWord_count(){
        return word_count;
    }
}
