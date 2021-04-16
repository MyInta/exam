package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2021/4/16
 * @describe 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：s1 = "great", s2 = "rgeat"
 * 输出：true
 * 解释：s1 上可能发生的一种情形是：
 * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
 * 示例 2：
 * 输入：s1 = "abcde", s2 = "caebd"
 * 输出：false
 * 示例 3：
 * 输入：s1 = "a", s2 = "a"
 * 输出：true
 * 提示：
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 */
public class Q87isScramble {
    public boolean isScramble(String s1, String s2) {
        if (!match(s1, s2)) {
            return false;
        }
        if (s1.length() == 1) {
            return true;
        }
        for (int i = 1; i <= s1.length(); i++) {
            if (check(s1, i, s2)) {
                return true;
            }
        }
        return false;
    }

    // s1切分出[0,index]，s2找[0,index]或者[s2.length()-1-index, s2.length()-1]有无和其匹配的
    private boolean check(String s1, int index, String s2) {
        String target = s1.substring(0, index);
        String from1 = s2.substring(0, index);
        boolean first = false;
        if (match(target, from1)) {
            first = isScramble(target, from1);
        }
        String from2 = s2.substring(s2.length() - 1 - index, s2.length());
        boolean second = false;
        if (match(target, from2)) {
            second = isScramble(target, from2);
        }
        return first || second;
    }

    // 用来匹配是否为异位词
    private boolean match(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] chars1 = new int[127];
        for (char c : s1.toCharArray()) {
            chars1[c]++;
        }
        int[] chars2 = new int[127];
        for (char c : s2.toCharArray()) {
            chars2[c]++;
        }
        for (int i = 0; i < 127; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }
        return true;
    }
}
