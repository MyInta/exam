package leetcode_inta.exam_main.leetcode_exam2021.D222;

import java.util.*;

/**
 * @author inta
 * @date 2021/1/3
 * @describe
 */
public class Q2 {
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int delicious : deliciousness) {
            map.put(delicious, map.getOrDefault(delicious, 0) + 1);
        }
        long res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < 22; i++) {
                int target = 1 << i;
                int second = target - entry.getKey();
                if (map.containsKey(second)) {
                    if (second != entry.getKey()) {
                        res += 1l * map.get(second) * entry.getValue();
                    } else {
                        // 注意这里因为自身下次不可达，为统一数据量，乘以2,而原先本该除以2，抵消了
                        res += 1l * entry.getValue() * (entry.getValue() - 1);
                    }
                }
            }
        }
        // 最后结果需要消去重复计算值
        return (int)(res / 2 % 1_000_000_007);
    }
}
