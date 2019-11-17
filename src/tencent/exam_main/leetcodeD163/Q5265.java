package tencent.exam_main.leetcodeD163;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/17
 * @describe
 */
public class Q5265 {
    //核心是去除当整体余1时候MIN(一个余数1,两个余数2和)，当整体余2时候MIN(一个余数2,两个余数1和)的最小值
    public int maxSumDivThree(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int num : nums) {
            res += num;
            if (num % 3 == 1) {
                list1.add(num);
            } else if (num % 3 == 2) {
                list2.add(num);
            }
        }
        if (res % 3 == 0) {
            return res;
        } else if (res % 3 == 1) {
            int min = list1.get(0);
            if (list2.size() >= 2) min = Math.min(min, list2.get(0) + list2.get(1));
            return res - min;
        } else if (res % 3 == 2) {
            int min = list2.get(0);
            if (list1.size() >= 2) min = Math.min(min, list1.get(0) + list1.get(1));
            return res - min;
        }
        return 0;
    }
}
