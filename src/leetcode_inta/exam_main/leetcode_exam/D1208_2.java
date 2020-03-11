package leetcode_inta.exam_main.leetcode_exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/12/8
 * @describe
 */
public class D1208_2 {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i ++) {
            List<Integer> v = map.getOrDefault(groupSizes[i], new ArrayList<>());
            v.add(i);
            //长度达到要求
            if (v.size() == groupSizes[i]) {
                res.add(v);
                //重置
                map.put(groupSizes[i], new ArrayList<>());
                continue;
            }
            map.put(groupSizes[i], v);
        }
        return res;
    }
}
