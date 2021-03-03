# 词频统计

[toc]

## 运行

>javac *.java
>
>java WordCount filepath1(.txt) filepath2(.txt)

>```
>test.txt:
>uExUY6sYWxFVV14sx5Kq
>rx759rkSQ0Y92d gEik K5f
>94jCsy95
>JLDK a C r 40008ZIHH 7wSwrM
>p
>9EE7O2aE63
>h08Z p0kK YzgxF0879N7
>icvaNU8rKN8 6 N1u7M B n 7q6Q2gGH
>
>16Cb5BQfPbw2d6fqwarP1uSOpe e7
>x21LZr
>g
>
> N0Y3XImV0VK55s2tKMZ
>
>gTy 3
>P4tvq
>I 
>```

>结果:
>
>characters: 227
>words: 5
>lines: 15
>word1: geik
>word2: icvanu8rkn8
>word3: jldk
>word4: uexuy6sywxfvv14sx5kq
>word5: yzgxf0879n7

## 功能简介

### WordCount

#### 命令行输出

```java
/**
* 命令行输出
* @param cc 计算核心类
*/
public static void print(CountCore cc);
```

#### 文件写入

```java
/**
* 文件写入
* @param cc 计算核心类
*/
public static void print(CountCore cc);
```

### CountCore

#### 构造函数

```java
/**
 * 构造函数
 * @param inPath 需要读取的文件名
 */
CountCore(String inPath);
```

#### 获取字符数

```java
/**
 * 获取文件中的字符数
 * @return 文件中的字符数
 */
public int getCharCount();
```

#### 获取有效行数

```java
/**
 * 获取有效行数
 * @return 有效行数
 */
public int getValidLines();
```

#### 获取单词数

```java
/*获取单词数*/
/**
 * 获取单词数
 * @return 单词数
 */
public int getWordCount();
```

#### 获取出现次数最多的10个单词

```java
/**
 * 获取出现最多的10个单词
 * @return 10个单词序列, 按降序排序
 */
public String[] getPopularWord();
```

## 作业链接

[作业链接](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672)

## 博客链接

[博客链接]()