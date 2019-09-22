package tencent.exam_main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/9/22
 * @describe 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，
 * 其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * 示例 1:
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 *
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 */
public class Main_4 {
    private Map<Integer, Character> map;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chars = s.toCharArray();
        map = new HashMap<>();
        int[] nums = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            map.put(i, chars[i]);
            nums[i] = i;
        }
        for (List<Integer> l:pairs) {
            int temp = nums[l.get(0)];
            nums[l.get(0)] = nums[l.get(1)];
            nums[l.get(1)] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < map.size(); j++) {
            sb.append(map.get(nums[j]));
        }
        return sb.toString();
    }
}
