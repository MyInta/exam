package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/25
 * @describe 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。
 * 你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 *
 * 以任意顺序返回这两个数字均可。
 *
 * 示例 1:
 *
 * 输入: [1]
 * 输出: [2,3]
 *
 * 示例 2:
 *
 * 输入: [2,3]
 * 输出: [1,4]
 *
 * 提示：
 *
 *     nums.length <= 30000
 *
 */
public class I1719missingTwo {
    //数学题。。。1-n和-nums和 剩下的不就是那两个缺失的值么
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int sum = 0;
        for (int num : nums) sum += num;
        //因为nums.length<=30000直接乘不越界
        int all = (1 + n) * n / 2;
        int twoSum = all - sum;
        int target = twoSum / 2;
        sum = 0;
        for (int num : nums) {
            if (num <= target) sum += num;
        }
        int first = (1 + target) * target / 2 - sum;
        return new int[]{first, twoSum - first};
    }
}
