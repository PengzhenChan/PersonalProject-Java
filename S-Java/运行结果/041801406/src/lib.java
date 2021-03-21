import java.io.*;
import java.util.*;

public class Lib {
    static CountData cd = new CountData();

    /*Used to open the file, count the number of words,
    the number of lines and the word frequency, and store them in the cd object*/
    static void openFile(File input) {
        try {
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                cd.setCountLine();
                cd.setCountWord(countWord(s));
                cd.setGetWordFrequency(getWordFrequency(s));
            }
            br.close();
            fr.close();
            return;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return;
        }
    }

    /*Used to return the object of CountData;*/
    static CountData getCd() {
        return cd;
    }

    /*Used to open the file to count the number of characters*/
    static int countChar(File file) {
        int myCountChar = 0;
        try {
            byte[] tem = new byte[20 * 1024];
            int len = tem.length;
            int bytes = 0;
            FileInputStream in = new FileInputStream(file);
            while ((bytes = in.read(tem, 0, len)) != - 1)
                myCountChar += bytes;
            cd.setCountChar(myCountChar);
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return myCountChar;
        }
    }

    /*Accept a string and count the number of words, and return an int type*/
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

    /*Accept a string and count the frequency of words, and return a HashMap*/
    static HashMap<String, Integer> getWordFrequency(String s) {
        String word = "";
        HashMap<String, Integer> myWordCount = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (isWord(word)) {
                    word = word.toLowerCase();
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
            word = word.toLowerCase();
            if (myWordCount.containsKey(word)) {
                myWordCount.put(word, myWordCount.get(word) + 1);
            }
            else
                myWordCount.put(word, 1);
        }
        return myWordCount;
    }

    /*Used to determine if it is a word,return a boolean type*/
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
