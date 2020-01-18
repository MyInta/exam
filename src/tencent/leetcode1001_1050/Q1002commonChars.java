package tencent.leetcode1001_1050;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/14
 * @describe 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 */
public class Q1002commonChars {
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        int[] counts = new int[26];
        for (char c : A[0].toCharArray()) {
            counts[c - 'a'] ++;
        }
        for (int i = 1; i < A.length; i ++) {
            int[] temp = new int[26];
            for (char t : A[i].toCharArray()) {
                temp[t - 'a'] ++;
            }
            for (int k = 0; k < 26; k ++) {
                counts[k] = Math.min(counts[k], temp[k]);
            }
        }
        for (int i = 0; i < 26; i ++) {
            while (counts[i] > 0) {
                char c = (char) ('a' + i);
                res.add(c + "");
                counts[i] --;
            }
        }
        return res;
    }
}
