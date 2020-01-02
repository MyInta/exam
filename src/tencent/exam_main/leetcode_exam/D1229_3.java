package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/29
 * @describe
 */
public class D1229_3 {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return tryReach(arr, start, visited);
    }
    private boolean tryReach(int[] arr, int start, boolean[] visited) {
        if (start < 0 || start >= arr.length || visited[start]) return false;
        visited[start] = true;
        int len = arr[start];
        if (len == 0) return true;
        return tryReach(arr, start + len, visited) || tryReach(arr, start - len, visited);
    }
}
