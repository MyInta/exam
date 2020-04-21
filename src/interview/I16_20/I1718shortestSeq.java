package interview.I16_20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2020/4/21
 * @describe 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 *
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 *
 * 示例 1:
 *
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 *
 * 示例 2:
 *
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 *
 * 提示：
 *
 *     big.length <= 100000
 *     1 <= small.length <= 100000
 *
 */
public class I1718shortestSeq {
    public int[] shortestSeq(int[] big, int[] small) {
        //题意说了，短的元素各不相同,可以用set，而长的有重复，窗口设置用map
        Set<Integer> set = new HashSet<>();
        for (int s : small) set.add(s);
        //key表示元素，value表示数量
        Map<Integer, Integer> window = new HashMap<>();
        int left = 0, right = 0, count = 0;
        int[] res = new int[2];
        res[0] = -1;
        res[1] = 100000;
        while (right < big.length) {
            int key = big[right];
            int value = window.getOrDefault(key, 0);
            //如果key是目标元素
            if (set.contains(key)) {
                //如果之前没添加过，就统计数量+1
                if (value == 0) count ++;
                window.put(key, value + 1);
            }
            //如果count统计元素数量和短数组元素数量一致，就考虑其为备选索引边界
            while (count == set.size()) {
                if (right - left < res[1] - res[0]) {
                    res[0] = left;
                    res[1] = right;
                }
                if (set.contains(big[left])) {
                    window.put(big[left], window.get(big[left]) - 1);
                    if (window.get(big[left]) == 0) count --;
                }
                left ++;
            }
            right ++;
        }
        return res[0] == -1 ? new int[0] : res;
    }
}
