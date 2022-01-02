package something.huawei.helper8;

import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/23
 * @describe
 */
public class Q1 {
    public static int method(String[] arrStr) {
        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(arrStr[i]);
        }
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i >= 3) {
                dp[i] = Math.max(dp[i - 3], dp[i - 1] + arr[i]);
            } else {
                dp[i] = Math.max(0, i == 0 ? arr[i] : dp[i - 1] + arr[i]);
            }
        }
        return dp[arr.length - 1];
    }

    // 1,-5,-6,4,3,6,-2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] arrStr = line.split(",");
        System.out.println(method(arrStr));
    }
}
