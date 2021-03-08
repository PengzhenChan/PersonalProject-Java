## 运行

## 功能简介

**统计某个文本文档当中的单词数，并将结果输出到另一个文件当中**

* workFunc包

  用于存放各类函数的包，其中working_set也在其中

  * working_set类中有getNumberOfWord方法，用于统计单词数

* tool包

  用于存放各类功能类函数

  * countF

    需要数数的函数

    * countCh返回传递字符串中的字符的多少
    * countR 某一行元素传递过来后，判断是否只有换行，只有换行则不算有效行
    * inLineFirst 将每一行传递过来的字符串数组处理后放入到map中返回
    * addInMap 将传递过来的map与需要的map进行比对，并合并
    * sortMap 将map按value值排序

  * IOF

    文件输入输出

    * fileRead 读入文件并处理到指定map中
    * hasEnterLastLine Panduan最后一行是否有换行
    * print 输出到指定文件中

  * judgeF

    需要判断的函数

    * isWord 判断是否为单词

* basic包

  存放基础函数包

  * scanIn 读入输入输出位置
  * removeSpaces 空白字符串转换
  * haveEnter 判断是否为空行

## 作业连接

[作业在这里](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672)

## 博客连接

[点击这里进入该作业的博客](https://www.cnblogs.com/yabi/p/14481878.html)