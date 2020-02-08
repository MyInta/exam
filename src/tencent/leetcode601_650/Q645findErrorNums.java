package tencent.leetcode601_650;

/**
 * @author inta
 * @date 2020/2/5
 * @describe 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，
 * 导致集合丢失了一个整数并且有一个元素重复。
 *
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 *
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 */
public class Q645findErrorNums {
    //没有空间要求的话，我会先想到set，但因为题给长度有限制，直接使用数组来存储
    public int[] findErrorNums(int[] nums) {
        int first = 1, second = 0;
        int[] count = new int[10001];
        for (int num : nums) {
            count[num] ++;
            if (count[num] == 2) first = num;
        }
        for (int i = 1; i <= nums.length; i ++) {
            if (count[i] == 0) {
                second = i;
                break;
            }
        }
        return new int[]{first, second};
    }

    //大神思路借鉴了找两个重复值那道题目，但效率没有上面自己方法高
    public int[] findErrorNums2(int[] nums) {
        int len = nums.length;
        //统计nums累加值
        int sum = 0;
        //重复元素
        int doub = 0;
        for (int i = 0; i < len; i ++) {
            int temp = nums[Math.abs(nums[i]) - 1];
            //遍历每个元素值对应位置元素值，如果值大于0，说明没遇到过，小于0为遇到过一次
            if (temp > 0) {
                //没遇到过，所以要将该位置元素变为负值
                nums[Math.abs(nums[i]) - 1] = -temp;
            } else {
                //如果小于0(因为nums里面所有元素范围在[1,n]非0，不存在0)
                doub = Math.abs(nums[i]);
            }
            //累加值
            sum += Math.abs(nums[i]);
        }
        //找缺失的元素，规律上我们用等差公式求和总值-sum即缺失元素-重复元素值,这里不考虑优化越界，因为值最多是10000
        return new int[]{doub, doub + len * (len + 1) / 2 - sum};
    }
}
