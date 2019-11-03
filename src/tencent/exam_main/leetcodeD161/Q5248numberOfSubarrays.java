package tencent.exam_main.leetcodeD161;

import java.util.ArrayList;

/**
 * @author inta
 * @date 2019/11/3
 * @describe 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果某个子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中「优美子数组」的数目。
 */
public class Q5248numberOfSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        //用来记录奇数的索引坐标(首位添加一个“奇数”)
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(-1);
        for (int i = 0; i < nums.length; i ++) {
            if ((nums[i] & 1) == 1) arrayList.add(i);
        }
        arrayList.add(nums.length);
        //如果数量小于k,无解
        if (arrayList.size() - 2 < k) return 0;
        //我们要的解为奇数往前遇到另一个奇数前非奇数部分乘上往后遇到第一个奇数之前的非奇数部分商
        int res = 0;
        for (int i = 1; i < arrayList.size() - k; i ++) {
            int left = arrayList.get(i);
            int right = arrayList.get(i + k - 1);
            res += (left - arrayList.get(i - 1)) * (arrayList.get(i + k) - right);
        }
        return res;
    }
}
