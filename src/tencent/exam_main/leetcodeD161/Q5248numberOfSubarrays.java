package tencent.exam_main.leetcodeD161;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    //挨个累加，没有遇到奇数之前，累加有多少非奇数，除了开头（所以增设了1 map的（0，1））
    public int numberOfSubarrays2(int[] nums, int k) {
        int s = 0;
        Map<Integer, Integer> sum = new HashMap<>();
        sum.put(0, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            //如果遇到奇数，那么就将指针向后移动，要开始新的map计数了
            if ((nums[i] & 1) == 1) {
                s ++;
            }
            //当达到数量k之后，每一次往后的遍历，不管当前是不是奇数，都会累加k元素之前的计数数量
            //其实本质上和第一种方法的左右相乘一样，只是后者是以一个个加上去的形式搞定的，譬如原本i*j，后者是i+i..+i(j个)
            if (s - k >= 0) {
                res += sum.get(s - k);
            }
            sum.put(s, sum.getOrDefault(s, 0) + 1);
        }
        return res;
    }
}
