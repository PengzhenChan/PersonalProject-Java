import java.io.*;
import java.util.*;

public class Lib {
    static CountData cd=new CountData();
    static CountData openFile(File input) {
        int myCountLine = 0;
        int myWordCount = 0;
        HashMap<String, Integer> myWordCountMap = new HashMap<String, Integer>();
        List<Map.Entry<String, Integer>> list = null;
        if (! input.exists()) {
            return null;
        }
        else {
            try {
                FileReader fr = new FileReader(input);
                BufferedReader br = new BufferedReader(fr);
                String s = "";
                while ((s = br.readLine()) != null) {
                    myCountLine++;
                    myWordCount += countWord(s);
                    myWordCountMap.putAll(getWordFrequency(s));
                }
                list = new ArrayList<Map.Entry<String, Integer>>(myWordCountMap.entrySet());
                list.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                cd.setCountLine(myCountLine);
                cd.setCountWord(myWordCount);
                cd.setGetWordFrequency(list);
                return cd;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return cd;
            }
        }
    }

    static void countChar(File file) {
        int myCountChar = 0;
        if (! file.exists()) {
            return ;
        }
        else {
            try {
                byte[] tem = new byte[20 * 1024];
                int len = tem.length;
                int bytes = 0;
                FileInputStream in = new FileInputStream(file);
                while ((bytes = in.read(tem, 0, len)) != - 1)
                    myCountChar += bytes;
                cd.setCountChar(myCountChar);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
            }
        }
    }

    static int countWord(String s) {
        int myWordCount = 0;
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (isWord(word)) {
                    myWordCount++;
                }
                word = "";
            }
        }
        if (isWord(word)) {
            myWordCount++;
        }
        return myWordCount;
    }

    static  HashMap<String, Integer> getWordFrequency(String s) {
        String word = "";
        HashMap<String, Integer> myWordCount = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (isWord(word)) {
                    if (myWordCount.containsKey(word)) {
                        myWordCount.put(word, myWordCount.get(word) + 1);
                    }
                    else
                        myWordCount.put(word, 1);
                }
                word = "";
            }
        }
        if (isWord(word)) {
            if (myWordCount.containsKey(word)) {
                myWordCount.put(word, myWordCount.get(word) + 1);
            }
            else
                myWordCount.put(word, 1);
        }
        return myWordCount;
    }

    static boolean isWord(String s) {
        if (s.length() < 4)
            return false;
        for (int i = 0; i < 4; i++) {
            if (! Character.isLowerCase(s.charAt(i)) && ! Character.isUpperCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
