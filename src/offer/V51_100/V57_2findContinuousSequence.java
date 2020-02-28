package offer.V51_100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/28
 * @describe 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 *
 */
public class V57_2findContinuousSequence {
    //大神给的思路，滑窗来做
    public int[][] findContinuousSequence(int target) {
        int l = 1, r = 1, sum = 1;
        int end = target / 2 + 1;
        List<int[]> lists = new ArrayList<>();
        //右边界在中间值界限内
        while (r <= end) {
            //总和不够就往右移动一个，累加
            if (sum < target) {
                r ++;
                sum += r;
            } else if (sum > target) {
                //总和大了，就消减左边
                sum -= l;
                l ++;
            } else {
                //相等情况就添加
                int[] temp = new int[r - l + 1];
                int index = 0;
                for (int i = l; i <= r; i ++) {
                    temp[index ++] = i;
                }
                lists.add(temp);
                //添加完记得消减，左边移动一位
                sum -= l;
                l ++;
            }
        }
        int[][] res = new int[lists.size()][];
        for (int i = 0; i < lists.size(); i ++) {
            res[i] = lists.get(i);
        }
        return res;
    }
}
