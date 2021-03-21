**如何运行**

编译：在WordCount.java根目录下 cmd输入 javac -encoding utf8 WordCount.java

运行：在WordCount.class根目录下 cmd输入 java WordCount <输入文件路径> <输出文件路径>

**功能简介**

1. **统计文件的字符数（对应输出第一行）**：

   - 只需要统计Ascii码，汉字不需考虑
   - 空格，水平制表符，换行符，均**算**字符

2. 统计文件的**单词总数（对应输出第二行）**，单词：至少以**4**个英文字母开头，跟上字母数字符号，单词以分隔符分割，**不区分大小写**。

   - 英文字母： A-Z，a-z
   - 字母数字符号：A-Z， a-z，0-9
   - 分割符：空格，非字母数字符号
   - **例**：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词

3. 统计文件的**有效行数（对应输出第三行）**：任何包含**非空白**字符的行，都需要统计。

4. 统计文件中各**单词的出现次数（对应输出接下来10行）**，最终只输出频率最高的**10**个。

   - 频率相同的单词，优先输出字典序靠前的单词。

     > 例如，windows95，windows98和windows2000同时出现时，则先输出windows2000

   - 输出的单词统一为小写格式

**[作业链接](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672)**

**[博客链接](https://www.cnblogs.com/tarsss/p/14488489.html)**