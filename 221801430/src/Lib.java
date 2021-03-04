import java.io.*;

public class Lib {

    String fileName;
    File file = null;
    InputStream in = null;
    String[] sortWords = new String[11];
    int[] sortCount = new int[11];

    //读取文件内容
    public BufferedReader ReadFile(String fileName) {
        file = new File(fileName);
        BufferedReader reader = null;
        try {
            if (!file.exists())
                file.createNewFile();

            reader = new BufferedReader(new FileReader(file));
            in = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            return reader;
        }
    }

    //统计文件的字符数
    public long CharCount (BufferedReader bufferedReader) {

        long num = 0L;
        int ch;

        try {
            while ((ch=bufferedReader.read()) != -1){
                if (ch <= 127 && ch >= 0){
                    num++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return num;
        }
    }

    //统计文件单词总数
    public long WordsCount(BufferedReader bufferedReader) {
        long num = 0L;
        int one;
        String str = new String();

        try {
            while ((one = bufferedReader.read()) != -1) {
                char ch = (char) one;

                if(ch>='a'&&ch<='z' || ch>='0'&&ch<='9'
                        || ch>='A'&&ch<='Z'){
                    str += ch;
                }
                else {
                    if(str != null && IsWord(str)) {

                        num++;
                    }
                    String temp = null;
                    str = temp;
                }

            }
            if(!str.equals("") && IsWord(str)){
                num++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    //判断单词是否合法
    public boolean IsWord(String str) {
        String temp = new String();
        if (str == null)
            return false;
        if (str==temp)
            return false;

        for (int i=0;i<str.length();i++) {
            char ch = str.charAt(i);

            if (i<4 && ch>='0' && str.charAt(i)<='9') {
                return false;
            }
        }

        return true;
    }

    //统计文件行数
    public int FileLines(BufferedReader bufferedReader) {
        int rows = 0;
        String line = new String();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                String temp = line.replaceAll("\\s*","");

                if (line.length() != 0) {
                    rows++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return rows;
    }

    // 统计单词出现的次数
    public void WordsNum(BufferedReader bufferedReader,int n) {
        int one;
        String str = new String();
        //BufferedReader brTemp = bufferedReader;
        //int n = (int)WordsCount(bufferedReader);
        String[] words = new String [n];
        int[] counts = new int [n];
        int j = 0;

        for (int i = 0; i < n ; i++)
            counts[i] = 0;

        try {
            while ((one = bufferedReader.read()) != -1) {
                char ch = (char) one;

                if(ch>='a'&&ch<='z' || ch>='0'&&ch<='9'
                        || ch>='A'&&ch<='Z'){
                    if (str == null)
                    {
                        String tem = new String();
                        str = tem;
                    }
                    str += ch;

                }
                else{

                    if (str != "" && IsWord(str)) {
                        String temp = WordChange (str);

                        //单词未在数组中，则存入计数
                        if (!IfSame(words , temp , counts) && j<words.length) {
                            words[j] = str;
                            counts[j]++;
                            j++;
                        }

                        str = null;

                    }
                }

            }

            //最后一个单词统计
            if (IsWord(str)) {
                String temp = WordChange (str);
                if (!IfSame(words, temp, counts) && j < words.length) {
                    words[j] = str;
                    counts[j]++;
                    j++;
                }
            }

            SortWords(words, counts);

            ShowWords(words.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //将单词转换成小写
    public String WordChange(String str) {
        String temp = str.toLowerCase();
        return temp;
    }

    //判断在数组中是否有相同的单词,若有相同的单词则计数
    public boolean IfSame(String[] words , String str , int[] nums){

        for (int i=0 ; i<words.length && words.length >= 1; i++){

            if (str.equals(words[i])) {
                nums[i]++;
                return true;
            }
        }
        return false;
    }

    //根据频数排序，字符串数组大小10，将最大10个存入
    public void SortWords(String[] words , int[] counts) {

        for (int i = 0; i < counts.length && words[i] != null; i++) {

            for (int j = i+1 ; j<counts.length && words[j] != null; j++){
                if (counts[i]<counts[j]){
                    ChangInt(counts , i , j);
                    ChangS(words , i , j);
                }

                else if (counts[i] == counts[j]) {
                    if (words[i].compareTo(words[j]) > 0){
                        ChangInt(counts, i, j);
                        ChangS(words, i, j);
                    }
                }
            }

        }

        for (int i = 0; i<10 && i<words.length; i++) {
            sortWords[i] = words[i];
            sortCount[i] = counts[i];
        }
    }

    //交换数组中元素位置,字符串
    public String[] ChangS(String[] str , int i, int j) {
        String tempS = new String ();
        tempS = str[j];
        str[j] = str[i];
        str[i] = tempS;
        return str;
    }

    //交换数组中元素位置，整型
    public int[] ChangInt(int[] counts, int i , int j) {
        int temp;
        temp = counts[j];
        counts[j] = counts[i];
        counts[i] = temp;
        return counts;
    }

    //输出单词及其频数
    public void ShowWords(int n) {
        for (int i = 0; i < 10 && i < n && sortCount[i] != 0; i++) {
            System.out.println(sortWords[i] + ":" + sortCount[i]);
        }
    }

    public String[] GetWords() {
        return sortWords;
    }

    public int[] GetCount() {
        return sortCount;
    }
}
