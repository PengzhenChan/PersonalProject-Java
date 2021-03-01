import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现WordOperation接口
 * 实现其中的所有功能
 *
 * @author 李星源221801334
 * @date 2021/02/25
 */
public class WordOperationImpl implements WordOperation {
    // 一个线程处理的字符串的最小长度
    private static final int MIN_THREAD_LENGTH = 256 * 10;
    // 单词的正则表达式匹配
    private static final Pattern wordPattern = Pattern.compile("[a-z]{4}[a-z0-9]*");
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    // 输出文件
    private File outputFile;
    // 文本内容
    private String content;
    // 有效行数
    private AtomicInteger lineNum;
    // 单词数
    private AtomicInteger wordNum;
    // 记录单词-个数的映射
    private Map<String, Integer> wordIntegerMap;

    private static final ExecutorService threadPool = new ThreadPoolExecutor(
        8, Math.max((CPU_COUNT << 1), 16), 30, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(Integer.MAX_VALUE), r -> new Thread(null, r, "", 0),
        new ThreadPoolExecutor.CallerRunsPolicy());

    public WordOperationImpl(File inputFile, File outputFile){
        content = FileUtil.readMMAP(inputFile).toLowerCase();
        this.outputFile = outputFile;
        wordNum = new AtomicInteger(0);
        lineNum = new AtomicInteger(0);
        wordIntegerMap = new ConcurrentHashMap<>(1 << 18);
    }

    /**
     * 计算文本的字符数
     *
     * @return 字符数
     */
    @Override
    public int countCharacter() {
        return content.length();
    }

    /**
     * 统计文本中单词个数
     *
     * @return 单词数
     */
    @Override
    public int countWord() {
        Matcher wordMatcher = wordPattern.matcher(content);
        int wordNum = 0;
        if (wordMatcher.find()){
            int start = wordMatcher.start() - 1;
            if (start >= 0){
                if (wordLegal(content.charAt(start))){
                    ++wordNum;
                }
            } else {
                ++wordNum;
            }
        }
        while (wordMatcher.find()){
            if (wordLegal(content.charAt(wordMatcher.start() - 1))){
                ++wordNum;
            }
        }
        return wordNum;
    }

    /**
     * 统计文本中top10单词及个数
     *
     * @return 单词列表
     */
    @Override
    public List<Word> countTopTenWord() {
        Map<String, Integer> wordIntegerMap = new HashMap<>(1<<18);
        Matcher wordMatcher = wordPattern.matcher(content);
        String word;
        if (wordMatcher.find()){
            word = wordMatcher.group();
            int start = wordMatcher.start() - 1;
            if (start>=0){
                if (wordLegal(content.charAt(start))){
                    wordIntegerMap.put(word, 1);
                }
            } else {
                wordIntegerMap.put(word, 1);
            }
        }
        while (wordMatcher.find()){
            if (wordLegal(content.charAt(wordMatcher.start() - 1))){
                word = wordMatcher.group();
                if (wordIntegerMap.containsKey(word)){
                    wordIntegerMap.put(word, wordIntegerMap.get(word) + 1);
                } else {
                    wordIntegerMap.put(word, 1);
                }
            }
        }

        Queue<Map.Entry<String, Integer>> wordQueue = new PriorityQueue<>(16, (o1, o2) -> {
            if (o1.getValue().intValue() != o2.getValue().intValue()){
                return o1.getValue() - o2.getValue();
            }
            return o2.getKey().compareTo(o1.getKey());
        });
        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordIntegerMap.entrySet());
        for (Map.Entry<String, Integer> oneWord : words){
            if (wordQueue.size() < 10){
                wordQueue.add(oneWord);
            } else if (isReplace(oneWord, wordQueue.peek())){
                wordQueue.poll();
                wordQueue.add(oneWord);
            }
        }

        List<Word> wordList = new ArrayList<>(16);
        while (!wordQueue.isEmpty()){
            Map.Entry<String, Integer> oneWord = wordQueue.poll();
            wordList.add(0, new Word(oneWord.getKey(), oneWord.getValue()));
        }
        return wordList;
    }

    /**
     * 统计字符数、单词数、行数、top10单词并输出到文件
     */
    @Override
    public void countAll() {
        //如果长度为0
        if (content.length() == 0){
            saveResult(null);
        }

        int len = content.length();
        int oneLen = Math.max(MIN_THREAD_LENGTH, len >> 4);
        int start = 0;
        int j;
        for (int i = 0;i < len;i += oneLen){
            if (i > start){
                for (j = i;j < len;++j){
                    if (content.charAt(j) == '\n'){
                        break;
                    }
                }
                int finalStart = start;
                int finalJ = j;
                threadPool.execute(() -> countLine(finalStart, finalJ));
                threadPool.execute(() -> countWordThread(finalStart, finalJ));
                start = j + 1;
            }
        }
        if (start < len){
            int finalStart1 = start;
            threadPool.execute(() -> countLine(finalStart1, len));
            threadPool.execute(() -> countWordThread(finalStart1, len));
        }

        threadPool.shutdown();
        boolean loop = false;
        do {
            try {
                loop = (!threadPool.awaitTermination(1, TimeUnit.MINUTES));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(loop);
        saveResult(getTopTen());
    }

    /**
     * 判断是否是合法的单词
     *
     * @param c 前一个字符
     * @return true为合法
     */
    private boolean wordLegal(char c){
        if ((c >= '0') && (c <='9'))
            return false;
        return (c < 'a') || (c > 'z');
    }

    /**
     * 计算行数
     *
     * @param start 开始位置
     * @param end 结束位置
     */
    private void countLine(int start, int end){
        String temp = content.substring(start, end);
        int tempLen = temp.length();
        // 一行的开始位置
        int startIndex = 0;
        // 一行的结束位置
        int endIndex;
        int i;
        int count = 0;
        char c;
        while (startIndex < tempLen) {
            for (endIndex = startIndex;endIndex<tempLen;endIndex++){
                if (temp.charAt(endIndex) == '\n'){
                    break;
                }
            }
            if ((endIndex - startIndex) > 0){
                for (i = startIndex;i < endIndex;++i){
                    c = temp.charAt(i);
                    if ((c >= 33) && (c <= 126)){
                        break;
                    }
                }
                if (i < endIndex){
                    ++count;
                }
            }
            startIndex = endIndex + 1;
        }
        lineNum.getAndAdd(count);
    }

    /**
     * 获取top10单词
     *
     * @return top10列表
     */
    private List<Map.Entry<String, Integer>> getTopTen(){
        Queue<Map.Entry<String, Integer>> wordQueue = new PriorityQueue<>(16, (o1, o2) -> {
            if (o1.getValue().intValue() != o2.getValue().intValue()){
                return o1.getValue() - o2.getValue();
            }
            return o2.getKey().compareTo(o1.getKey());
        });
        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordIntegerMap.entrySet());
        for (Map.Entry<String, Integer> word : words){
            if (wordQueue.size() < 10){
                wordQueue.add(word);
            } else if (isReplace(word, wordQueue.peek())){
                wordQueue.poll();
                wordQueue.add(word);
            }
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(16);
        while (!wordQueue.isEmpty()){
            wordList.add(wordQueue.poll());
        }
        return wordList;
    }

    /**
     * 判断优先队列是否弹出数据
     *
     * @param m1 替换数据
     * @param m2 被替换数据
     * @return true为应该替换
     */
    private boolean isReplace(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2){
        if (m1.getValue().intValue() != m2.getValue().intValue()){
            return m1.getValue() > m2.getValue();
        }
        return m1.getKey().compareTo(m2.getKey()) < 0;
    }

    /**
     * 往map中修改数据
     *
     * @param key 单词
     */
    private void dealMap(String key){
        Integer oldValue;
        while (true) {
            oldValue = wordIntegerMap.get(key);
            if (oldValue == null) {
                if (wordIntegerMap.putIfAbsent(key, 1) == null) {
                    break;
                }
            } else {
                if (wordIntegerMap.replace(key, oldValue, oldValue + 1)) {
                    break;
                }
            }
        }
    }

    /**
     * 统计单词个数
     *
     * @param startIndex 开始位置
     * @param endIndex 结束位置
     */
    private void countWordThread(int startIndex, int endIndex){
        String temp = content.substring(startIndex, endIndex);
        Matcher wordMatcher = wordPattern.matcher(temp);
        String word;
        int count = 0;
        if (wordMatcher.find()){
            word = wordMatcher.group();
            int start = wordMatcher.start() - 1;
            if (start>=0){
                if (wordLegal(temp.charAt(start))){
                    ++count;
                    dealMap(word);
                }
            } else {
                ++count;
                dealMap(word);
            }
        }
        while (wordMatcher.find()){
            if (wordLegal(temp.charAt(wordMatcher.start() - 1))){
                ++count;
                dealMap(wordMatcher.group());
            }
        }
        wordNum.getAndAdd(count);
    }

    /**
     * 保存计算结果到文件
     *
     * @param topTen top10单词
     */
    private void saveResult(List<Map.Entry<String, Integer>> topTen){
        StringBuilder sb = new StringBuilder();
        sb.append("characters: ").append(content.length()).append("\n")
            .append("words: ").append(wordNum.get()).append("\n")
            .append("lines: ").append(lineNum.get()).append("\n");
        if (topTen != null){
            for (int i = topTen.size() - 1;i >= 0;--i){
                sb.append(topTen.get(i).getKey()).append(": ").append(topTen.get(i).getValue()).append("\n");
            }
        }
        FileUtil.write(outputFile, sb.toString());
    }

}
