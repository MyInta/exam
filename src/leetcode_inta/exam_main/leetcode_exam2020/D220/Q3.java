package leetcode_inta.exam_main.leetcode_exam2020.D220;

import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/12/20
 * @describe
 */
public class Q3 {
    //本能告诉我这是dp题目，怎么找状态转移方程？ dp[i] = Math.max(dp[i-1]...dp[i-k]) + nums[i]
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        pq.add(dp[0]);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = pq.peek() + nums[i];
            pq.add(dp[i]);
            if (i >= k && dp[i - k] == pq.peek()) pq.remove(dp[i - k]);
        }
        return dp[nums.length - 1];
    }

    //上面竞赛时候虽然过了，但是其实是存在问题的，案例考虑不充足如 [1,1,1,-2,-2,-2,1,1,1] k=3
    public int maxResult2(int[] nums, int k) {
        int res = nums[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);
        pq.add(new int[]{res, 0});
        for (int i = 1; i < nums.length; i++) {
            while (i - pq.peek()[1] > k) {
                pq.poll();
            }
            res = pq.peek()[0] + nums[i];
            pq.add(new int[]{res, i});
        }
        return res;
    }
}
