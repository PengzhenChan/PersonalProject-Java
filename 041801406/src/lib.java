import java.io.*;
import java.util.Calendar;

public class lib {
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
                /*不使用.read()来判断 因为会读取首字母*/
                while ((s = br.readLine()) != null) {
                    for (int i = 0; i < s.length(); i++) {
                        if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i))) {
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
