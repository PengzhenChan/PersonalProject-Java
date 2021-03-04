import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class MyWords {
    private final String content;
    private int frequency;

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increaseFrequency() {
        frequency++;
    }

    public MyWords(String str) {
        content = str;
        frequency = 1;
    }
}

public class WordCount {
    //main函数入口
    public static void main(String[] args) {
        ArrayList<MyWords> myWordsArrayList;
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        //统计字符数量
        countCharacters(str[0], str[1]);
        //统计单词数量，获得单词序列
        myWordsArrayList = countWords(str[0], str[1]);
        //统计有效行数
        countLines(str[0], str[1]);
        //统计词频
        countFrequency(str[1], myWordsArrayList);
    }

    //统计字符数量，并返回字符数量
    public static int countCharacters(String inputFileName,String outputFileName) {
        //变量i统计字符数量
        int i = 0;
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFileName,false));
            int b = inputFile.read();
            while (b != -1) {
                i++;
                b = inputFile.read();
            }
            String str = "characters:" + i +"\n";
            outputFile.write(str);
            inputFile.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    //统计单词数量，并返回 单词对象的ArrayList
    public static ArrayList<MyWords> countWords(String inputFileName, String outputFileName) {
        //保存所有单词
        ArrayList<MyWords> myWordsList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
            int b = 0;
            //单词数量
            int numOfWords = 0;
            while (true) {
                //用来保存1个完整单词
                String str = "";
                //判断是否为1个单词
                boolean isItAWord = false;
                //这个for循环是判断单词的逻辑
                for (int i=0; i<4; i++) {
                    b = reader.read();
                    if (b ==-1) {
                        break;
                    }
                    if (Character.isLetter((char)b)) {
                        str += Character.toLowerCase((char)b);
                        //i=3时说明有4个连续的字母，这是一个单词
                        if (i == 3) {
                            isItAWord = true;
                            numOfWords++;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (b == -1) {//如果是末尾，结束
                    break;
                }
                //是单词和不是单词，两种处理路线
                //是单词，则将它补充完整。不是单词，将指针放到下一个分割符后面
                if (isItAWord) {
                    b = reader.read();
                    //如果刚好读到末尾或分割符，那这4个字母就是1个单词,str不做处理
                    //这里如果遇到文件结尾或者分割符，while循环结束
                    while (b != -1 && Character.isLetterOrDigit((char)b)) {
                        str = str + Character.toLowerCase((char)b);
                        b = reader.read();
                    }
                    //将单词存入ArrayList 或者 增加词频
                    handleWord(str, myWordsList);
                } else if (b != -1 && !Character.isLetterOrDigit((char)b)) {//不是单词而且b是分割符，回到循环开头
                    continue;
                } else {//不是单词，且b不是末尾也不是分割符，则补齐单词
                    b = reader.read();
                    while (b != -1 && Character.isLetterOrDigit((char)b)) {
                        b = reader.read();
                    }
                }
            }//while

            writer.write("words:" + numOfWords + "\n");
            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return myWordsList;
    }

    //统计行数
    public static void countLines(String inputFileName, String outputFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
            int numOfLines = 0;
            String lineStr;
            lineStr = reader.readLine();
            while (lineStr != null) {
                if (isEmptyLine(lineStr)) {
                    lineStr = reader.readLine();
                    continue;
                } else {
                    numOfLines++;
                    lineStr = reader.readLine();
                }
            }
            writer.write("lines:" + numOfLines + "\n");
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //统计词频
    public static void countFrequency(String outputFileName, ArrayList<MyWords> myWordsArrayList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
            for (int i=0; i<myWordsArrayList.size() - 1; i++) {
                for (int j=i+1; j<myWordsArrayList.size(); j++) {
                    boolean flag = myWordsArrayList.get(j).getContent().
                                   compareTo(myWordsArrayList.get(i).getContent()) < 0;
                    if (flag) {
                        MyWords tempWord = myWordsArrayList.get(i);
                        myWordsArrayList.set(i, myWordsArrayList.get(j));
                        myWordsArrayList.set(j,tempWord);
                    }
                }
            }
            for( int i=0; i<myWordsArrayList.size() - 1; i++) {
                for (int j=i+1; j<myWordsArrayList.size(); j++) {
                    boolean flag = myWordsArrayList.get(j).getFrequency() >
                                   myWordsArrayList.get(i).getFrequency();
                    if (flag) {
                        MyWords tempWord = myWordsArrayList.get(i);
                        myWordsArrayList.set(i, myWordsArrayList.get(j));
                        myWordsArrayList.set(j,tempWord);
                    }
                }
            }
            for (int i=0; i<10; i++) {
                writer.write(myWordsArrayList.get(i).getContent() + ":" +
                             myWordsArrayList.get(i).getFrequency() + "\n");
                if (i == myWordsArrayList.size()-1) {
                    break;
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理单词，加入ArrayList中或者增加词频
    public static void handleWord(String str, ArrayList<MyWords> myWordsList) {
        for (int i=0; i<myWordsList.size() || myWordsList.size() == 0; i++) {
            if (myWordsList.size() == 0) {
                MyWords aWord = new MyWords(str);
                myWordsList.add(aWord);
                return;
            }
            //if-else:如果找到相同字符串，则
            if (myWordsList.get(i).getContent().equals(str)) {
                //增加词频
                myWordsList.get(i).increaseFrequency();
                return;
            } else if (i == myWordsList.size() - 1){
                MyWords aWord = new MyWords(str);
                myWordsList.add(aWord);
                return;
            }
        }
    }

    //判断是否为空行
    public static boolean isEmptyLine(String str) {
        for(int i=0; i<str.length(); i++) {
            String tempStr = str.substring(i, i+1);
            boolean flag = tempStr.equals(" ") ||
                           tempStr.equals("\n") ||
                           tempStr.equals("\r") ||
                           tempStr.equals("\t");
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}