## 如何运行

输入文件和输出文件以命令行参数传入,切记一定要输入完整和正确，不能只输入要统计的文件

例如我们在命令行窗口(cmd)中输入：

```
E://input.txt 
E://output.txt
```

这时，我们的程序会统计E://input.txt 的单词，并将结果输出到E://output.txt

<br>

## 功能简介

假设有一个软件每隔一小段时间会记录一次用户的搜索记录，记录为英文。

输入文件和输出文件以命令行参数传入。例如我们在命令行窗口(cmd)中输入：

```
//Java语言
java WordCount input.txt output.txt
```

则会统计input.txt中的以下几个指标 

1. **统计文件的字符数（对应输出第一行）**：

   - 只需要统计Ascii码，汉字不需考虑
   - 空格，水平制表符，换行符，均**算**字符

2. 统计文件的**单词总数（对应输出第二行）**，单词：至少以**4**个英文字母开头，跟上字母数字符号，单词以分隔符分割，**不区分大小写**

   - 英文字母： A-Z，a-z
   - 字母数字符号：A-Z， a-z，0-9
   - 分割符：空格，非字母数字符号
   - **例**：file123是一个单词， 123file不是一个单词。file，File和FILE是同一个单词

3. 统计文件的**有效行数（对应输出第三行）**：任何包含**非空白**字符的行，都需要统计。

4. 统计文件中各**单词的出现次数（对应输出接下来10行）**，最终只输出频率最高的**10**个

   - 频率相同的单词，优先输出字典序靠前的单词
   -  输出的单词统一为小写格式

   然后将统计结果输出到output.txt，输出的格式如下；其中`word1`和`word2` 对应具体的单词，`number`为统计出的个数；换行使用'\n'，编码统一使用UTF-8

   ```
   characters: number
   words: number
   lines: number
   word1: number
   word2: number
   ...
   ```

   <br>


## 作业链接

[软工实践寒假作业（2/2）]( https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672 )
<br>

## 博客链接 

[作业链接]( https://www.cnblogs.com/xyh-Tse/p/14461182.html )