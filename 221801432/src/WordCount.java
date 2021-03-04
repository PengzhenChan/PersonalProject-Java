import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class MyWords {
    private String content;
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
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        //统计字符数量
        countCharacters(str[0], str[1]);
        //统计单词数量
        countWords(str[0],str[1]);
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
    //每一次read都要考虑是否到达了文件末尾
    public static ArrayList countWords(String inputFileName, String outputFileName) {
        //保存所有单词
        ArrayList myWordsList = new ArrayList<MyWords>();
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

}