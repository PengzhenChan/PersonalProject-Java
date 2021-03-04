import java.io.*;
import java.util.*;

public class lib {
    static boolean openFile(File file) {
        if (! file.exists()) {
            return false;
        }
        else {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String s = "";
                while ((s = br.readLine()) != null) {

                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return false;
            }
        }
    }

    static int countChar(File file) {
        int myCountChar = 0;
        if (! file.exists()) {
            return - 1;
        }
        else {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while (br.read() != 1) {
                    String s = br.readLine();
                    myCountChar += s.length();
                    myCountChar++;
                }
                return myCountChar;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return myCountChar;
            }
        }
    }

    static int countLine(File file) {
        int myCountLine = 0;
        if (! file.exists()) {
            return - 1;
        }
        else {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while (br.readLine() != null) {
                    myCountLine++;
                }
                return myCountLine;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return myCountLine;
            }
        }
    }

    static int countWord(File file) {
        if (! file.exists()) {
            return - 1;
        }
        else {
            int myWordCount = 0;
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String s;
                String word = "";
                while ((s = br.readLine()) != null) {
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
                    word = "";
                }
                return myWordCount;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return myWordCount;
            }
        }
    }

    static List<Map.Entry<String, Integer>> getWordFrequency(File file) {
        if (! file.exists()) {
            return null;
        }
        else {
            HashMap<String, Integer> myWordCount = new HashMap<String, Integer>();
            List<Map.Entry<String, Integer>> list = null;
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String s;
                String word = "";
                while ((s = br.readLine()) != null) {
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
                    word = "";
                }
                list = new ArrayList<Map.Entry<String, Integer>>(myWordCount.entrySet());
                list.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return list;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                return list;
            }
        }
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
