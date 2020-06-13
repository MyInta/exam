package leetcode_inta.leetcode451_500;

import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/6/13
 * @describe 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动将会使 n - 1 个元素增加 1。
 *
 *
 *
 * 示例:
 *
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 3
 *
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 *
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 */
public class Q453minMoves {
    //大佬的思维，n-1加一，就相当于最大元素-1，要使得元素都相等，也就是所有元素都减到最小
    public int minMoves(int[] nums) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            res += num;
            if (num < min) min = num;
        }
        //最终解为数组和-最小值和
        return res - min * nums.length;
    }
}
