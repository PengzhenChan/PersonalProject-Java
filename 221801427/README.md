# PersonalProject-Java


----------


## 功能简介
输入英文文本（只考虑Ascii码，汉字不需考虑）
 <br>1. 统计字符数（空格，水平制表符，换行符，均算字符）
 <br>2. 统计有效行数（任何包含非空白字符的行，都需要统计）
 <br>3. 统计单词数（至少以4个英文字母开头，跟上字母数字符号，单词以分隔符分割，不区分大小写)
 <br>4. 统计频率最高的10个单词的出现次数（值降序，键字典序）
<br> 按以上顺序逐行打印至输出文本，输出的格式如下

> characters: number <br>
words: number <br>
lines: number <br>
word1: number <br>
word2: number <br>
... 


----------


## 运行方式
运行cmd

    javac -encoding UTF-8 WordCount.java
    java WordCount input.txt output.txt


----------


## 作业链接
[寒假作业(2/2) 作业要求](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)


----------


## 博客链接
[我的博客](https://www.cnblogs.com/railgunSE/)


----------


## 签入记录
### 第一次commit
建立了基本架构，制定了代码规范
### 第二次commit
实现字符计算，以及对应的文件读写功能
### 第三次commit
重写了算法实现，增加计算行数功能
### 第四次commit
增加了计算有效单词总数的功能
### 第五次commit
增加了统计词频功能，输出功能也完整了
### 第六次commit
改用StringBuilder提高性能，修改、增加了一些代码规范上的细节
### 第七次commit
通过去删去不必要的字符操作，改进了部分算法效率
### 第八次commit
修改了读取算法与计算行数算法以适应读取\r的需要
### 第九次commit
输出格式，细节修改，所需功能基本完成完备
### 第十次commit
提取了读取字符串并拆分为有效单词Map的方法，提高效率
### 第十一次commit
改用NIO读取文件以提升性能
### 第十二次commit
优化了性能：采用StringTokenizer分割字符串，采用多线程<br>删去了之前尝试各类算法时遗留的不必要的导入
### 第十三次commit
合并计算字符数与单词数的类，添加注释与README文本<br>更改编码模式为UTF-8解决提交时的乱码问题




