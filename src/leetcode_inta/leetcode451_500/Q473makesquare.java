package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2019/11/5
 * @describe 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，
 * 请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 *
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 *
 * 示例 1:
 *
 * 输入: [1,1,2,2,2]
 * 输出: true
 *
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 *
 * 输入: [3,3,3,3,4]
 * 输出: false
 *
 * 解释: 不能用所有火柴拼成一个正方形。
 * 注意:
 *
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 *
 */
public class Q473makesquare {
    //深度遍历的体型，目标值和层级,层级depth固定为了4
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length <= 3) return false;
        //找到最大值
        int maxNum = 0;
        //以及总和
        int sumNum = 0;
        for (int num : nums) {
            if (num > maxNum) maxNum = num;
            sumNum += num;
        }
        //边需要的长度
        int sideSum = sumNum / 4;
        //如果总和不能被4整除，或者单边小于最大值，不可能为正方形
        if (sumNum % 4 != 0 || maxNum > sideSum) return false;
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, 4, sideSum, 0, 0, visited);
    }

    /**
     *
     * @param nums
     * @param depth 层级
     * @param target 目标值
     * @param curSum 当前累加值
     * @param start 从哪个索引开始
     * @param visited 该点是否访问过
     * @return
     */
    private boolean dfs(int[] nums, int depth, int target, int curSum, int start, boolean[] visited) {
        //如果遍历到了第0层。说明可以构建
        if (depth == 0) return true;
        //如果当前累加到了目标值，就重置当前值并进入下一层遍历
        if (curSum == target) return dfs(nums, depth - 1, target, 0, 0, visited);

        for (int i = start; i < nums.length; i ++) {
            if (!visited[i] && curSum + nums[i] <= target) {
                visited[i] = true;
                //如果可以深度遍历下去并成功实现，就返回true
                if (dfs(nums, depth, target, curSum + nums[i], i + 1, visited)) return true;
                //否者重置,并找下一个点继续
                visited[i] = false;
            }
        }
        return false;
    }
}
