package leetcode_inta.exam_main.leetcode_exam2022.D274;

import java.util.*;

/**
 * @author inta
 * @date 2022/1/2
 * @describe
 */
public class Q4 {
    // 先获取集群，在集群里面考虑下方试试[1,0,0,2,1,4,7,8,9,6,7,10,8]
    // 贪心做减法，某个位置被标记多次，超过两次那么整体数量减一
//    public int maximumInvitations(int[] favorite) {
//        int[] counts = new int[favorite.length];
//        for (int i = 0; i < favorite.length; i++) {
//            counts[i] += favorite[favorite[i]] == i ? 0 : 1;
//            counts[favorite[i]]++;
//        }
//        int res = favorite.length;
//        for (int count : counts) {
//            if (count > 2) {
//                res--;
//            }
//        }
//        return res;
//    }
    private int[] parents;
    public int maximumInvitations(int[] favorite) {
        parents = new int[favorite.length];
        for (int i = 0; i < favorite.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < favorite.length; i++) {
            merge(i, favorite[i]);
        }
        // 合并之后取各个群体
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int num : favorite) {
            int curP = find(num);
            Set<Integer> set = map.getOrDefault(curP, new HashSet<>());
            set.add(curP);
            map.put(curP, set);
        }
        // 然后遍历各个群体找数量最大值
        int res = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            Set<Integer> set = entry.getValue();
            Map<Integer, Integer> counts = new HashMap<>();
            int temp = set.size();
            for (int num : set) {
                counts.put(num, counts.getOrDefault(num, 0) + 1);
            }
            for (int num : set) {
                if (counts.get(num) > 2) {
                    temp--;
                }
            }
            res += temp;
        }
        return res;
    }

    private int find(int target) {
        if (parents[target] == target) {
            return target;
        }
        return find(parents[target]);
    }

    private void merge(int num1, int num2) {
        int pA = find(num1);
        int pB = find(num2);
        if (pA != pB) {
            parents[pA] = pB;
        }
    }
}
