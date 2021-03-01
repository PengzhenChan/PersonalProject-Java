import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountChar {
    public int charCount(String string) throws IOException {
        int characters = 0;
        String regex = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            characters++;
        }
        return characters - 1;
    }

}
