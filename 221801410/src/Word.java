import java.util.*;
import java.io.*;

public class Word {
    private String words;
    private int frequent;
    
    public Word()
    {
        words = "";
        frequent = 0;
    }
    
    public Word(String word,int fre)
    {
        words = word;
        frequent = fre;
    }
    
    public void setWords(String word)
    {
        words = word;
    }
    
    public void setFrequent(int fre)
    {
        frequent = fre;
    }
    
    public String getWords()
    {
        return words;
    }
    
    public int getFrequent()
    {
        return frequent;
    }
}
