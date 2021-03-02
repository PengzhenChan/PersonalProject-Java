import java.util.*;

public class CompareRule implements Comparator<Word>
{
    public int compare(Word aWord,Word bWord)
    {
        if(aWord.GetFrequent() > bWord.GetFrequent())
        {
            return -1;
        }
        else if(aWord.GetFrequent() == bWord.GetFrequent())
        {
            return 0;
        }
        else
        {
            return 1;
        }
        
    }
}
