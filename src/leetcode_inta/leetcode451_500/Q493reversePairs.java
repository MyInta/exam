package leetcode_inta.leetcode451_500;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/11/28
 * @describe 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 
 * 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 *
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 */
public class Q493reversePairs {
    //状态转移方程 dp[i] = dp[i + 1] + (满足nums[i]>2*nums[j](j∈[i+1,end])的数量)
    //上面思路不对，实际实现的是暴力解法，耗时过多
    //看了评论得到的思路，使用二分查找数量，维持一个集合保存有序的2*nums[i]的值
    //从右往左遍历，每加一个元素我们考虑添加数量时二分在集合中获取，
    //而新的值作为被比较*2以添加到集合中且要保持有序
    public int reversePairs(int[] nums) {
        if (nums.length < 2) return 0;
        int res = 0;
        List<Long> list = new ArrayList<>();
//        list.add((long)2*nums[nums.length - 1]);
        for (int i = nums.length - 1; i >= 0; i--) {
            res += binarySearch((long)nums[i], list);
            list.add(binarySearch((long)2*nums[i],list), (long)2*nums[i]);
        }
        return res;
    }
    //从集合中找目标值的索引位置，即比其小的元素数量
    private int binarySearch(long num, List<Long> list) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
