import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李星源
 * @date 2021/02/25
 */
public class WordOperationImpl implements WordOperation {
    // 输出文件
    private File outputFile;
    // 文本内容
    private String content;
    // 有效行数
    private AtomicInteger lineNum;
    // 单词数
    private AtomicInteger wordNum;
    // 文本的每一行
    private String[] lines;
//    // 文本中未判断的单词
//    private String[] wordTemp;

    // 一个线程最小处理量
    private static final int MIN_GRANULARITY = 512 * (1 << 5);
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService threadPool = new ThreadPoolExecutor(
        4, Math.max((CPU_COUNT << 1), 8), 30, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(Integer.MAX_VALUE), r -> new Thread(null, r, "", 0),
        new ThreadPoolExecutor.CallerRunsPolicy());

    // 记录单词个数的映射
    private Map<String, Integer> wordIntegerMap;
//    // 记录线程数
//    private CountDownLatch countDownLatch;

    public WordOperationImpl(File inputFile, File outputFile){
        this.outputFile = outputFile;
        content = FileUtil.read(inputFile).toLowerCase();
        wordNum = new AtomicInteger(0);
        lines = content.split("\n");
        lineNum = new AtomicInteger(0);
//        wordTemp = content.split("[^a-z0-9]+");
        wordIntegerMap = new HashMap<>(8192);
        System.out.println("初始化成功");
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
        wordNum = new AtomicInteger(0);
        String[] temp = content.split("[^a-z0-9]+");
        for (String word : temp){
            if (word.matches("^[a-z]{4}[a-z0-9]*$")){
                wordNum.getAndIncrement();
            }
        }
        return wordNum.get();
    }

    /**
     * 统计文本中top10单词及个数
     *
     * @return 单词列表
     */
    @Override
    public List<Word> countTopTenWord() {
        wordIntegerMap = new HashMap<>(8192);
        String[] temp = content.split("[^a-z0-9]+");
        for (String word : temp){
            if (word.matches("^[a-z]{4}[a-z0-9]*$")){
                if (wordIntegerMap.containsKey(word)){
                    wordIntegerMap.replace(word, wordIntegerMap.get(word) + 1);
                } else {
                    wordIntegerMap.put(word, 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordIntegerMap.entrySet());
        words.sort((o1, o2) -> {
            if (o1.getValue().intValue() != o2.getValue().intValue()){
                return o2.getValue() - o1.getValue();
            }
            return o1.getKey().compareTo(o2.getKey());
        });

        List<Word> topTen = new ArrayList<>();
        int num = 0;
        for (Map.Entry<String, Integer> word : words){
            if (num < 10){
                topTen.add(new Word(word.getKey(), word.getValue()));
            }
            ++num;
        }
        return topTen;
    }

    /**
     * 统计字符数、单词数、行数、top10单词并输出到文件
     */
    @Override
    public void countAll() {
//        // 统计单词个数及top10词频
//        int wordLen = wordTemp.length;
//        int oneLen = Math.max((wordLen >> 4), MIN_GRANULARITY);
////        if ((wordLen % oneLen) != 0){
////            countDownLatch = new CountDownLatch((wordLen / oneLen) + 1);
////        } else {
////            countDownLatch = new CountDownLatch(wordLen / oneLen);
////        }
//        for (int i=0;i<wordLen;i+=oneLen){
//            int threadLen;
//            if ((i + oneLen) > wordLen){
//                threadLen = (wordLen - i);
//            } else {
//                threadLen = oneLen;
//            }
//            int finalI = i;
//            threadPool.execute(() -> countWord(finalI, threadLen));
//        }

//        System.out.println("添加统计单词");

        // 统计有效行数
        int lineLen = lines.length;
        int oneLen = Math.max((lineLen >> 4), MIN_GRANULARITY);
        for (int i=0;i<lineLen;i+=oneLen){
            int threadLen;
            if ((i + oneLen) > lineLen){
                threadLen = (lineLen - i);
            } else {
                threadLen = oneLen;
            }
            int finalI = i;
            threadPool.execute(() -> countLine(finalI, threadLen));
            threadPool.execute(() -> countWord(finalI, threadLen));
        }

        System.out.println("添加行数");

//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        List<Word> topTen = getTopTen();
        threadPool.shutdown();
        boolean loop = false;
        do {
            try {
                loop = (!threadPool.awaitTermination(1, TimeUnit.MINUTES));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(loop);

        System.out.println("开始保存");

        saveResult(getTopTen());
    }

    /**
     * 获取top10单词
     *
     * @return top10列表
     */
    private List<Word> getTopTen(){
        List<Map.Entry<String, Integer>> words = new ArrayList<>(wordIntegerMap.entrySet());
        words.sort((o1, o2) -> {
            if (o1.getValue().intValue() != o2.getValue().intValue()){
                return o2.getValue() - o1.getValue();
            }
            return o1.getKey().compareTo(o2.getKey());
        });

        List<Word> topTen = new ArrayList<>();
        int num = 0;
        for (Map.Entry<String, Integer> word : words){
            if (num < 10){
                topTen.add(new Word(word.getKey(), word.getValue()));
            }
            ++num;
        }
        return topTen;
    }

    /**
     * 统计单词个数及top10词频
     *
     * @param index 起始位置
     * @param len 长度
     */
    private void countWord(int index, int len){
        int end = index + len;
        int count = 0;
        String[] words;
        for (int i=index;i<end;i++){
            words = lines[i].split("[^a-z0-9]+");
            count += words.length;
            for (String word : words){
                if (word.matches("^[a-z]{4}[a-z0-9]*$")){
                    synchronized (wordIntegerMap){
                        if (wordIntegerMap.containsKey(word)){
                            wordIntegerMap.replace(word, wordIntegerMap.get(word) + 1);
                        } else {
                            wordIntegerMap.put(word, 1);
                        }
                    }
                } else {
                    --count;
                }
            }
//            if (wordTemp[i].matches("^[a-z]{4}[a-z0-9]*$")){
//                synchronized (wordIntegerMap){
//                    if (wordIntegerMap.containsKey(wordTemp[i])){
//                        wordIntegerMap.replace(wordTemp[i], wordIntegerMap.get(wordTemp[i]) + 1);
//                    } else {
//                        wordIntegerMap.put(wordTemp[i], 1);
//                    }
//                }
//            } else {
//                --len;
//            }
        }
        wordNum.getAndAdd(count);
//        countDownLatch.countDown();
    }

    /**
     * 统计文本的有效行数
     *
     * @param index 起始位置
     * @param len 长度
     */
    private void countLine(int index, int len){
        int end = index + len;
        char c;
        for (int i=index;i<end;i++){
            if (lines[i].length() < 1){
                --len;
//                System.out.println(i);
                continue;
            }
            int j;
            for (j=0;j<lines[i].length();j++){
                c = lines[i].charAt(j);
                if ((c >= 33) && (c <= 126)){
                    //System.out.println(i);
                    break;
                }
            }
            if (j >= lines[i].length()){
                --len;
            }
        }
//        System.out.println(len);
        lineNum.getAndAdd(len);
    }

    /**
     * 保存计算结果到文件
     *
     */
    private void saveResult(List<Word> topTen){
        StringBuilder sb = new StringBuilder();
        sb.append("characters: ").append(content.length()).append("\n")
            .append("words: ").append(wordNum.get()).append("\n")
            .append("lines: ").append(lineNum.get()).append("\n");
        for (Word word : topTen){
            sb.append(word.getSpell()).append(": ").append(word.getCount()).append("\n");
        }
        FileUtil.write(outputFile, sb.toString());
    }

}
