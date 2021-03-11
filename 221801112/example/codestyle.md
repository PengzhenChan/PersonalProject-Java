# 编程规范

+ 格式规约：

  + 缩进：采用4个空格

    > 说明：如果使用tab缩进，必须设置1个tab为4个空格。IDEA设置tab为4个空格时，请勿勾选Use tab character；而在eclipse中，必须勾选insert spaces for tabs。
    >
    > IDEA取消勾选如下：

  + 每行最多字符数：单行字符数限制不超过 120个，超出需要换行。

    ```
    正例：
    StringBuffer sb = new StringBuffer();
    //超过120个字符的情况下，换行缩进4个空格，并且方法前的点符号一起换行
    sb.append("zi").append("xin")...
    	.append("huang")...
    	.append("huang")...
    	.append("huang");
    反例：
    StringBuffer sb = new StringBuffer();
    //超过120个字符的情况下，不要在括号前换行
    sb.append("zi").append("xin")...append
    	("huang");
    //参数很多的方法调用可能超过120个字符，不要在逗号前换行
    method(args1, args2, args3, ...
    	, argsX);
    ```

  + 函数最大行数：函数代码不超过20行。

  + 空行规则：方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。

  + 操作符前后空格: 任何运算符左右必须加一个空格。

    > 说明：运算符包括赋值运算符=、逻辑运算符&&、加减乘除符号、三目运行符等。

+ 注释规则：

  + 类、类属性、类方法的注释必须使用Javadoc规范，使用/**内容*/格式，不得使用//xxx方式。
  + 所有的抽象方法（包括接口中的方法）必须要用Javadoc注释、除了返回值、参数，还必须指出该方法做什么事情，实现什么功能。
  + 所有的类都必须添加创建者信息。
  + 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/* */注释，注意与代码对齐。
  + 所有的枚举类型字段必须要有注释，说明每个数据项的用途。

+ 命名规约：

  + 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。

    ```
    反例： _name / __name / $Object / name_ / name$ / Object$
    ```

  + 代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。

    ```
    反例： DaZhePromotion [打折] / getPingfenByName()  [评分] / int某变量 = 3
    正例： alibaba / taobao / youku / hangzhou等国际通用的名称，可视同英文。
    ```

  + 变量命名：使用lowerCamelCase风格，遵从驼峰形式。

    ```
    正例： localValue / getHttpMessage() /  inputUserId
    ```

  + 类命名：类名使用UpperCamelCase风格，遵从驼峰形式，但以下情形例外：（领域模型的相关命名）DO / BO / DTO / VO等。

    ```
    正例：MarcoPolo / UserDO / XmlService / TcpUdpDeal /   TaPromotion
    反例：macroPolo / UserDo / XMLService / TCPUDPDeal /   TAPromotion
    ```

  + 函数命名：使用lowerCamelCase风格，遵从驼峰形式。

    ```
    正例： localValue / getHttpMessage() /  inputUserId
    ```

  + 常量：常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚。

    ```
    正例： MAX_STOCK_COUNT
    反例： MAX_COUN
    ```

- 其他规则

  - Math.random()这个方法返回是double类型，注意取值的范围 0≤x<1（能够取到零值，注意除零异常），如果想获取整数类型的随机数，不要将x放大10的若干倍然后取整，直接使用Random对象的nextInt或者nextLong方法。

  - 获取当前毫秒数System.currentTimeMillis();而不是new Date().getTime();

    > 说明：如果想获取更加精确的纳秒级时间值，用System.nanoTime()。在JDK8中，针对统计时间等场景，推荐使用Instant类。

  