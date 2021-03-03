public class CountLine {
    public static int countLine(String text){
        int num = 0;
        String[] lines = text.split("\r\n|\r|\n");

        for(int i=0; i<lines.length; i++){
            if(isValidate(lines[i])){
                num++;
            }
        }
        return num;
    }

    /*判断该行是否有效 无效则返回false 有效则返回true*/
    public static boolean isValidate(String line){
        if(line==null || "".equals(line)){
            return false;
        }
        else{
            return true;
        }
    }

}
