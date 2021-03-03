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
        frequency = 0;
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

    //统计单词数量
    //每一次read都要考虑是否到达了文件末尾
    public static void countWords(String inputFileName, String outputFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
            int b = 0;
            //单词数量
            int numOfWords = 0;
            ArrayList myWordsList = new ArrayList<MyWords>();
            while (true) {
                //用来存单词
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
                        str += (char)b;
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
                if (b == -1) {
                    break;
                }
                //是单词和不是单词，两种处理路线
                //是单词，则将它补充完整。不是单词，将指针放到下一个分割符后面
                if (isItAWord) {
                    b = reader.read();
                    //如果刚好读到末尾或分割符，那这4个字母就是1个单词,str不做处理
                    //这里如果遇到文件结尾或者分割符，while循环结束
                    while (b != -1 && Character.isLetterOrDigit((char)b)) {
                        str = str + (char) b;
                        b = reader.read();
                    }
                } else {//如果不是末尾，也不是分割符，补齐单词。
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
    }

}