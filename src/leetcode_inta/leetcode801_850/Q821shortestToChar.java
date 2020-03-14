package leetcode_inta.leetcode801_850;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/28
 * @describe 给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。
 *
 * 示例 1:
 *
 * 输入: S = "loveleetcode", C = 'e'
 * 输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 * 说明:
 *
 * 字符串 S 的长度范围为 [1, 10000]。
 * C 是一个单字符，且保证是字符串 S 里的字符。
 * S 和 C 中的所有字母均为小写字母。
 *
 */
public class Q821shortestToChar {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];
        //保存下C位置索引即可
        List<Integer> c_idx = new ArrayList<>();
        int index = 0;
        for (char c : S.toCharArray()) {
            if (c == C) c_idx.add(index);
            index ++;
        }
        //首
        int head = c_idx.get(0);
        for (int i = 0; i < head; i ++) {
            res[i] = head - i;
        }
        //中间
        for (int j = 1; j < c_idx.size(); j ++) {
            int left = c_idx.get(j - 1);
            int right = c_idx.get(j);
            for (int k = left; k < right; k ++) {
                res[k] = Math.min(k - left, right - k);
            }
        }
        //尾
        int end = c_idx.get(c_idx.size() - 1);
        for (int i = end; i < S.length(); i ++) {
            res[i] = i - end;
        }
        return res;
    }

    //官解，左右两边遍历找最小
    public int[] shortestToChar2(String S, char C) {
        int[] res = new int[S.length()];
        char[] chars = S.toCharArray();
        int pre = -S.length();
        for (int i = 0; i < S.length(); i ++) {
            //遇到C就让pre指针移到该位置，目前保持pre为i的左侧最近一个C索引位置
            if (chars[i] == C) {
                pre = i;
            }
            res[i] = i - pre;
        }
        //改动pre从右往左遍历
        pre = 2 * S.length();
        for (int i = S.length() - 1; i >= 0; i --) {
            if (chars[i] == C) {
                pre = i;
            }
            res[i] = Math.min(res[i], pre - i);
        }
        return res;
    }
}
