import re
class WordCount:
    def __init__(self, s):
        """
        Args:
            s: 待处理字符串
        """
        if not type(s) == str:
            raise TypeError("请传入一个字符串")
        self.str = s
        self.words = []

    def charCount(self):
        """
        Returns:
            字符数量
        """
        return len(self.str)

    def wordCount(self):
        """
        Returns:
            单词数量
        """
        s = self.str
        s = s.lower()
        self.words = re.findall("([a-z]{4,}[a-z0-9]*)", s)

        return len(self.words)

    def topWord(self):
        """
        Returns:
            [[单词，频率]...]
        """
        if self.words == []:
            self.wordCount()
        count = []
        keys = list(set(self.words))
        for key in keys:
            count.append([key, self.words.count(key)])
        count.sort(key=(lambda e: e[1]))
        return count[:10]


    def lineCount(self):
        """
        Returns:
            行数
        """
        s = self.str
        # 去除空白字符\f\n\r\t\v\b
        spaces = "\f\t\v\b"
        for space in spaces:
            s = s.replace(space, "")

        # 将\r\n和\r式的换行转化为\n式
        s = s.replace("\r", "\n")
        while True:
            tmp = s.replace("\n\n", "\n")
            if tmp == s:
                break
            s = tmp

        # 标准化完成,行数计算
        return s.count("\n") + 1
