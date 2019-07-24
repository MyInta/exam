package tencent.leetcode201_250;

/**
 * @author inta
 * @date 2019/7/18
 * @describe 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
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
 *
 */
public class Q208Trie {
    class TreeNode{
        boolean isword;
        TreeNode[] child = new TreeNode[26];
        TreeNode(){
            isword = false;
        }
    }

    TreeNode root;
    /** Initialize your data structure here. */
    public Q208Trie() {
        root = new TreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode t = root;
        for(int i=0;i<word.length();i++){
            int idx = word.charAt(i)- 'a';
            if(t.child[idx]==null){
                t.child[idx] = new TreeNode();
            }
            t = t.child[idx];
        }
        t.isword = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode t = root;
        for(int i=0;i<word.length();i++){
            int idx = word.charAt(i)- 'a';
            if(t.child[idx]==null){
                return false;
            }
            t = t.child[idx];
        }
        return t.isword;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode t = root;
        for(int i=0;i<prefix.length();i++){
            int idx = prefix.charAt(i)- 'a';
            if(t.child[idx]==null){
                return false;
            }
            t = t.child[idx];
        }
        return true;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */