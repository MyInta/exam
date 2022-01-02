package something.huawei.helpdir;

import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/5
 * @describe
 */
public class Q1 {
    public static int method(int[] arr) {
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

    // 调试方法
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] arrStr = line.split(",");
        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(arrStr[i]);
        }
        System.out.println(method(arr));
    }
}
