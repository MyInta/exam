package leetcode_inta.leetcode451_500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/12/30
 * @describe 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 */
public class Q451frequencySort {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //降序排列
        PriorityQueue<Map.Entry<Character, Integer>> p = new PriorityQueue<>((x,y)->y.getValue()-x.getValue());
        for (Map.Entry entry : map.entrySet()) {
            //挨个添加
            p.offer(entry);
        }
        StringBuilder sb = new StringBuilder();
        //发现优先队列的iterator不能保证顺序输出，还是得用poll方式遍历
//        for (Map.Entry<Character, Integer> me : p) {
        while (!p.isEmpty()) {
            Map.Entry<Character, Integer> me = p.poll();
            char c = me.getKey();
            int times = me.getValue();
            for (int i = 0; i < times; i ++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //hash数组
    public String frequencySort2(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c] ++;
        }
        int[] count_copy = Arrays.copyOf(count, count.length);
        Arrays.sort(count);

        StringBuilder sb = new StringBuilder();
        for (int i = count.length - 1; i >= 0 && count[i] > 0; i --) {
            for (int j = 0; j < count_copy.length; j ++) {
                //只要找到与排序后count元素的数量值一样的未排序的copy中对应元素值的元素j即可
                if (count[i] == count_copy[j]) {
                    while (count_copy[j] -- > 0) {
                        sb.append((char)j);
                    }
                }
            }
        }
        return sb.toString();
    }
}
