package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/9/9
 * @describe 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Q5LongestPalindrome {
    public String longestPalindrome(String s) {
        String res = s;
        int len = s.length();
        if (len == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append('#');
        char[] chars = sb.toString().toCharArray();
        int[] pLen = new int[(len<<1)+1];
        for (int i = 0;i < pLen.length; i++) {
            pLen[i]++;
            for (int r = 1; i - r >=0 && i + r < pLen.length; r++) {
                if (chars[i - r] == chars[i + r]) {
                    pLen[i]++;
                } else {
                    //如果两端匹配不上，就跳出左右寻找的循环
                    break;
                }
            }
            //进入下一个索引重复定义pLen数值
        }
        int max = 0;
        for (int i : pLen) {
            max = Math.max(max,i);
        }
        max--;
        for (int i = 0; i < pLen.length; i++) {
            if (pLen[i] == max+1) {
                int start = 0;
                int end = 1;
                if (max%2 == 0){
                    start = (i>>1) - (max>>1);
                    end = (i>>1) + (max>>1);
                } else {
                    start = (i>>1) - (max>>1);
                    end = (i>>1) + ((max + 1)>>1);
                }
                res = res.substring(start, end);
                break;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Q5LongestPalindrome q5LongestPalindrome = new Q5LongestPalindrome();
        String str = "babadccdabacc";
        q5LongestPalindrome.longestPalindrome(str);
    }

    //只想到了最简单的实现，遍历每个元素，作为中心，或者比较右边相同则作为两中心，往左往右拓展找最长回文串
    public String longestPalindrome1(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String one = getPalindrome(s, i, i);
            if (one.length() > res.length()) res = one;
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                one = getPalindrome(s, i - 1, i);
                if (one.length() > res.length()) res = one;
            }
        }
        return res;
    }
    private String getPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    //dp做
    public String longestPalindrome2(String s) {
        if (s.length() < 2) return s;
        int len = s.length();
        //回文串可以被取出来的坐标
        int x = 0;
        int distance = 1;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i ++) {
            //默认的单个字符为回文串
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j ++) {
            for (int i = 0; i < j; i ++) {
                //在前半段，考虑边缘值是否相等
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    //如果目前为止可行，那么就考虑下回文长度
                    if (j - i + 1 > distance) {
                        distance = j - i + 1;
                        x = i;
                    }
                }
            }
        }
        return s.substring(x, x + distance);
    }
}
