/**
 * 枚举所有的异常信息统一管理
 *
 * @author 李星源
 * @date 2021/02/25
 */
public enum ExceptionInfo {
    OK(0, "OK"),

    PARAMETER_ZERO(1, "请输入输入、输出文件的路径"),
    PARAMETER_ONE(1, "请输入输出文件的路径"),
    INPUT_FILE_NOT_EXIST(1, "输入文件不存在"),
    ILLEGAL_INPUT_FILE(1, "输入文件不是txt文件"),
    ILLEGAL_OUTPUT_FILE(1, "输出文件不是txt文件"),
    OUTPUT_FILE_CREATE_ERROR(1, "输出文件创建失败"),
    READ_FILE_ERROR(1, "文件读入失败"),
    WRITE_FILE_ERROR(1, "文件写入失败"),

    CREATE_OUTPUT_FILE(2, "输出文件不存在，已自动创建"),
    CLEAR_OUTPUT_FILE(2, "已自动清空输出文件原有内容");

    // 异常分类标识码
    private int code;
    // 异常说明
    private String message;

    ExceptionInfo(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
