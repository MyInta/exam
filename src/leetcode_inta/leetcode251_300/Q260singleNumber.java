package leetcode_inta.leetcode251_300;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/11/20
 * @describe 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 *
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 *
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 */
public class Q260singleNumber {
    //暴力破解时间费
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int index = 0;
        for (int i = 0; i < nums.length; i ++) {
            boolean flag = true;
            for (int j = 0; j < nums.length; j ++) {
                if (i == j) continue;
                if ((nums[i] ^ nums[j]) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res[index ++] = nums[i];
            }
        }
        return res;
    }

    //稍微快乐一点，不够
    public int[] singleNumber2(int[] nums) {
        int[] res = new int[2];
        int index = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++) {
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i ++;
            } else {
                res[index ++] = nums[i];
            }
        }
        return res;
    }

    //使用异或操作
    public int[] singleNumber3(int[] nums) {
        int[] res = new int[2];
        //核心在于异或找到两个数的异或值，因为不相等，故非0，必有一个位置为1且，
        // 整个数组可以划分为该位置有1和无1两种
        int twoSum = 0;
        for (int num : nums) {
            twoSum ^= num;
        }
        //一般x & -x可以确定x为偶数时候的最大2的次幂值，x奇数时候为1，这里我们只是随便找一个twoSum中的1
        int mark = twoSum & -twoSum;
        for (int num : nums) {
            //mark只包含一个1，和其与的为0的说明该位置为o，反之
            if ((num & mark) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
