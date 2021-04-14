package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2019/7/18
 * @describe 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class Q208Trie {
    private class TreeNode {
        TreeNode[] child;
        boolean isWord;
        TreeNode() {
            child = new TreeNode[127];
            isWord = false;
        }
    }

    private TreeNode tn;

    /** Initialize your data structure here. */
    public Q208Trie() {
        tn = new TreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (search(word)) {
            return;
        }
        TreeNode temp = tn;
        for (char c : word.toCharArray()) {
            if (temp.child[c] == null) {
                temp.child[c] = new TreeNode();
            }
            temp = temp.child[c];
        }
        temp.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode temp = tn;
        for (char c : word.toCharArray()) {
            if (temp.child[c] == null) {
                return false;
            }
            temp = temp.child[c];
        }
        return temp.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode temp = tn;
        for (char c : prefix.toCharArray()) {
            if (temp.child[c] == null) {
                return false;
            }
            temp = temp.child[c];
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