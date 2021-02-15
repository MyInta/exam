package leetcode_inta.leetcode451_500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2021/2/3
 * @describe 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，
 * 每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 *
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 *
 * 提示：
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class Q480medianSlidingWindow {
    // 使用两个优先队列来保存中位数左右情况
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pqLeft = new PriorityQueue<>((a,b) -> b-a);
        PriorityQueue<Integer> pqright = new PriorityQueue<>();
        List<Double> list = new ArrayList<>();
        int[] kthList = new int[k];
        System.arraycopy(nums, 0, kthList, 0, k);
        Arrays.sort(kthList);
        for (int i = 0; i < k / 2; i++) {
            pqLeft.add(kthList[i]);
        }
        for (int j = k / 2; j < k; j++) {
            pqright.add(kthList[j]);
        }
        int to = k;
        int from = 0;
        int halfLength = k / 2;
        boolean oddFlag = nums.length % 2 == 0;
        int sum;
        double curMid;
        if (oddFlag) {
            sum = pqLeft.peek() + pqright.peek();
            curMid = sum * 1.0 / 2;
        } else {
            curMid = pqright.peek();
        }
        list.add(curMid);
        while (to < nums.length) {
            if (nums[to] < curMid) {
                pqLeft.add(nums[to]);
            } else {
                pqright.add(nums[to]);
            }
            if (pqLeft.size() > halfLength) {
                pqLeft.remove(nums[from]);
            } else {
                pqright.remove(nums[from]);
            }
            if (oddFlag) {
                sum = pqLeft.peek() + pqright.peek();
                curMid = sum * 1.0 / 2;
            } else {
                curMid = pqright.peek();
            }
            list.add(curMid);
            to++;
            from++;
        }
        double[] res = new double[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
