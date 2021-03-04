import java.io.*;

public class Lib {

    String fileName;
    File file = null;
    InputStream in = null;
    String[] sortWords;
    int[] sortCount;

    //读取文件内容
    public BufferedReader ReadFile(String fileName) {
        file = new File(fileName);
        BufferedReader reader = null;
        try {
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
                    if(IsWord(str)) {
                        num++;
                    }
                    String temp = new String();
                    str = temp;
                }

            }
            if(IsWord(str))
                num++;
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    //判断单词是否合法
    public boolean IsWord(String str) {

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

    //统计单词出现的次数
    public void WordsNum(BufferedReader bufferedReader) {
        int one;
        String str = new String();
        int n = (int)WordsCount(bufferedReader);
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
                    str += ch;
                }
                else{
                    if (IsWord(str)) {
                        String temp = WordChange (str);

                        //单词未在数组中，则存入
                        if (!IfSame(words , temp , counts)) {
                            words[j] = str;
                            j++;
                        }
                    }
                }

            }
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
        for (int i=0 ; i<words.length ; i++){

            if (str == words[i]) {
                nums[i]++;
                return true;
            }
        }
        return false;
    }

    //根据频数排序，字符串数组大小10，将最大10个存入
    public void SortWords(String[] words , int[] counts) {
        sortWords = new String [11];
        sortCount = new int [11];
        int max = 0;

        for (int i = 0 ; i < counts.length ; i++) {
            for (int j = i ; j<counts.length ; j++){
                if (counts[i]<counts[j]){

                    ChangInt(counts , i , j);
                    ChangS(words , i , j);

                }
            }
        }
    }

    //交换数组中元素位置,字符串
    public String[] ChangS(String[] str , int i, int j) {
        String tempS = new String ();
        tempS = str[j];
        str[j] = str[i];
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
}
