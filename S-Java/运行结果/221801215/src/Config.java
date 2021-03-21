public class Config {
    public static final char NEW_LINE_CHAR = '\n';
    public static final String CHARSET="UTF-8";

    public static final String FILE_NOT_FOUND_EXCEPTION_TIP = "未找到文件，请检查文件路径或文件名";
    public static final String UNSUPPORTED_ENCODING_EXCEPTION_TIP = "源代码被修改，当前打开文件使用的编码格式不正确，当前编码格式为"
            + CHARSET + "\n请修改Config.CHARSET";
    public static final String IO_EXCEPTION_TIP = "文件读写出错，请稍后重试";

    public static final String COMMANDLINE_HELP = "请输入正确的执行命令：\n" + "java WordCount <输入文件路径> <输出文件路径>";
    public static final String COMMANDLINE_SUCCESS_TIP = "执行完成";
}
