package leetcode_inta.leetcode1051_1100;

/**
 * @author inta
 * @date 2020/3/12
 * @describe 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 *  
 *
 * 提示：
 *
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 *
 */
public class Q1071gcdOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        if (len1 > len2 && !str1.startsWith(str2) || len1 <= len2 && !str2.startsWith(str1)) return "";
        int max = gcd(len1, len2);
//        System.out.println(max);
        for (int i = max; i > 0; i --) {
            String T = str1.substring(0, i);
            if (containT(str1, T) && containT(str2, T)) return T;
        }
        return "";
    }
    private int gcd(int len1, int len2) {
        if (len1 == 0) return len2;
        return gcd(len2 % len1, len1);
    }
    private boolean containT(String str, String T) {
        int len = T.length(), cur = 0;
        while (cur < str.length() - len + 1) {
            if (!str.startsWith(T, cur)) return false;
            cur += len;
        }
        return true;
    }


    //网上大佬的解答思路 str1为n个T str2为m个T 那么str1+str2一定等于str2+str1才行 有n+m个T
    public String gcdOfStrings2(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }
}
