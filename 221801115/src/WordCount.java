import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount 
{
    private static String inputPath;/*输入路径*/
    private static String outputPath;/*输出路径*/
	public static void main(String[] args) 
    {
        inputPath = "C:\\Users\\Lin Minghao\\Desktop\\" + args[0];
        outputPath = "C:\\Users\\Lin Minghao\\Desktop\\" + args[1];
        writeFile(outputPath);
	}

    public static int countCharacters(String inputPath) 
    {
        int characters = 0;

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));

            while ((br.read()) != -1) 
            {
                characters++;
            }

            br.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return characters;
    }

    public static int countNumberOfWords(String inputPath)
    {
        int num = 0;

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            String text = "";

            while ((tmp = br.readLine()) != null)
            {
                text += tmp + "\n";
            }

            String regex = "(?<=[^a-zA-Z0-9])[a-zA-Z]{4}[a-zA-Z0-9]{0,}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find())
            {
                num++;
            }

            br.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return num;
    }

    public static int countLines(String inputPath)
    {
        int lines = 0;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            
            while ((tmp = br.readLine()) != null)
            {
                if (!tmp.trim().equals(""))
                {
                    lines++;
                }
            }

            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lines;
    }

    public static List<Map.Entry<String, Integer>> countWordFrequency(String inputPath)
    {
        Map<String, Integer> map = new HashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            String text = "";

            while ((tmp = br.readLine()) != null)
            {
                text += tmp + "\n";
            }

            String regex = "(?<=[^a-zA-Z0-9])[a-zA-Z]{4}[a-zA-Z0-9]{0,}";
            String word;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

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

            list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
            br.close();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Map.Entry<String, Integer>> sortWord(List<Map.Entry<String, Integer>> list) {
        try {
            list.sort(new Comparator<Map.Entry<String, Integer>>() 
            {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) 
                {
                    if (a.getValue() == b.getValue())
                    {
                        return a.getKey().compareTo(b.getKey());
                    }
                    else
                    {
                        return b.getValue().compareTo(a.getValue());
                    }
                }
            });
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return list;
    }

    public static void writeFile(String outputPath) 
    {
        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath));

            bw.write("characters: " + countCharacters(inputPath));
            bw.write("\nwords: " + countNumberOfWords(inputPath));
            bw.write("\nlines: " + countLines(inputPath));

            List<Map.Entry<String, Integer>> list = sortWord(countWordFrequency(inputPath));
            Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
            Map.Entry<String, Integer> entry;
            for (int i = 0; iterator.hasNext() && i < 10  ; i++) 
            {
                entry = iterator.next();
                bw.write("\n" + entry.getKey() + ": " + entry.getValue());
            }
            bw.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}