package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/10/21
 * @describe 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明: 
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 */
public class Q134canCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i ++) {
            nums[i] = gas[i] - cost[i];
        }
        for (int j = 0; j < len; j ++) {
            //优化用的起始点，if中语句必执行，所以不用担心为-1，往后遍历了几个，就在j上+几个，然后从下一个开始
            int[] newStart = {-1};
            if (isAble(nums, j, newStart)) return j;
            //考虑优化的话，在每次遍历到第一个将总和为负的点时，起始点从此点+1索引开始（之前任何一个点绝对也都到不了一圈的）
            j += newStart[0];
        }
        return -1;
    }
    private boolean isAble(int[] nums, int start, int[] newStart) {
        int sum = 0;
        for (int i = start; i < nums.length; i ++) {
            newStart[0] ++;
            sum += nums[i];
            if (sum < 0) {
                return false;
            }
        }
        for (int j = 0; j < start; j ++) {
            sum += nums[j];
            if (sum < 0) {
                return false;
            }
        }
        return true;
    }

    //简易版本
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) return -1;
        int sum = 0;
        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i ++) {
            total += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }
        return total >= 0 ? start : -1;
    }

}
