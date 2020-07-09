package leetcode_inta.exam_main.leetcode_exam2020.D195;

/**
 * @author inta
 * @date 2020/6/28
 * @describe
 */
public class Q2 {
    public boolean canArrange(int[] arr, int k) {
        if (arr.length % 2 != 0) return false;
        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            boolean find = false;
            for (int j = i + 1; j < arr.length; j++) {
                if (visited[j]) continue;
                if (((arr[i] % k) + (arr[j] % k)) % k == 0) {
                    visited[j] = true;
                    find = true;
                    break;
                }
            }
            if (!find) return false;
        }
        return true;
    }

    public boolean canArrange2(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % k;
        }
        int[] counts = new int[k];
        for (int a : arr) {
            if (a >= 0) {
                counts[a] ++;
            } else {
                counts[-a] --;
            }
        }
        if (counts[0] % 2 != 0) return false;
        for (int i = 1; i <= k / 2; i++) {
            if (counts[i] != counts[k - i]) return false;
        }
        return true;
    }
}
