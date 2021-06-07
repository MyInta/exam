package leetcode_inta.exam_main.leetcode_exam2021.D244;

/**
 * @author inta
 * @date 2021/6/6
 * @describe
 */
public class Q1 {
    // 获取行信息，比较
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(mat[i][j]);
            }
        }
        String matStr = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb2.append(target[i][j]);
            }
        }
        if (matStr.equals(sb2.toString())) {
            return true;
        }
        StringBuilder sb3 = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                sb3.append(target[j][i]);
            }
        }
        if (matStr.equals(sb3.toString())) {
            return true;
        }
        StringBuilder sb4 = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                sb4.append(target[i][j]);
            }
        }
        if (matStr.equals(sb4.toString())) {
            return true;
        }
        StringBuilder sb5 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                sb5.append(target[j][i]);
            }
        }
        return matStr.equals(sb5.toString());
    }
}
