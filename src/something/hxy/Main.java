package something.hxy;

import java.util.*;

/**
 * @author hxy
 * @date 2021/12/18
 * @describe
 */
public class Main {

    private static final String SPLIT = " ";

    private static final String ONE = "1";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str == null || str.isEmpty()) {
            return;
        }
        String[] arr = str.split(SPLIT);
        int len = arr.length;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            if (ONE.equals(arr[i])) {
                matrix[0][i] = 1;
            }
        }
        for (int i = 1; i < len; i++) {
            str = sc.nextLine();
            for (int j = i; j < len; j++) {
                arr = str.split(SPLIT);
                if (ONE.equals(arr[j])) {
                    matrix[i][j] = 1;
                }
            }
        }
        System.out.print(method(matrix));
    }

    private static int[] parents;

    private static int result;

    private static int method(int[][] matrix) {
        int n = matrix.length;
        parents = new int[n];
        result = n;
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (matrix[i][j] == 1) {
                    merge(i, j);
                }
            }
        }
        return result;
    }

    private static void merge(int i, int j) {
        int pa = find(i);
        int pb = find(j);
        if (pa == pb) {
            return;
        } else {
            for (int k = 0; k < parents.length; k++) {
                if (parents[k] == pa) {
                    parents[k] = pb;
                }
            }
        }
        result--;
    }

    private static int find(int i) {
        return parents[i];
    }

}
