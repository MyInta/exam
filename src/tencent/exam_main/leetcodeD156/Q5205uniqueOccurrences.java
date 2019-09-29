package tencent.exam_main.leetcodeD156;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2019/9/29
 * @describe 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 */
public class Q5205uniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr.length == 0) {
            return true;
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : arr) {
            if (!hm.containsKey(i)) {
                hm.put(i, 1);
            }
            hm.put(i, hm.get(i) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int value = entry.getValue();
            if (set.contains(value)) {
                return false;
            }
            set.add(value);
        }
        return true;
    }
}
