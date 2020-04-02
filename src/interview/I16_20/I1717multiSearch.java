package interview.I16_20;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/4/2
 * @describe 给定一个较长字符串big和一个包含较短字符串的数组smalls，
 * 设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
 * 输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 *
 * 示例：
 *
 * 输入：
 * big = "mississippi"
 * smalls = ["is","ppi","hi","sis","i","ssippi"]
 * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
 *
 * 提示：
 *
 *     0 <= len(big) <= 1000
 *     0 <= len(smalls[i]) <= 1000
 *     smalls的总字符数不会超过 100000。
 *     你可以认为smalls中没有重复字符串。
 *     所有出现的字符均为英文小写字母。
 *
 */
public class I1717multiSearch {
    public int[][] multiSearch(String big, String[] smalls) {
        List<Integer>[] counts = new ArrayList[smalls.length];
        for (int i = 0; i < smalls.length; i++) {
            String small = smalls[i];
            int index = -1;
            counts[i] = new ArrayList<>();
            if (small.equals("")) {
                continue;
            }
            while (big.indexOf(small, index + 1) != -1) {
                index = big.indexOf(small, index + 1);
                counts[i].add(index);
//                System.out.println(index);
            }
        }
        int[][] res = new int[smalls.length][];
        for (int i = 0; i < res.length; i++) {
            List<Integer> list = counts[i];
            int[] temp = new int[list.size()];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = list.get(j);
            }
            res[i] = temp;
        }
        return res;
    }
}
