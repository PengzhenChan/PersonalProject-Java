
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib {
    String inputFileName;
    String outputFileName;
    Map<String,Integer> wordMap;
    ArrayList<Map.Entry<String,Integer>> arrayList1;
    int char_count;
    int word_count;
    int rows_count;
    Lib(String inputFileName, String outputFileName) throws IOException {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        wordMap = new HashMap<>();
        CalCharCount();
        CalWordCount();
        CalMaxWord();
        CalRows();
        PrintfFile();
    }

    void CalCharCount(){
/*        Charset c = Charset.forName("UTF-8");*/
        ArrayList<String> arrayList = new ArrayList<>();
        int count=0;
        try {
            FileReader fileReader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str;
            String s = null;
            while ((str = bufferedReader.readLine()) != null) {
                s=str;
                arrayList.add(str);
                str = str.replaceAll("\t"," ");
                count+=str.length();
                count+=2;
            }
            if (!s.equals("")){
                count-=2;
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.char_count = count;
        System.out.println(char_count);
    }
    int getChar_count(){
        return char_count;
    }
    void CalWordCount(){
/*        Charset c = Charset.forName("UTF-8");*/
        ArrayList<String> arrayList = new ArrayList<>();
        int count = 0;
        try {
            FileReader fileReader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                arrayList.add(str);
                String[] parts = str.split("\\W");
                for (int i = 0;i<parts.length;i++){
                    if (ifTrueWold(parts[i])){
                        count++;
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        int count=0;
        String[] strArrayTrue = (String[]) arrayList.toArray(new String[0]);
        for(String str : strArrayTrue) {
            String[] parts=str.split("\\W");
            for (int i = 0;i<parts.length;i++){
                if (ifTrueWold(parts[i])){
                    count++;
                }
            }
        }*/
        word_count=count;
    }
    Boolean ifTrueWold(String wold){
        String regex = "^[a-zA-Z]{4,}([a-zA-Z0-9])*";
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
    void CalMaxWord(){
/*        Charset c = Charset.forName("UTF-8");*/
        String word;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(inputFileName);
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
        String[] strArrayTrue = (String[]) arrayList.toArray(new String[0]);
        for(String str : strArrayTrue) {
            String[] parts=str.split("\\W");
            for (int i = 0;i<parts.length;i++){
                if (ifTrueWold(parts[i])){
                    word=parts[i].toLowerCase();
                    if(wordMap.containsKey(word)){
                        int value=wordMap.get(word);
                        value++;
                        wordMap.put(word,value);
                    }
                    else {
                        wordMap.put(word,1);
                    }
                }
            }
        }
        arrayList1 = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
        Collections.sort(arrayList1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
                int result=t1.getValue().compareTo(stringIntegerEntry.getValue());
                if (result!=0){
                    return result;
                }
                else {
                    return stringIntegerEntry.getKey().compareTo(t1.getKey());
                }
            }
        });
        for (Map.Entry<String, Integer> t : arrayList1) {
            System.out.println(t.getKey() + ":" + t.getValue());
        }
    }
    void CalRows(){
/*        Charset c = Charset.forName("UTF-8");*/
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(inputFileName);
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
            String regex = "[^\\s]+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()){
                count++;
            }
        }
        rows_count=count;
        System.out.println(rows_count);
    }
    void PrintfFile() throws IOException {
        File fout = new File(outputFileName);
        FileOutputStream fos = new FileOutputStream(fout);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write("characters: " + char_count + "\n");
        osw.write("words: " + word_count + "\n");
        osw.write("lines: " + rows_count+ "\n");
        if (arrayList1.size()<10){
            for (Map.Entry<String, Integer> t : arrayList1) {
                osw.write(t.getKey() + ": " + t.getValue() + "\n");
            }
        }
        else {
            int i=0;
            for (Map.Entry<String, Integer> t : arrayList1) {
                i++;
                osw.write(t.getKey() + ": " + t.getValue() + "\n");
                if (i>9){
                    break;
                }
            }
        }
        osw.close();
    }
}
