package tencent.leetcode401_450;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/11
 * @describe 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 */
public class Q448findDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i ++) {
            int temp = nums[i];
            while (temp != nums[temp - 1]) {
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
                //需要一直遍历，所以也要修改temp指向,此时的nums[i]是新值
                temp = nums[i];
            }
        }
        //遍历进行完交换的数组，找缺失
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
