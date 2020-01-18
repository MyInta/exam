package tencent.leetcode901_950;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/15
 * @describe 给定一个整数数组 nums，将该数组升序排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：[5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -50000 <= A[i] <= 50000
 */
public class Q912sortArray {
    //这道题不难，很简单，但是题意不是为了简单而出的，是考量排序算法，那么我们就做排序算法的
    //我喜欢用冒泡排序，这个实现下，其他也抽空实现下,这个竟然超时了！！！时间要求还挺高的
    public List<Integer> sortArray(int[] nums) {
        int index = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            left = index;
            for (int i = left; i < right; i ++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    index = i;
                }
            }
            right = index;
            for (int i = right; i > left; i --) {
                if (nums[i] < nums[i - 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                    index = i;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(num);
        }
        return res;
    }

    //那就使用归并来试一下
    public List<Integer> sortArray2(int[] nums) {
        int left = 0, right = nums.length - 1;
        List<Integer> res = sort01(nums, left, right);
        return res;
    }
    private List<Integer> sort01(int[] nums, int left, int right) {
        List<Integer> res = new ArrayList<>();
        if (left > right) return res;
        if (left == right) {
            res.add(nums[left]);
            return res;
        }
        int mid = left + ((right - left) >> 1);
        List<Integer> l = sort01(nums, left, mid);
        List<Integer> r = sort01(nums, mid + 1, right);
        int i = 0, j = 0;
        while (i < l.size() && j < r.size()) {
            if (l.get(i) <= r.get(j)) {
                res.add(l.get(i));
                i ++;
            } else {
                res.add(r.get(j));
                j ++;
            }
        }
        while (i < l.size()) res.add(l.get(i ++));
        while (j < r.size()) res.add(r.get(j ++));
        return res;
    }
}
