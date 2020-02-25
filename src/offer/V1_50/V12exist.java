package offer.V1_50;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/25
 * @describe 设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，
 * 那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 *  同LC79
 */
public class V12exist {
    public boolean exist(char[][] board, String word) {
        this.board = board;
        char[] words = word.toCharArray();
        this.words = words;
        int start = 0, end = word.length();
        int n = board.length, m = board[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (words[start] == board[i][j] && dfs(start, end, n, m, i, j)) return true;
            }
        }
        return false;
    }
    private char[][] board;
    private char[] words;
    private boolean[][] visited;
    private boolean dfs(int start, int end, int n, int m, int i, int j) {
        if (start == end) return true;
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] || words[start] != board[i][j]) return false;
        visited[i][j] = true;
        if(dfs(start + 1, end, n, m, i + 1, j)
                || dfs(start + 1, end, n, m, i - 1, j)
                || dfs(start + 1, end, n, m, i, j + 1)
                || dfs(start + 1, end, n, m, i, j - 1)) return true;
        visited[i][j] = false;
        return false;
    }
}
