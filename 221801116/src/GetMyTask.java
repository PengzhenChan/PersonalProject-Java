import java.util.concurrent.Callable;

public class GetMyTask implements Callable<String> {

    private String path;
    private int mode;

    public GetMyTask(String path, int mode) {
        this.path = path;
        this.mode = mode;
    }

    @Override
    public String call() throws Exception {
        switch (mode){
            case 1:return CountChar.countChar(path);
            case 2:return CountWord.countWord(path);
            case 3:return CountLine.countLine(path);
            case 4:return CountWordRate.countWordRate(path);
            default:return null;
        }
    }
}
