package leetcode_inta.exam_main.leetcode_exam2020.D205;

/**
 * @author inta
 * @date 2020/9/6
 * @describe
 */
public class Q4 {

    //A或B的叶子节点，只能有两个，超过就说明有断层
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int res = 0;
        int[] parentA = new int[n + 1];
        int[] parentB = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parentA[i] = i;
            parentB[i] = i;
        }
        for (int[] edge : edges) {
            int type = edge[0];
            int first = edge[1];
            int second = edge[2];
            if (type == 3) {
                if (parentA[first] == parentA[second] && parentB[first] == parentB[second]) {
                    res++;
                }
                parentA[first] = parentA[second];
                parentB[first] = parentB[second];
            } else if (type == 1) {
                if (parentA[first] == parentA[second]) {
                    res++;
                }
                parentA[first] = parentA[second];
            } else {
                if (parentB[first] == parentB[second]) {
                    res++;
                }
                parentB[first] = parentB[second];
            }
        }
        if (!check(parentA) || !check(parentB)) return -1;
        return res;
    }
    private boolean check(int[] p) {
        int target = p[1];
        for (int i = 1; i < p.length; i++) {
            if (p[i] != target) return false;
        }
        return true;
    }
}
