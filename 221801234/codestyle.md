# 代码风格

## 缩进

* 左大括号不单独占行，右大括号单独占行，内部相对缩进一个[Tab]

```java
void print(){
	System.out.println();
    if(true){
        System.out.println();
    }
}
```

## 变量命名

1. 代码中的命名不以下划线或美元符号开始，也不能以下划线或美元符号结束。

2. 代码中的命名不使用拼音，不使用拼音缩写，不使用拼音与英文混合的方式，不使用中文

## 每行最多字符数

* 每行最多字符数为100

## 函数最大行数

* 函数最大行数不超过25行

## 函数、类命名

* 方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格，遵从驼峰命名形式

* 类名使用UpperCamelCase风格，遵从驼峰命名形式

## 常量

* 常量命名全部大写，单词间用下划线隔开

## 空行规则

* 方法与方法之间空一行

* 变量与方法之间空一行
* 包名下空一行

```java
package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    private String inPath;
    private String outPath;
    private Map<String,Integer> map;

	public void print(){
		//...
	}
}
```

## 注释规则

* 方法上使用/**/
* 类上使用/***/
* 内部使用单行注释

## 操作符前后空格

* 运算符号(+,-,*,/,=)与变量之间用一个空格隔开
* ;后面有其他字符，则空一格

```java
int a = 3 + 4;
int b;
int c;
b = a + c;
for(int i = 0; i < n; i++)
```

## 其他规则

* 如果使用到了设计模式，在类名中体现出具体模式
* 枚举类名带上Enum后缀，枚举成员名称需要全大写，单词间用下划线隔开
* 抽象类命名使用Abstract开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾。接口使用Impl结尾