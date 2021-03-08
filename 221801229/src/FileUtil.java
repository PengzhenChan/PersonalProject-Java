import java.io.File;

/**
 * 作者： 221801229 Shy
 * 功能： 通过路径取得文件
 */
public class FileUtil
{
    //通过路径获取文件
    public File getFile(String  path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("file not found");
        }
        System.out.println("locate:"+path);
        return file;
    }
}
