package tencent.exam_main.leetcodeD156;

/**
 * @author inta
 * @date 2019/9/29
 * @describe 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），
 * 也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 */
public class Q5207equalSubstring {
    public int equalSubstring(String s, String t, int maxCost) {
        int[] mark = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            mark[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int res = 0;
        int left = 0;
        int sum = maxCost;
        int temp = 0;
        while (left < mark.length) {
            sum = maxCost;
            temp = 0;
            for (int i = left; i < mark.length; i++) {
                sum -= mark[i];
                if (sum < 0) {
                    res = Math.max(res, temp);
                    break;
                } else {
                    temp++;
                }
            }
            left++;
            res = Math.max(res, temp);
        }
        return res;
    }

    public static void main(String[] args) {
        Q5207equalSubstring q = new Q5207equalSubstring();
        String s = "krpgjbjjznpzdfy";
        String t = "nxargkbydxmsgby";
        int maxCost = 14;
        System.out.println(q.equalSubstring(s,t,maxCost));
    }

}
