package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/9/5
 * @describe 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 */
public class Q41FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        //遍历一遍后得到1-n排列数字 期间存在变数
        for (int cur = 0; cur <= len-1; cur++) {
            int temp = nums[cur];
            while (nums[cur] > 0 && temp <= len&& temp != nums[temp - 1]) {
                //交换
                nums[cur] = nums[temp - 1];
                nums[temp - 1] = temp;
                temp = nums[cur]; //因为while里面用到这个是潜在变量
            }
        }
        //遍历找出变数
        for (int i = 0; i< len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //看了一遍都没有变数，说明是1-n稳了，那就是缺n+1
        return len + 1;
    }
}
