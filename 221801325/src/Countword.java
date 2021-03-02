public class Countword
{
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
            {
                num++;
            }
        }
       return num;

    }



}
