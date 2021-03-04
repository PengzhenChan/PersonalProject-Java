import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountCore {
    private String inPath;
    private Map<String, Integer> map;
    private Data data;
    private int cnt;

    CountCore(String inPath) {
        data = new Data();
        cnt=0;
        map = new HashMap<String, Integer>(100000, 0.75f);
        this.inPath = inPath;
        if (inPath == null) {
            return;
        }
        File file = new File(inPath);
        if (!file.exists())
            this.inPath = null;
    }

    public String getInPath() {
        return inPath;
    }

    public int getWordCount(String key) {
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

            data.setCharacters(cnt);
            return cnt;
        } catch (Exception ie) {
            System.out.println("file is't exist");
        }
        return 0;
    }

    public int getWordsCount() {
        int cnt = 0;
        String pattern = "[A-Za-z]{4,}[A-Za-z0-9]*";
        Matcher m = null;
        String s = null;
        String[] s1;

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(inPath)))) {
            Pattern r = Pattern.compile(pattern);
            while ((s = reader.readLine()) != null) {
                s = s.trim();
                if(s.length()<4){
                    continue;
                }
                s1 = s.split("[^a-zA-Z0-9]");
                for (String tp : s1) {
                    m = r.matcher(tp);
                    if (tp.length()>=4&&m.matches()) {
                        cnt++;
                        map.merge(tp.toLowerCase(), 1, Integer::sum);
                    }
                }
            }
            data.setWords(cnt);
            return cnt;
        } catch (Exception ie) {
            System.out.println("file is't exist");
        }
        return 0;
    }

    private void getAllDatas() {
        String s;
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3=0;
        String[] s1;
        Pattern r = Pattern.compile("[A-Za-z]{4,}[A-Za-z0-9]*");
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(inPath)))) {
            while ((s = reader.readLine()) != null) {
                cnt2 += s.length();
                ++cnt2;
                s=s.trim();
                if (!"".equals(s)) {
                    ++cnt1;
                }
                Matcher m;
                if(s.length()>=4){
                    s1 = s.split("[^a-zA-Z0-9]");
                    for (String tp : s1) {
                        m = r.matcher(tp);
                        if (tp.length()>=4&&m.matches()) {
                            ++cnt3;
                            map.merge(tp.toLowerCase(), 1, Integer::sum);
                        }
                    }
                }
            }
            data.setCharacters(--cnt2);
            data.setLines(cnt1);
            data.setWords(cnt3);
        } catch (Exception ie) {
            System.out.println("file is't exist");
        }

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
            data.setLines(cnt);
            return cnt;
        } catch (Exception ie) {
            System.out.println("file is't exist");
        }
        return 0;
    }

    void quickSort(String[] list, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        int k = i + 1;
        int x = map.get(list[l]);
        while (k <= j) {
            if (map.get(list[k]) < x) {
                String tp = list[j];
                list[j] = list[k];
                list[k] = tp;
                j--;
            } else if (map.get(list[k]) > x) {
                String tp = list[i];
                list[i] = list[k];
                list[k] = tp;
                k++;
                i++;
            } else {
                k++;
            }
        }
        quickSort(list, l, i - 1);
        quickSort(list, j + 1, r);
    }

    void quickSort_1(String[] list, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        int k = i + 1;
        String x = list[l];
        while (k <= j) {
            if (list[k].compareTo(x) > 0) {
                String tp = list[j];
                list[j] = list[k];
                list[k] = tp;
                j--;
            } else if (list[k].compareTo(x) < 0) {
                String tp = list[i];
                list[i] = list[k];
                list[k] = tp;
                k++;
                i++;
            } else {
                k++;
            }
        }
        quickSort_1(list, l, i - 1);
        quickSort_1(list, j + 1, r);

    }

    public String[] getPopularWord() {
        String[] list = map.keySet().toArray(new String[0]);
        if (list.length == 0) {
            return list;
        }
        quickSort(list, 0, list.length - 1);
        int cnt = 0;
        int x = 0;
        int integer = map.get(list[0]);
        for (int i = 0; i < list.length; i++) {
            if (map.get(list[i]) != integer) {
                quickSort_1(list, x, i - 1);
                cnt += i - x;
                x = i;
                integer = map.get(list[i]);

            }
            if (cnt > 10)
                break;
        }
        quickSort_1(list, x, list.length - 1);
        data.setStrings(list);
        return list;
    }

    public Data getData() {
        if(cnt==0){
            cnt=1;
            getAllDatas();
            getPopularWord();
        }
        return data;
    }
}