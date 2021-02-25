import java.io.File;
import java.io.IOException;

/**
 * 接收命令行参数并执行功能
 * main函数入口
 *
 * @author 李星源
 * @date 2021/02/23
 */
public class WordCount {

    public static void main(String[] args) {
        ExceptionInfo info = argsLegal(args);
        if (info.getCode() != 0){
            System.err.println(info.getMessage());
        }
        if (info.getCode() == 1){
            return;
        }
    }

    private static ExceptionInfo argsLegal(String[] args){
        // 输入参数0个
        if (args.length == 0){
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
        if (!inputFile.getName().endsWith(".txt")){
            return ExceptionInfo.ILLEGAL_INPUT_FILE;
        }

        File outputFile = new File(args[1]);
        // 输出文件后缀名不是.txt
        if (!inputFile.getName().endsWith(".txt")){
            return ExceptionInfo.ILLEGAL_OUTPUT_FILE;
        }
        // 输出文件不存在
        if (!outputFile.exists()){
            File root = outputFile.getParentFile();
            if (!root.exists()){
                root.mkdirs();
            }
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                return ExceptionInfo.OUTPUT_FILE_ERROR;
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
