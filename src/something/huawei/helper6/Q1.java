package something.huawei.helper6;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/20
 * @describe
 * 现有一个任务数组，数组元素表示在这1秒内新增的任务格式个数且每秒都有新增任务
 * 假设GPU最多一次执行n个任务,一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成
 * 输入：
 * 第一个参数为GPU最多一次执行的任务个数，范围[1,10000]
 * 第二个参数为任务数组长度，范围[1,10000]
 * 第3个参数为任务数组，范围[1,10000]
 *
 * 输出：
 * 执行完所有任务最少需要多少秒
 *
 * 输入：
 * 3
 * 5
 * 1 2 3 4 5
 * 输出：
 * 6
 */
public class Q1 {
    public static int method(int count, String[] taskArr) {
        int[] nums = new int[taskArr.length];
        for (int i = 0; i < taskArr.length; i++) {
            nums[i] = Integer.valueOf(taskArr[i]);
        }
        int res = 0;
        int resume = 0;
        for (int num : nums) {
            int curValue = num + resume;
            if (curValue >= count) {
                resume = curValue - count;
            } else {
                resume = 0;
            }
            res++;
        }
        res += resume / count;
        resume = resume % count;
        return resume > 0 ? res + 1 : res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int GPUCount = Integer.valueOf(sc.nextLine());
        sc.nextLine();
        String[] taskArr = sc.nextLine().split(" ");
        System.out.println(method(GPUCount, taskArr));
    }
}
