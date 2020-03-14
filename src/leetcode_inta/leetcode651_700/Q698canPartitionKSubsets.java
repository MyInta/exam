package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2019/11/4
 * @describe 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *  
 *
 * 注意:
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 *
 */
public class Q698canPartitionKSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int max_num = 0;
        int sum = 0;
        for (int num : nums) {
            if (num > max_num) max_num = num;
            sum += num;
        }
        int target = sum / k;
        //如果总和划分之后非整，或者划分的段落和还没有一个元素大，说明不可完美切分k份
        if (sum % k != 0 || target < max_num) return false;
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, k, target, 0, 0, visited);
    }
    private boolean dfs(int[] nums, int k, int target, int cur, int start, boolean[] visited) {
        if (k == 0) return true;
        //当当前累加满足目标值，进入下一层遍历
        if (cur == target) {
            return dfs(nums, k - 1, target, 0, 0, visited);
        }
        //从出发点开始，找下一个满足target的组合
        for (int i = start; i < nums.length; i ++) {
            //如果是未访问过的，且在目标值内的，进入遍历
            if (!visited[i] && cur + nums[i] <= target) {
                visited[i] = true;
                if (dfs(nums, k, target, cur + nums[i], i + 1, visited)) return true;
                //否者，回溯，将遍历信息重置
                visited[i] = false;
            }
        }
        //遍历了一圈都没找到合适的，说明不存在，返回false
        return false;
    }
}
