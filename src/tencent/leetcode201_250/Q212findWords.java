package tencent.leetcode201_250;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/10
 * @describe 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 *
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？
 * 散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 *
 */
public class Q212findWords {

    //极其费时
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return res;
        int n = board.length;
        int m = board[0].length;
        for (String word : words) {
            boolean[][] visited;
            boolean findOne = false;
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < m; j ++) {
                    visited = new boolean[n][m];
                    if (isValid(board, word.toCharArray(), i, j, 0, visited)) {
                        res.add(word);
                        findOne = true;
                        break;
                    }
                }
                if (findOne) break;
            }
        }
        return res;
    }
    private boolean isValid(char[][] board, char[] wordChars, int i, int j, int cur, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return false;
        if (board[i][j] == wordChars[cur]) {
            visited[i][j] = true;
            if (cur == wordChars.length - 1) return true;
            boolean flag =  isValid(board, wordChars, i + 1, j, cur + 1, visited)
                    || isValid(board, wordChars, i - 1, j, cur + 1, visited)
                    || isValid(board, wordChars, i, j + 1, cur + 1, visited)
                    || isValid(board, wordChars, i, j - 1, cur + 1, visited);
            if (flag) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }



    //使用前缀树来做
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return res;
        //一开始将所有字典内的字符添加到前缀树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                dfs(board, trie.root, i, j, res);
            }
        }
        return res;
    }
    private void dfs(char[][] board, Trie.TrieNode trieNode, int i, int j, List<String> res) {
        //越界考虑
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        //将要遍历的字母
        char present = board[i][j];
        //如果是之前遍历过，或者不存在前缀树中，就直接返回
        if (present == '#' || trieNode.children[present - 'a'] == null) return;
        //否则的话，说明它是存在于前缀树中的，我们判断是否是最终位置，就看其value
        Trie.TrieNode presentTrieNode = trieNode.children[present - 'a'];
        String v = presentTrieNode.value;
        if (v != null) {
            //不为null说明是有值，加入到res中，并防止重复添加，就将值覆盖掉
            res.add(v);
            presentTrieNode.value = null;
        }
        //将该位置标志为访问过了，用"#"替代吧
        board[i][j] = '#';
        //否则继续深度遍历
        dfs(board, presentTrieNode, i + 1, j, res);
        dfs(board, presentTrieNode, i - 1, j, res);
        dfs(board, presentTrieNode, i, j + 1, res);
        dfs(board, presentTrieNode, i, j - 1, res);
        //回溯，将该位置标记为没访问过之前状态
        board[i][j] = present;
    }

    class Trie{
        class TrieNode{
            //孩子节点
            public TrieNode[] children;
            //最终记录值
            public String value;
            public TrieNode() {
                //代表26个字母
                children = new TrieNode[26];
            }
        }

        //根节点
        TrieNode root;
        //初始化根节点
        public Trie() {
            root = new TrieNode();
        }

        //增加前缀方法
        public void insert(String s) {
            //指针
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    //若该位置为空，新建
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            //最后给末端层即最后一个元素赋值
            cur.value = s;
        }
    }
}
