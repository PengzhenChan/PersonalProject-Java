import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Lib {
    Map<String, Integer> map = new HashMap<String, Integer>();
    public List<Map.Entry<String, Integer>> countmostWord(String str)
    {

        String[] word = str.split("\\s+");
        for (int i = 0; i < word.length; i++)
        {
            word[i] = word[i].toLowerCase();
            System.out.println(word[i]);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> mapping2, Map.Entry<String, Integer> mapping1)
            {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> mapping2, Map.Entry<String, Integer> mapping1)
            {
                return mapping2.getKey().compareTo(mapping1.getKey());
            }
        });
        //按照出现次数降序输出（前10）
        Map.Entry<String, Integer> mapping = null;

        return list;
    }
    public int countword(String string)
    {
        int num = 0;
        String[] word = string.split("\\s+");
        for (int i = 0; i < word.length; i++)
        {
            word[i] = word[i].toLowerCase();
        }
        String regex = "^[a-z]{4,}.*";
        for (int i = 0; i < word.length; i++)
        {
            String temp = word[i];
            if (temp.matches(regex))
            {   num++;
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        return num;

    }
    public int charCount(String string) throws IOException {
        int characters = 0;
        String regex = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            characters++;
        }
        return characters;
    }
    public  int lineCount(String string)
    {
        int num_of_line=0;
        String[] line = string.split("\r\n|\r|\n");
        for (int i = 0;i<line.length;i++)
        {
            if(!line[i].equals("")||line[i]!=null)
            {
                num_of_line++;
            }

        }
        return num_of_line;
    }

}
