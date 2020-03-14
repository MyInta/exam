package leetcode_inta.leetcode101_150;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/11/12
 * @describe 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例 1:
 *
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 *
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 */
//O1空间的解法理解不了。。。
public class Q135candy {

    public int candy(int[] ratings) {
        //用两个数组来记录变化
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < ratings.length; i ++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i --) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < ratings.length; i ++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
    //使用On空间
    public int candy2(int[] ratings) {
        int[] dp = new int[ratings.length];
        dp[0] = 1;
        for (int i = 1; i < ratings.length; i ++) {
            if (ratings[i] > ratings[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }
        int sum = dp[dp.length - 1];
        for (int i = ratings.length - 2; i >= 0; i --) {
            if (ratings[i] > ratings[i + 1] && dp[i] <= dp[i + 1]) {
                dp[i] = dp[i + 1] + 1;
            }
            sum += dp[i];
        }
        return sum;
    }
}
