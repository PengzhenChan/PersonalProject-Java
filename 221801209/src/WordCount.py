import sys
from Lib import WordCount

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("python WordCount.py input.txt output.txt")
    s = ""
    with open(sys.argv[1], "r", encoding="utf-8") as f:
        s = f.read()
    wc = WordCount(s)

    output = ""
    output += "characters: %d\n" % wc.charCount()
    output += "words: %d\n" % wc.wordCount()
    output += "lines: %d\n" % wc.lineCount()

    frequency = wc.topWord()
    for word in frequency:
        output += "%s: %d" % (word[0], word[1])

    with open(sys.argv[2], "w", encoding="utf-8") as f:
        f.write(output)
