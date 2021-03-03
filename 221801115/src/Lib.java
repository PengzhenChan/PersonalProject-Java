import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib 
{
    private static final String REGEX = "(?<=[^a-zA-Z0-9])[a-zA-Z]{4}[a-zA-Z0-9]{0,}";

    private String inputPath;

    public Lib(String inputPath) 
    {
        this.inputPath = inputPath;
    }

    public int countCharacters(String inputPath) 
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
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                br.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return characters;
    }

    public int countNumberOfWords(String inputPath) 
    {
        int num = 0;
        BufferedReader br = null;
        try 
        {
            br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            String text = "";

            while ((tmp = br.readLine()) != null) 
            {
                text += tmp + "\n";
            }

            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) 
            {
                num++;
            }

        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                br.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return num;
    }

    public int countLines(String inputPath) 
    {
        int lines = 0;
        BufferedReader br = null;
        try 
        {
            br = new BufferedReader(new FileReader(inputPath));
            String tmp;

            while ((tmp = br.readLine()) != null) 
            {
                if (!tmp.trim().equals("")) 
                {
                    lines++;
                }
            }

        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                br.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return lines;
    }

    public List<Map.Entry<String, Integer>> countWordFrequency(String inputPath) 
    {
        Map<String, Integer> map = new HashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        BufferedReader br = null;
        try 
        {
            br = new BufferedReader(new FileReader(inputPath));
            String tmp;
            String text = "";

            while ((tmp = br.readLine()) != null) 
            {
                text += tmp + "\n";
            }

            String word;
            Pattern pattern = Pattern.compile(REGEX);
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
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                br.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    public List<Map.Entry<String, Integer>> sortWord(List<Map.Entry<String, Integer>> list) 
    {
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

        return list;
    }

    public void writeFile(String outputPath) 
    {
        BufferedWriter bw = null;
        try 
        {
            bw = new BufferedWriter(new FileWriter(outputPath));

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

        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                bw.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
