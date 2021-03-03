import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountCore {
    private String inPath;
    private Map<String, Integer> map;

    CountCore(String inPath) {
        map = new TreeMap<>();
        this.inPath = inPath;
    }
    public int getWordCount(String key){
        return map.get(key);
    }

    public int getCharCount() {
        int tp;
        int cnt = 0;
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(inPath)))) {
            while ((tp = reader.read()) != -1) {
                if (tp < 128) {
                    cnt++;
                }
            }
            return cnt;
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return 0;
    }

    public int getWordsCount() {
        int cnt = 0;
        String pattern = "[A-Za-z]{4,}[A-Za-z0-9]*";
        Matcher m = null;
        String s = null;
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(inPath)))) {
            Pattern r = Pattern.compile(pattern);
            while ((s = reader.readLine()) != null) {
                String[] s1 = s.split("[^a-zA-Z0-9]");
                for (String tp : s1) {
                    m = r.matcher(tp);
                    if (m.matches()) {
                        cnt++;
                        map.merge(tp.toLowerCase(), 1, Integer::sum);
                    }
                }
            }
            return cnt;
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return 0;
    }

    public int getValidLines() {
        String s;
        int cnt = 0;
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(inPath)))) {
            while ((s = reader.readLine()) != null) {
                if (!"".equals(s.trim())) {
                    cnt++;
                }
            }
            return cnt;
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return 0;
    }

    void quick_sort(String[] list, int l, int r) {
        if(l>=r) return;
        int i = l, j = r;
        int k = i+1;
        int x=map.get(list[l]);
        if(k<j){
            while (k<=j) {
                if (map.get(list[k]) < x){
                    String tp = list[j];
                    list[j] = list[k];
                    list[k] = tp;
                    j--;
                }
                else if (map.get(list[k]) > x){
                    String tp = list[i];
                    list[i] = list[k];
                    list[k] = tp;
                    k++;
                    i++;
                }else{
                    k++;
                }
            }
            if(r-l>20){
                quick_sort(list, l, i - 1);
            }else{
                quick_sort(list, l, i - 1);
                quick_sort(list, j + 1, r);
            }
        }
    }

    public String[] getPopularWord() {
        String[] list = map.keySet().toArray(new String[0]);
        quick_sort(list, 0, list.length - 1);
        return list;
    }
}