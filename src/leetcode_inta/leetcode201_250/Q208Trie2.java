package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2019/7/18
 * @describe 【时间还不如前一个Q208Trie】
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class Q208Trie2 {
    class TrieNode{

        private TrieNode[] child;
        //小写字母a-z
        private final int CHAR_SIZE = 26;
        private boolean word = false;
        public TrieNode(){
            child = new TrieNode[CHAR_SIZE];
        }

        public boolean containsKey(char c){
            //是否包含某字符，就看对应子节点处是否存在
            return child[getIdx(c)]!=null;
        }
        //增设节点
        public void put(char c){
            if(!containsKey(c)){
                child[getIdx(c)] = new TrieNode();
            }
        }
        //通过某字符获得对应节点
        public TrieNode get(char c){
            if(containsKey(c)){
                return child[getIdx(c)];
            }
            return null;
        }
        //获得某字符对应索引
        public int getIdx(char c){
            return c-'a';
        }
        //set get isword
        public boolean isWord() {
            return word;
        }
        public void setWord(boolean word) {
            this.word = word;
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public Q208Trie2(){
        root = new TrieNode();
    }

    /** Returns if the word is in the trie. */
    public void insert(String word) {
        TrieNode t = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!t.containsKey(c)){
                t.put(c);
            }
            //如果存在这个字符，就遍历下一个节点
            t = t.get(c);
        }
        //都添加了一遍后，把节点最后那个位置设置word为true
        t.setWord(true);
    }

    private TrieNode searchPrefix(String word) {
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!t.containsKey(c)) {
                return null;
            }
            //如果存在这个字符，就遍历下一个节点
            t = t.get(c);
        }
        return t;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode t = searchPrefix(word);
        return t!=null&&t.isWord();
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode t = searchPrefix(prefix);
        return t!=null;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */