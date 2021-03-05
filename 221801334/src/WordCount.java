import java.io.File;
import java.io.IOException;

/**
 * 接收命令行参数并执行功能
 * main函数入口
 *
 * @author 李星源221801334
 * @date 2021/02/23
 */
public class WordCount {

    public static void main(String[] args) {
        ExceptionInfo info = argsLegal(args);
        if (info.getCode() != 0){
            System.out.println(info.getMessage());
        }
        if (info.getCode() == 1){
            return;
        }

        new WordOperationImpl(new File(args[0]), new File(args[1])).countAll();
    }

    /**
     * 判断参数是否合法
     *
     * @param args 参数
     * @return 异常信息
     */
    private static ExceptionInfo argsLegal(String[] args){
        // 输入参数0个
        if ((args == null) || (args.length == 0)){
            return ExceptionInfo.PARAMETER_ZERO;
        }
        // 输入参数1个
        if (args.length == 1){
            return ExceptionInfo.PARAMETER_ONE;
        }

        File inputFile = new File(args[0]);
        // 输入文件不存在
        if (!inputFile.exists()){
            return ExceptionInfo.INPUT_FILE_NOT_EXIST;
        }
        // 输入文件后缀名不是.txt
        if (!args[0].endsWith(".txt")){
            return ExceptionInfo.ILLEGAL_INPUT_FILE;
        }

        File outputFile = new File(args[1]);
        // 输出文件后缀名不是.txt
        if (!args[1].endsWith(".txt")){
            return ExceptionInfo.ILLEGAL_OUTPUT_FILE;
        }
        // 输出文件不存在
        if (!outputFile.exists()){
            File root = outputFile.getParentFile();
            if ((root != null) && (!root.exists())){
                root.mkdirs();
            }
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                return ExceptionInfo.OUTPUT_FILE_CREATE_ERROR;
            }
            return ExceptionInfo.CREATE_OUTPUT_FILE;
        }
        //输出文件中已经有内容
        if (outputFile.length() > 0){
            return ExceptionInfo.CLEAR_OUTPUT_FILE;
        }
        return ExceptionInfo.OK;
    }

}
