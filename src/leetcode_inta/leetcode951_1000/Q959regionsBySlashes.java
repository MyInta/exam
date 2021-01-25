package leetcode_inta.leetcode951_1000;

/**
 * @author inta
 * @date 2021/1/25
 * @describe 在由 1 x 1 方格组成的 N x N 网格 grid 中，
 * 每个 1 x 1方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * 返回区域的数目。
 *
 * 示例 1：
 * 输入：
 * [
 *   " /",
 *   "/ "
 * ]
 * 输出：2
 *
 * 示例 2：
 * 输入：
 * [
 *   " /",
 *   "  "
 * ]
 * 输出：1
 *
 * 示例 3：
 * 输入：
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 *
 * 示例 4：
 * 输入：
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 *
 * 示例 5：
 * 输入：
 * [
 *   "//",
 *   "/ "
 * ]
 * 输出：3
 *
 * 提示：
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。
 */
public class Q959regionsBySlashes {
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        int n = len * len * 4;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = 0; j < grid[i].length(); j++) {
                int cur = i * len + count;
                if (grid[i].charAt(j) == '/') {
                    unionFind.merge(cur * 4, cur * 4 + 3);
                    unionFind.merge(cur * 4 + 1, cur * 4 + 2);

                } else if (grid[i].charAt(j) == '\\') {
                    j++;
                    unionFind.merge(cur * 4, cur * 4 + 1);
                    unionFind.merge(cur * 4 + 2, cur * 4 + 3);
                } else {
                    unionFind.merge(cur * 4, cur * 4 + 1);
                    unionFind.merge(cur * 4 + 2, cur * 4 + 3);
                    unionFind.merge(cur * 4, cur * 4 + 2);
                }
                if (count > 0) {
                    unionFind.merge((cur - 1) * 4 + 1, cur * 4 + 3);
                }
                if (i > 0) {
                    unionFind.merge((cur - len) * 4 + 2, cur * 4);
                }
                count++;
            }
        }
        return unionFind.getCount();
    }

    // 并查集
    private class UnionFind {
        private int[] parents;
        private int count;

        public UnionFind(int n) {
            this.parents = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        public void merge(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) {
                return;
            }
            parents[parentX] = parentY;
            count--;
        }

        public int find(int child) {
            if (parents[child] != child) {
                parents[child] = find(parents[child]);
            }
            return parents[child];
        }
    }
}
