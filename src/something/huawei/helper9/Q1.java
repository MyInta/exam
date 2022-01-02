package something.huawei.helper9;

import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/27
 * @describe
 */
public class Q1 {
    private static int res = 0;
    public static int method(int count, int[] nums) {
        int resume = 0;
        resume = getResume(count, nums, resume);
        res += resume / count;
        resume = resume % count;
        return resume > 0 ? res + 1 : res;
    }

    private static int getResume(int count, int[] nums, int resume) {
        for (int num : nums) {
            int curValue = num + resume;
            if (curValue >= count) {
                resume = curValue - count;
            } else {
                resume = 0;
            }
            res++;
        }
        return resume;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int GPUCount = Integer.valueOf(sc.nextLine());
        sc.nextLine();
        String[] taskArr = sc.nextLine().split(" ");
        int[] nums = new int[taskArr.length];
        for (int i = 0; i < taskArr.length; i++) {
            nums[i] = Integer.valueOf(taskArr[i]);
        }
        System.out.println(method(GPUCount, nums));
    }
}
