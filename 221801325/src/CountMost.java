import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMost {
    public List<Map.Entry<String, Integer>> countWord(String str)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String[] word = str.split("\\s+");
        for (int i = 0; i < word.length; i++)
        {
            word[i] = word[i].toLowerCase();
        }
        String regex = "^[a-z]{4,}.*";
        for (int i = 0; i < word.length; i++)
        {
            String temp = word[i];
            if (temp.matches(regex))
            {
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }


        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> mapping2, Map.Entry<String, Integer> mapping1)
            {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        //按照出现次数降序输出（前10）
        Map.Entry<String, Integer> mapping = null;

        return list;
    }





    }

