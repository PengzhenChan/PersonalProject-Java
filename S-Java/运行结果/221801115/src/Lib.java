import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib
{
    //匹配单词的正则表达式
    private static final String REGEX = "(?<=[^a-zA-Z0-9])[a-zA-Z]{4}[a-zA-Z0-9]*";
    //输入文件的路径
    private final String inputPath;

    public Lib(String inputPath)
    {
        this.inputPath = inputPath;
    }

    /* 统计文件的字符数 */
    public int countCharacters()
    {
        int characters = 0;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(inputPath));

            while ((br.read()) != -1)
            {
                characters++;
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                assert br != null;
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return characters;
    }

    /* 统计文件的单词总数 */
    public int countNumberOfWords()
    {
        int num = 0;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            StringBuilder text = new StringBuilder();

            while ((tmp = br.readLine()) != null)
            {
                text.append("\n").append(tmp);
            }

            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(text.toString());

            while (matcher.find())
            {
                num++;
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                assert br != null;
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return num;
    }

    /* 统计文件的有效行数 */
    public int countLines()
    {
        int lines = 0;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(inputPath));
            int tmp;
            String str = "";

            while ((tmp = br.read()) != -1)
            {
                str += String.valueOf((char)tmp);
            }
            
            String[] texts = str.split("\\\\n");
            lines = texts.length;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                assert br != null;
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return lines;
    }

    /* 统计单词频率并返回一个存储对应数据的ArrayList */
    public List<Map.Entry<String, Integer>> countWordFrequency()
    {
        Map<String, Integer> map = new HashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            StringBuilder text = new StringBuilder();

            while ((tmp = br.readLine()) != null)
            {
                text.append("\n").append(tmp);
            }

            String word;
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(text.toString());

            while (matcher.find())
            {
                word = matcher.group().toLowerCase();
                if (map.containsKey(word))
                {
                    map.put(word, map.get(word) + 1);
                }
                else
                {
                    map.put(word, 1);
                }
            }
            
            //通过ArrayList构造函数把map.entrySet()转换成list
            list = new ArrayList<>(map.entrySet());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                assert br != null;
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    /* 给单词按着频率优先，字典序其次的顺序排序 */
    public List<Map.Entry<String, Integer>> sortWords(List<Map.Entry<String, Integer>> list)
    {
        list.sort((a, b) -> {
            if (a.getValue().equals(b.getValue()))
            {
                return a.getKey().compareTo(b.getKey());
            }
            else
            {
                return b.getValue().compareTo(a.getValue());
            }
        });

        return list;
    }

    /* 用迭代器得到一个存储十个单词及其频率的字符串并返回 */
    public String getSortedWordsAndFrequency() {
        StringBuilder tmp = new StringBuilder();
        List<Map.Entry<String, Integer>> list = sortWords(countWordFrequency());
        Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
        Map.Entry<String, Integer> entry;

        for (int i = 0; iterator.hasNext() && i < 10; i++)
        {
            entry = iterator.next();
            tmp.append("\n").append(entry.getKey()).append(": ").append(entry.getValue());
        }

        return tmp.toString();
    }

    /* 写入文件 */
    public void writeFile(String outputPath)
    {
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(outputPath));

            bw.write("characters: " + countCharacters());
            bw.write("\nwords: " + countNumberOfWords());
            bw.write("\nlines: " + countLines());
            bw.write(getSortedWordsAndFrequency());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                assert bw != null;
                bw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
