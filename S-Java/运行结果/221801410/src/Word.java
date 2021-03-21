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
    
    public void SetWords(String word)
    {
        words = word;
    }
    
    public void SetFrequent(int fre)
    {
        frequent = fre;
    }
    
    public String GetWords()
    {
        return words;
    }
    
    public int GetFrequent()
    {
        return frequent;
    }
    
    public void AddFrequent()
    {
        frequent = frequent + 1;
    }
}
