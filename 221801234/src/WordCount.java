package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    private String inPath;
    private String outPath;
    private Map<String, Integer> map;

    WordCount(String inPath, String outPath) {
        map = new TreeMap<>();
        this.inPath = inPath;
        this.outPath = outPath;
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

    public int getWordCount() {
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
                    System.out.println(tp);
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


    public String[] getPopularWord() {
        String[] list = map.keySet().toArray(new String[0]);
        for (int i = list.length; i >= 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (map.get(list[j]) < map.get(list[j + 1])) {
                    String tp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = tp;
                }
            }
        }
        return list;
    }

    public void print() {
        System.out.println("characters: " + getCharCount());
        System.out.println("words: " + getWordCount());
        System.out.println("lines: " + getValidLines());
        String[] popularWord = getPopularWord();
        for (int i = 0; i < popularWord.length; i++)
            System.out.println("word" + (i + 1) + ": " + popularWord[i]);
    }

    public static void main(String[] args) {
        WordCount wc = new WordCount("src/com/company/test.txt", null);
        wc.print();
    }
}
