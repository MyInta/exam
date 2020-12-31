package leetcode_inta.leetcode151_200;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/9/7
 * @describe 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 *
 * 说明:
 *
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class Q164MaximumGap {
    public int maximumGap(int[] nums) {
        int res = 0;
        if (nums.length < 2) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        return res;
    }

    //要满足时间复杂度，使用基数排序或者桶排，这里我先使用桶排尝试一下
    public int maximumGap2(int[] nums) {
        if (nums.length < 2) return 0;
        //获得数组的最大和最小元素
        int nums_min = Arrays.stream(nums).min().getAsInt();
        int nums_max = Arrays.stream(nums).max().getAsInt();
        //获得切割成数组间隔数量的距离
        int dis = Math.max(1, (nums_max - nums_min) / (nums.length - 1));
        //桶数量 +1是为了保证最大值可以被分配在一个桶内，我们的桶是[)模式的
        int size = (nums_max - nums_min) / dis + 1;
        //维持桶中最大最小元素
        int[][] counts = new int[size][2];
        for (int[] count : counts) {
            //初始值都为-1
            Arrays.fill(count, -1);
        }
        int res = 0;
        for (int num : nums) {
            //找到新添加元素对应桶中的索引位置
            int index = (num - nums_min) / dis;
            //如果桶中还没添加过元素
            if (counts[index][0] == -1) {
                counts[index][0] = num;
                counts[index][1] = num;
            } else {
                counts[index][0] = Math.min(counts[index][0], num);
                counts[index][1] = Math.max(counts[index][1], num);
            }
        }
        //此时遍历一圈后，已经将元素都放进桶里面，再一次遍历来找差值,准备一个前索引
        int pre = -1;
        for (int i = 0; i < counts.length; i++) {
            //考虑到桶可能为空时要跳过
            if (counts[i][0] == -1) continue;
            //如果已保存前一索引信息，比较前桶元素最大值和当前桶最小值的差值
            if (pre != -1) {
                res = Math.max(res, counts[i][0] - counts[pre][1]);
            }
            pre = i;
        }
        return res;
    }
}
