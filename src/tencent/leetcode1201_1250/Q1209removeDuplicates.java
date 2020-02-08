package tencent.leetcode1201_1250;

/**
 * @author inta
 * @date 2020/2/8
 * @describe 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，
 * 并删除它们，使被删去的字符串的左侧和右侧连在一起。
 *
 * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
 *
 * 在执行完所有删除操作后，返回最终得到的字符串。
 *
 * 本题答案保证唯一。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd", k = 2
 * 输出："abcd"
 * 解释：没有要删除的内容。
 * 示例 2：
 *
 * 输入：s = "deeedbbcccbdaa", k = 3
 * 输出："aa"
 * 解释：
 * 先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
 * 再删除 "bbb"，得到 "dddaa"
 * 最后删除 "ddd"，得到 "aa"
 * 示例 3：
 *
 * 输入：s = "pbbcggttciiippooaais", k = 2
 * 输出："ps"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s 中只含有小写英文字母。
 *
 */
public class Q1209removeDuplicates {
    public String removeDuplicates(String s, int k) {
        //是否有删除操作
        boolean changed = true;
        while (changed) {
            //默认没有了
            changed = false;
            for (int i = 0; i < s.length() - k + 1; i ++) {
                char c = s.charAt(i);
                boolean del = true;
                for (int j = i + 1; j < i + k; j ++) {
                    if (s.charAt(j) != c) {
                        i += j - i - 1;
                        del = false;
                        break;
                    }
                }
                //如果找到符合要求，即需要删除
                if (del) {
                    s = s.substring(0, i) + s.substring(i + k);
                    changed = true;
                }
            }
        }
        return s;
    }
}
