import re


def cmp(mycmp):
    class K:
        def __init__(self, obj, *args):
            self.obj = obj

        def __lt__(self, other):
            return mycmp(self.obj, other.obj) < 0

        def __gt__(self, other):
            return mycmp(self.obj, other.obj) > 0

        def __eq__(self, other):
            return mycmp(self.obj, other.obj) == 0

        def __le__(self, other):
            return mycmp(self.obj, other.obj) <= 0

        def __ge__(self, other):
            return mycmp(self.obj, other.obj) >= 0

        def __ne__(self, other):
            return mycmp(self.obj, other.obj) != 0

    return K


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
        keys = []
        for k in self.words:
            if k not in keys:
                keys.append(k)
        for key in keys:
            count.append([key, self.words.count(key)])
        count2 = count.copy()

        count.sort(key=cmp(lambda e1, e2: e2[1] if e1[1] > e2[1] else e1[1] ))
        result = []
        for c in count:
            for c2 in count2:
                if c[1] == c2[1] and c2 not in result:
                    result.append(c2)
                    count2.remove(c2)
                    break
            if len(result) == 10:
                break


        return result

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
