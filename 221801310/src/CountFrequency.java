import java.util.*;

public class CountFrequency {

    /*返回未排序的单词频率hashMap*/
    public static HashMap<String, Integer> countFrequency(String text){
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        List<String> legalWords = SplitWord.textToLegalWord(text);

        //将单词分隔开
        for(String tmp : legalWords){
            if(!hashMap.containsKey(tmp)){
                hashMap.put(tmp, 1);
            }
            else{
                hashMap.put(tmp, hashMap.get(tmp)+1);
            }
        }

        return hashMap;
    }

    /*hashMap本身不可排序 所以要先转换为list*/
    public static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> hashMap){
        Set<Map.Entry<String, Integer>> entry = hashMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entry);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        //再将list转化为LinkedHashMap 不用这步也可以
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String,Integer> entry1:list){
            linkedHashMap.put(entry1.getKey(),entry1.getValue());
        }
        return linkedHashMap;
    }

}
