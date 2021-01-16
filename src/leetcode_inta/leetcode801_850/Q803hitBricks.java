package leetcode_inta.leetcode801_850;

/**
 * @author inta
 * @date 2021/1/16
 * @describe 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 *
 * 一块砖直接连接到网格的顶部，或者
 * 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，
 * 对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，
 * 它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 *
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 *
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 * 示例 1：
 *
 * 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * 输出：[2]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0]，
 *  [1,1,1,0]]
 * 消除 (1,0) 处加粗的砖块，得到网格：
 * [[1,0,0,0]
 *  [0,1,1,0]]
 * 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * 因此，结果为 [2] 。
 * 示例 2：
 *
 * 输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * 输出：[0,0]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0],
 *  [1,1,0,0]]
 * 消除 (1,1) 处加粗的砖块，得到网格：
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * 剩下的砖都很稳定，所以不会掉落。网格保持不变：
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * 接下来消除 (1,0) 处加粗的砖块，得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * 剩下的砖块仍然是稳定的，所以不会有砖块掉落。
 * 因此，结果为 [0,0] 。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] 为 0 或 1
 * 1 <= hits.length <= 4 * 104
 * hits[i].length == 2
 * 0 <= xi <= m - 1
 * 0 <= yi <= n - 1
 * 所有 (xi, yi) 互不相同
 */
public class Q803hitBricks {
    // 参考官方思路三步走，自己实现细节
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] result = new int[hits.length];
        int m = grid.length;
        int n = grid[0].length;
        int[][] dictions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 第一步，依敲碎目标，将砖块全部敲碎
        int[][] copyGrid = new int[m][n];
        for (int i = 0; i < m; i++) {
            // 不推荐直接修改参数，拷贝推荐使用System.arraycopy的拷贝方式
            System.arraycopy(grid[i], 0, copyGrid[i], 0, n);
        }
        for (int[] hit : hits) {
            copyGrid[hit[0]][hit[1]]--; // 考虑到可能原本该位置就没有砖块，如果只修改为0，对后续回填会产生重复风险
        }

        // 第二步，建图，把当前拥有的圈子都搭建好
        int parentSize = m * n;
        UnionFind gridUnionFind = new UnionFind(parentSize + 1);
        for (int j = 0; j < n; j++) {
            if (copyGrid[0][j] == 1) { // 初始化阶段，把第一排，也就是房顶元素都归一到一个父节点中
                gridUnionFind.merge(j, parentSize); // 这里的j其实是getIndex(0, j, n)的结果
            }
        }
        build(m, n, copyGrid, gridUnionFind);

        // 第三步，回填砖块，统计出回填时候满足题意的掉落砖块数量
        for (int i = hits.length - 1; i >= 0; i--) { // 逆序回填
            int[] hit = hits[i];
            int cur = getIndex(hit[0], hit[1], n);

            // 回填的前提是此处有砖块被敲碎
            if (copyGrid[hit[0]][hit[1]] == -1) {
                continue;
            }
            int preCount = gridUnionFind.getSize(parentSize); // 记录回填之前的砖块数量

            // 容易疏漏的一点，当补回的点是首行时，要把它指向我们起初规定的屋顶值parentSize
            if (hit[0] == 0) {
                gridUnionFind.merge(cur, parentSize);
            }

            // 回填的时候查看四周，是否需要合并
            for (int[] diction : dictions) {
               int x = hit[0] + diction[0];
               int y = hit[1] + diction[1];

               if (isValidRang(x, y, m, n) && copyGrid[x][y] == 1) {
                   // 合并
                   int target = getIndex(x, y, n);
                   gridUnionFind.merge(target, cur);
               }
            }

            // 此时再统计一下数量
            int curCount = gridUnionFind.getSize(parentSize);

            // 两者的差值修正后为砖块掉落数, 减一是因为hit中被敲的那一块砖块不算进掉落数内
            result[i] = Math.max(0, curCount - preCount - 1);

            // 别忘了回填
            copyGrid[hit[0]][hit[1]] = 1;
        }

        return result;
    }

    // 检验是否是在grid范围内的索引坐标
    private boolean isValidRang(int x, int y, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return false;
        }
        return true;
    }

    // 搭建圈子
    private void build(int m, int n, int[][] copyGrid, UnionFind gridUnionFind) {
        for (int i = 1; i < m; i++) {
            buildCol(n, copyGrid, gridUnionFind, i);
        }
    }

    // 同一行圈子搭建
    private void buildCol(int n, int[][] copyGrid, UnionFind gridUnionFind, int i) {
        for (int j = 0; j < n; j++) {
            if (copyGrid[i][j] == 1) {
                int curIndex = getIndex(i, j, n);
                if (copyGrid[i - 1][j] == 1) { // 合并上面
                    int targetIndex = getIndex(i - 1, j, n);
                    gridUnionFind.merge(curIndex, targetIndex);
                }
                if (j > 0 && copyGrid[i][j - 1] == 1) { // 合并左边
                    int targetIndex = getIndex(i, j - 1, n);
                    gridUnionFind.merge(curIndex, targetIndex);
                }
            }
        }
    }

    // 将二维元素转化为一维的，方便使用一位数组记录，指定父节点
    private int getIndex(int i, int j, int colLen) {
        return i * colLen + j;
    }

    /**
     * 并查集
     */
    private class UnionFind {
        private int[] parents;// 保存有多少圈子
        private int[] size; // 保存每个圈子内元素数量

        public UnionFind(int parentSize) {
            this.parents = new int[parentSize];
            this.size = new int[parentSize];
            for (int i = 0; i < parentSize; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        // 查找父节点
        public int find(int child) {
            if (child != parents[child]) {
                parents[child] = find(parents[child]);
            }
            return parents[child];
        }

        // 归并
        public void merge(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY) {
                return;
            }
            parents[parentX] = parentY; // 归并过程选择将X父节点指向Y
            size[parentY] += size[parentX]; // 圈子合并的同时更新父圈子内数量
        }

        public int getSize(int index) {
            int parent = find(index);
            return size[parent]; // 注意这里找圈子内数量时候用的是父节点
        }
    }
}
