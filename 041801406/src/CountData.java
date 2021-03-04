import java.util.*;

public class CountData {
    int countWord;
    int countLine;
    int countChar;
    HashMap<String, Integer> getWordFrequency;

    CountData() {
        countWord = 0;
        countLine = 0;
        countChar = 0;
        getWordFrequency = new HashMap<String, Integer>();
    }

    void setCountWord(int n) {
        countWord += n;
    }

    void setCountLine() {
        countLine++;
    }

    void setCountChar(int n) {
        countChar += n;
    }

    void setGetWordFrequency(HashMap<String, Integer> n) {
        for (String s : n.keySet()) {
            if (getWordFrequency.containsKey(s)) {
                getWordFrequency.put(s, getWordFrequency.get(s) + n.get(s));
            }
            else {
                getWordFrequency.put(s, 1);
            }
        }
    }

    int getCountWord() {
        return countWord;
    }

    int getCountLine() {
        return countLine;
    }

    int getCountChar() {
        return countChar;
    }

    List<Map.Entry<String, Integer>> getGetWordFrequency() {
        List<Map.Entry<String, Integer>> list = null;
        list = new ArrayList<Map.Entry<String, Integer>>(getWordFrequency.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o2.getValue().compareTo(o1.getValue());
                }
                else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });
        return list;
    }
}
