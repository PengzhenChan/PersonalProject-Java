import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lib {
    private String word;
    HashMap<String,Integer> hashMap;
    private int line;
    long wordsNum;
    Pattern pattern;
    Matcher matcher;
    public Lib(String word){
          wordsNum=0;
          this.word=word;
          line=0;
    }
    public int getLines(){
        String s="\\n";
        pattern=Pattern.compile(s);
        matcher=pattern.matcher(word);
        while(matcher.find()){
            line++;
        }
        return line;
    }
    public long getChars(){
        return word.length() ;
    }
    public long getWordsNum(){
        word=word.replaceAll("[^A-Za-z0-9]"," ");
        word=word.toLowerCase();
        hashMap=new HashMap<>();
        String s="[a-z]{4}[A-za-z0-9]*";
        pattern=Pattern.compile(s);
        matcher=pattern.matcher(word);
        while(matcher.find()){
            String key=matcher.group(0);
            Integer value=hashMap.get(key);
            System.out.println(key);
            if(value!=null){
                hashMap.put(key,value+1);
            }
            else{
                hashMap.put(key,1);
            }
            wordsNum++;
        }
        return wordsNum;
    }

    public String getTopWords(){
         String returnword="";
        hashMap = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
         hashMap = hashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
         Iterator iterator=hashMap.entrySet().iterator();
         int i=0;
         while(iterator.hasNext()&&i<10) {
             Map.Entry entry = (Map.Entry) iterator.next();
             Object key = entry.getKey();
             Object val = entry.getValue();
             i++;
             returnword+=key+":"+val+"\n";
             System.out.println(key + ":" + val);
         }
         return returnword;
    }
    public void setWord(String word){
        this.word=word;
    }

}
