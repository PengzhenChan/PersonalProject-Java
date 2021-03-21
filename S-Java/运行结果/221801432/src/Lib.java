import java.io.*;
import java.util.ArrayList;

public class Lib {

    public static int countCharacters(String inputFileName,String outputFileName) {
        int i = 0;
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFileName,false));
            int b = inputFile.read();
            while (b != -1) {
                i++;
                b = inputFile.read();
            }
            String str = "characters: " + i +"\n";
            outputFile.write(str);
            inputFile.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static ArrayList<MyWords> countWords(String inputFileName, String outputFileName) {
        ArrayList<MyWords> myWordsList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
            int b = 0;
            int numOfWords = 0;
            while (true) {
                String str = "";
                boolean isItAWord = false;
                for (int i=0; i<4; i++) {
                    b = reader.read();
                    if (b ==-1) {
                        break;
                    }
                    if (Character.isLetter((char)b)) {
                        str += Character.toLowerCase((char)b);
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
                if (isItAWord) {
                    b = reader.read();
                    while (b != -1 && Character.isLetterOrDigit((char)b)) {
                        str = str + Character.toLowerCase((char)b);
                        b = reader.read();
                    }
                    handleWord(str, myWordsList);
                } else if (b != -1 && !Character.isLetterOrDigit((char)b)) {
                    continue;
                } else {
                    b = reader.read();
                    while (b != -1 && Character.isLetterOrDigit((char)b)) {
                        b = reader.read();
                    }
                }
            }

            writer.write("words: " + numOfWords + "\n");
            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return myWordsList;
    }

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
            writer.write("lines: " + numOfLines + "\n");
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                writer.write(myWordsArrayList.get(i).getContent() + ": " +
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

    public static void handleWord(String str, ArrayList<MyWords> myWordsList) {
        for (int i=0; i<myWordsList.size() || myWordsList.size() == 0; i++) {
            if (myWordsList.size() == 0) {
                MyWords aWord = new MyWords(str);
                myWordsList.add(aWord);
                return;
            }
            if (myWordsList.get(i).getContent().equals(str)) {
                myWordsList.get(i).increaseFrequency();
                return;
            } else if (i == myWordsList.size() - 1){
                MyWords aWord = new MyWords(str);
                myWordsList.add(aWord);
                return;
            }
        }
    }

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
