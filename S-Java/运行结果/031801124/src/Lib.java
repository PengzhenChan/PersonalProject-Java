/**
 * @ClassName Lib
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/2/27 16:18
 * @Version 1.0
 **/
import java.io.*;
import java.util.*;

public class Lib {
    public void countChars(String inputfileName, String outputfileName) {
        long charnumber = 0;
        try {
            File inputfile = new File(inputfileName);
            Reader reader = null;
            File outfile = new File(outputfileName);
            reader = new InputStreamReader(new FileInputStream(inputfile));
            //if file doesnt exists, then create it
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (tempchar <= 127 && tempchar >= 0) {
                    charnumber++;
                }
            }
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(outfile, true);
            fileWritter.write("characters: " + Long.toString(charnumber));
            fileWritter.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("文件打开失败");
        }
    }

    public void countWords(String inputfileName, String outputfileName) {
        int wordnumber = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfileName));
            String strLine = null;
            int lineCount = 0;
            while (null != (strLine = bufferedReader.readLine())) {
                int strindex = 0;
                int strlength = strLine.length();
                int wordlength = 0;
                while (strindex < strlength) {
                    if (isPartOfAlphaWord(strLine.charAt(strindex)) == true && wordlength < 4) {
                        wordlength++;
                        strindex++;
                    } else if (isPartOfWord(strLine.charAt(strindex)) == true && wordlength >= 4) {
                        wordlength++;
                        strindex++;
                    } else {
                        if (wordlength >= 4) {
                            wordnumber++;
                        }
                        strindex++;
                        wordlength = 0;
                    }
                }
                if (wordlength >= 4) {
                    wordnumber++;
                }
            }
            File outfile = new File(outputfileName);
            //if file doesnt exists, then create it
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(outfile, true);
            fileWritter.write("\n" + "words: " + Integer.toString(wordnumber));
            fileWritter.close();

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("文件打开失败");
        }
    }

    public boolean isPartOfWord(char cha) {
        if ((cha <= '9' && cha >= '0') || (cha <= 'Z' && cha >= 'A') || (cha <= 'z' && cha >= 'a'))
            return true;
        else
            return false;
    }

    public boolean isPartOfAlphaWord(char cha) {
        if ((cha <= 'Z' && cha >= 'A') || (cha <= 'z' && cha >= 'a'))
            return true;
        else
            return false;
    }

    public void countMost(String inputfileName, String outputfileName) {
        Map<String, Integer> wordarray = new HashMap<String, Integer>();
        int wordnumber = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfileName));
            String strLine = null;
            int lineCount = 0;
            while (null != (strLine = bufferedReader.readLine())) {
                int strindex = 0;
                int strlength = strLine.length();
                int wordlength = 0;
                while (strindex < strlength) {
                    if (isPartOfAlphaWord(strLine.charAt(strindex)) == true && wordlength < 4) {
                        wordlength++;
                        strindex++;
                    } else if (isPartOfWord(strLine.charAt(strindex)) == true && wordlength >= 4) {
                        wordlength++;
                        strindex++;
                    } else {
                        if (wordlength >= 4) {
                            String tempword = strLine.substring(strindex - wordlength, strindex).toLowerCase();
                            if (wordarray.get(tempword) == null) {
                                wordarray.put(tempword, 1);
                            } else {
                                wordarray.put(tempword, wordarray.get(tempword) + 1);
                            }
                        }
                        strindex++;
                        wordlength = 0;
                    }
                }

                if (wordlength >= 4) {
                    String tempword = strLine.substring(strindex - wordlength, strindex).toLowerCase();

                    if (wordarray.get(tempword) == null) {
                        wordarray.put(tempword, 1);
                    } else {
                        wordarray.put(tempword, wordarray.get(tempword) + 1);
                    }
                }
                lineCount++;
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordarray.entrySet()); //转换为list
            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    if (o2.getValue() != o1.getValue())
                        return o2.getValue().compareTo(o1.getValue());
                    else
                        return o1.getKey().compareTo(o2.getKey());
                }
            });
            File outfile = new File(outputfileName);
            //if file doesnt exists, then create it
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(outfile, true);
            if (list.size() < 10) {
                for (int i = 0; i < list.size(); i++) {
                    fileWritter.write("\n" + list.get(i).getKey() + ": " + list.get(i).getValue());
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    fileWritter.write("\n" + list.get(i).getKey() + ": " + list.get(i).getValue());
                }
            }
            fileWritter.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("文件打开失败");
        }
    }

    public void countLines(String inputfileName, String outputfileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfileName));
            String strLine = null;
            int lineCount = 0;
            while (null != (strLine = bufferedReader.readLine())) {
                for(int i=0;i<strLine.length();i++)
                {
                    if(strLine.charAt(i)!='\t'&&strLine.charAt(i)!='\r'&&strLine.charAt(i)!=' ') {
                        lineCount++;
                        break;
                    }
                }
            }
            File outfile = new File(outputfileName);
            //if file doesnt exists, then create it
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(outfile, true);
            fileWritter.write("\n" + "lines: " + Integer.toString(lineCount));
            fileWritter.close();
        } catch (Exception e) {
            System.out.println("文件打开失败");
        }
    }
}

