package something.huawei.helper10;

import java.util.Scanner;

/**
 * @author inta
 * @date 2022/1/8
 * @describe
 */
public class Q2 {
    private static int res = Integer.MAX_VALUE;

    private static void dfs(int[] nums, int start, int end, int step) {
        if (start == end) {
            res = Math.min(res, step);
            return;
        }
        if (start > end) {
            return;
        }
        dfs(nums, start + nums[start], end, step + 1);
    }

    private static void method(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        if (nums.length == 1) {
            res = 1;
            return;
        }
        for (int i = 0; i <= Math.min(nums.length / 2 - 1, nums[0]); i++) {
            dfs(nums, i, nums.length - 1, 1);
        }
    }

    // 输入数组，第一步最多走len/2这是限制条件
    // 7 5 9 4 2 6 8 3 5 4 3 9
    // 1 2 3 7 1 5 9 3 2 1
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.valueOf(strs[i]);
        }
        method(nums);
        if (res == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(res);
        }
    }
}