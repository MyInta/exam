package something.didi;

import java.util.Scanner;

/**
 * @author inta
 * @date 2020/9/13
 * @describe
 */
public class Q2 {

    private static String solution(int[][] prices, int n, int k) {
        int[] parents = new int[n + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int[] price : prices) {
            if (price[2] > k) continue;
            int x = findP(price[0], parents);
            int y = findP(price[1], parents);
            if (x != y) {
                parents[x] = y;
            }
        }
        int sum = 0;
        int target = -1;
        for (int i = 1; i <= n; i++) {
            int p = findP(i, parents);
            if (p != target) {
                target = p;
                sum ++;
            }
        }
        return sum <= 1 ? "Yes" : "No";
    }
    private static int findP(int i, int[] parents) {
        if (parents[i] == i) return i;
        return findP(parents[i], parents);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.valueOf(in.nextLine());
        while (t > 0) {
            String nextline = in.nextLine();
            String[] splits = nextline.split(" ");
            int n = Integer.valueOf(splits[0]);
            int m = Integer.valueOf(splits[1]);
            int k = Integer.valueOf(splits[2]);
            int[][] prices = new int[m][3];
            for (int i = 0; i < m; i++) {
                String temp = in.nextLine();
                String[] temp_splits = temp.split(" ");
                prices[i][0] = Integer.valueOf(temp_splits[0]);
                prices[i][1] = Integer.valueOf(temp_splits[1]);
                prices[i][2] = Integer.valueOf(temp_splits[2]);
            }
            System.out.println(solution(prices, n, k));
            t--;
        }
    }
}
