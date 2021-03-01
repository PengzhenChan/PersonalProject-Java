/**
 * @ClassName Lib
 * @Description TODO
 * @Author Charley Chen
 * @DATE 2021/2/27 16:18
 * @Version 1.0
 **/
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Lib {
    public void countChars(String inputfileName,String outputfileName)
    {
        int charnumber=0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfileName));
            String strLine = null;
            int lineCount = 0;
            while(null != (strLine = bufferedReader.readLine())){
                charnumber+= strLine.length();
                lineCount++;


            }
            System.out.println("文件字符数为"+charnumber);
           // System.out.println("文件字符数统计成功！");
            File outfile =new File(outputfileName);

            //if file doesnt exists, then create it
            if(!outfile.exists()){
                outfile.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(outfile,true);
            fileWritter.write(Integer.toString(charnumber));
            fileWritter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

        public void countWords(String inputfileName, String outputfileName) {
            Map<String,Integer> wordarray=new HashMap<String,Integer>();
            int wordnumber = 0;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfileName));
                String strLine = null;
                int lineCount = 0;

                while (null != (strLine = bufferedReader.readLine())) {
                    int strindex = 0;
                    int strlength = strLine.length();
                    int wordlength = 0;
                    while (true) {
                        if(strindex<strlength&&isPartOfWord(strLine.charAt(strindex)) == true) {
                            wordlength++;
                            strindex++;
                        }
                        else
                        {
                            String tempword=strLine.substring(strindex-wordlength,strindex);
                            strindex++;
                            if(wordlength>=4)
                            {
                                if(wordarray.get(tempword)==null)
                                wordarray.put(tempword,1);
                                else
                                {
                                    wordarray.put(tempword,wordarray.get(tempword)+1);
                                }
                            }
                            break;
                        }
                    }

                    lineCount++;


                }
                wordnumber=wordarray.size(); //wrong

                System.out.println("文件单词数为" + wordnumber);
                System.out.println("文件单词数统计成功！");
                File outfile = new File(outputfileName);

                //if file doesnt exists, then create it
                if (!outfile.exists()) {
                    outfile.createNewFile();
                }

                //true = append file
                FileWriter fileWritter = new FileWriter(outfile, true);
                fileWritter.write(Integer.toString(wordnumber));
                fileWritter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public boolean isPartOfWord(char cha)
        {
            if((cha<='9'&&cha>='0')||(cha<='Z'&&cha>='A')||(cha<='z'&&cha>='a'))
                return true;
            else
                return false;
        }

    public void countMost()
    {

    }




}
