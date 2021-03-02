import java.util.*;

public class CompareRule implements Comparator<Word>
{
    public int compare(Word aWord,Word bWord)
    {
        if(aWord.GetFrequent() > bWord.GetFrequent())   //��Ƶ�����ǰ
        {
            return -1;
        }
        else if(aWord.GetFrequent() == bWord.GetFrequent())
        {
            if(aWord.GetWords().compareTo(bWord.GetWords()) < 0)    //�ֵ���С����ǰ
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
