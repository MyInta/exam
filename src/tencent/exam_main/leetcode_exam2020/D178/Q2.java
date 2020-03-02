package tencent.exam_main.leetcode_exam2020.D178;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/3/1
 * @describe
 */
public class Q2 {
    public String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        int len = votes[0].length();
        for (String vote : votes) {
            //统计每个字符在对应位置上的数量
            for (int i = 0; i < len; i ++) {
                if (!map.containsKey(vote.charAt(i))) {
                    map.put(vote.charAt(i), new int[len]);
                }
                map.get(vote.charAt(i))[i] ++;
            }
        }
        //此时map已经是一个k-字母 v-一个数组，该数组表示字符串vote每个位置上，k出现的次数
        Character[] voteChars = new Character[len];
        int index = 0;
        for (char c : votes[0].toCharArray()) {
            voteChars[index ++] = c;
        }
        Arrays.sort(voteChars, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                //根据字母在头到尾中出现的次数排
                for (int i = 0; i < len; i ++) {
                    if (map.get(o1)[i] != map.get(o2)[i]) return map.get(o2)[i] - map.get(o1)[i];
                }
                return o1 - o2;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (char c : voteChars) {
            sb.append(c);
        }
        return sb.toString();
    }
}
