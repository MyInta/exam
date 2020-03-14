package leetcode_inta.leetcode401_450;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/31
 * @describe 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 */
public class Q442findDuplicates {
    //这道题目要实现很简单，难在优化上，题目要求On时间和不使用额外空间
    public List<Integer> findDuplicates(int[] nums) {
        //我还是想先排序实现下答案,显然，这么做效率低下
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int cur = 0;
        while (cur < nums.length - 1) {
            if (nums[cur] == nums[cur + 1]) {
                res.add(nums[cur]);
                cur ++;
            }
            cur ++;
        }
        return res;
    }

    //大神们的思路是使用输入数组作为承载对象，规避新空间的开辟
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        for (int i = 0; i < nums.length; i ++) {
            //题意说元素1-n，那么索引范围是[0,n-1]需要减1
            int index = Math.abs(nums[i]) - 1;
            //题意知nums元素没有0
            if (nums[index] < 0) {
                //小于0，说明该位置元素被修改过，即某处nums元素值指向过该位置，即有重复对象
                res.add(index + 1);
                //或者写成这样也一样
//                res.add(Math.abs(nums[i]));
            } else {
                //如果不为负值，说明我们这次指向该位置元素还是第一次，做个标记，说明我们来过了，即搞成负值
                nums[index] = -nums[index];
            }
        }
        return res;
    }
}
