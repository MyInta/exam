package tencent.leetcode1_50;

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
         /*   for (int i = 0; i < pLen.length; i++) {
                if (pLen[i] == max+1) {
                    if (max%2 == 0){
                        int start = i/2 - (max - 1)/2 - 1;
                        int end = i/2 + (max - 1)/2 + 1;
                        res = res.substring(start, end);
                        // System.out.println(start + "-" + end + "偶数:" + res);
                    } else {
                        int start = i/2 - (max - 1)/2;
                        int end = i/2 + (max - 1)/2 + 1;
                        res = res.substring(start, end);
                        // System.out.println(start + "-" + end + "奇数:" + res);
                    }
                    break;
                }
            }*/
    public static void main(String[] args) {
        Q5LongestPalindrome q5LongestPalindrome = new Q5LongestPalindrome();
        String str = "babadccdabacc";
        q5LongestPalindrome.longestPalindrome(str);
    }
}
