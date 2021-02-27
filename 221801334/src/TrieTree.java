import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author 李星源
 * @date 2021/02/27
 */
public class TrieTree {
    public Node root;
    public Queue<Word> wordQueue;

    public TrieTree(){
        root = new Node('-');
        wordQueue = new PriorityQueue<>(10);
    }

    /**
     * 插入字符串
     *
     * @param index 初始位置
     * @param end 结束位置
     * @param content 文本内容
     */
    public void insert(int index, int end, String content){
        Node now = root;
        int charIndex;
        char c;

        for (int i=index;i<end;i++){
            c = content.charAt(i);
            charIndex = c - '0';
            charIndex = ((charIndex < 10) ? charIndex : (c - 'a' + 10));
            if (now.child[charIndex] == null){
                now.child[charIndex] = new Node(c);
            }
            now = now.child[charIndex];
        }
        ++(now.count);
    }

    /**
     * 获取优先队列中的topTen
     *
     * @param topTen 存储结果
     */
    public void getTopTen(Word[] topTen){
        dfs();
        wordQueue.toArray(topTen);
    }

    /**
     * 遍历树
     *
     */
    public void dfs(){
        for (int i=10;i<36;i++){
            if (root.child[i] != null){
                preTravel(root.child[i], new StringBuilder());
            }
        }
    }

    /**
     * 前序遍历树
     *
     * @param root 根结点
     * @param sb 当前字符串
     */
    private void preTravel(Node root, StringBuilder sb){
        sb.append(root.c);
        if (root.count > 0){
            Word word = new Word(sb.toString(), root.count);
            if (wordQueue.size() < 10){
                wordQueue.add(word);
            } else if (word.compareTo(wordQueue.peek()) > 0){
                wordQueue.poll();
                wordQueue.add(word);
            }
        }
        for (int i=0;i<36;i++){
            if (root.child[i] != null){
                preTravel(root.child[i], sb);
            }
        }
        sb.setLength(sb.length() - 1);
    }
}

/**
 * 字典树结点
 *
 * @author 李星源
 * @date 2021/02/27
 */
class Node{
    public char c;
    public int count;
    public Node[] child;

    public Node(char c){
        this.c = c;
        count = 0;
        child = new Node[36];
    }
}
