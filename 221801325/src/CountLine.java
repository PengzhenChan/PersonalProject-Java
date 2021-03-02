public class CountLine {
    public int countLine(String str )
    {
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;

    }

}
