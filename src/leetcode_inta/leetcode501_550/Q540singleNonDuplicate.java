package leetcode_inta.leetcode501_550;

/**
 * @author inta
 * @date 2020/6/9
 * @describe 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: [3,3,7,7,10,11,11]
 * 输出: 10
 * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
 */
public class Q540singleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        //遍历一遍就可以得到答案
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    //但题目要求logn和o1复杂度，就是二分求解,官方有个比较有意思的思路，就是依据偶数索引二分
    public int singleNonDuplicate2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //如果获得的mid是奇数，我们要修改到偶数位
            if (mid % 2 == 1) mid --;
            //如果偶数索引后一个和前面相同，说明答案在后半段
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                //反之答案在前半段
                right = mid;
            }
        }
        return nums[left];
    }
}
