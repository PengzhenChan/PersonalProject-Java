import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CountWord {
    FileInputStream inputStream;
    Map<String, Integer> words = new HashMap<>();

    int count = 0;

    public CountWord(FileInputStream in) {
        inputStream = in;
    }

    public int Count() throws IOException {
        String str = "";
        int i;
        while ((i = inputStream.read()) != -1) {
            if (Character.isLetterOrDigit(i)) {
                str = str + (char)i;
            } else {
                if (isWord(str)) {
                    count++;
                    if (!words.containsKey(str)) {
                        words.put(str.toLowerCase(), 1);
                    } else {
                        int value = words.get(str.toLowerCase());
                        words.put(str, value + 1);
                    }
                    str = "";
                }
            }
        }
        return count;
    }

    public LinkedHashMap<String, Integer> MostFreqWord() {
        if (words.isEmpty()) {
            return null;
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                int re = e2.getValue().compareTo(e1.getValue());
                if (re != 0) {
                    return re;
                } else {
                    return e1.getKey().compareTo(e2.getKey());
                }
            }
        });

        LinkedHashMap<String, Integer> topWords = new LinkedHashMap<>();

        for (int i = 0; i < 10; i++){
            topWords.put(list.get(i).getKey(), list.get(i).getValue());
        }

        return topWords;
    }

    private boolean isWord(String str) {
        return  str.length() >= 4 &&
                !Character.isDigit(str.charAt(0)) &&
                !Character.isDigit(str.charAt(1)) &&
                !Character.isDigit(str.charAt(2)) &&
                !Character.isDigit(str.charAt(3));
    }
}