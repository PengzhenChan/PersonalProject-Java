import java.util.*;

public class CompareRule implements Comparator<Word>
{
    public int compare(Word aWord,Word bWord)
    {
        if(aWord.GetFrequent() > bWord.GetFrequent())   //词频大的在前
        {
            return -1;
        }
        else if(aWord.GetFrequent() == bWord.GetFrequent())
        {
            if(aWord.GetWords().compareTo(bWord.GetWords()) < 0)    //字典序小的在前
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return 1;
        }
        
    }
}
