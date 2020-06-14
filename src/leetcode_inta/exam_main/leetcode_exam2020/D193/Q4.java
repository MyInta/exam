package leetcode_inta.exam_main.leetcode_exam2020.D193;

/**
 * @author inta
 * @date 2020/6/14
 * @describe
 */
public class Q4 {

    private class TreeAncestor {
        private int[] parents;
        public TreeAncestor(int n, int[] parent) {
            this.parents = parent;
            map = new int[n][n];
        }
        private int[][] map;
        public int getKthAncestor(int node, int k) {
            if (k == 0) return node;
            if (map[node][k] != 0) return map[node][k];
            map[node][k] = getKthAncestor(parents[node], k - 1);
            return map[node][k];
        }
    }
}
