package leetcode_inta.leetcode501_550;

/**
 * @author inta
 * @date 2020/1/25
 * @describe 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
 *
 * 示例:
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 要求:
 *
 * 该字符串只包含小写的英文字母。
 * 给定字符串的长度和 k 在[1, 10000]范围内。
 *
 */
public class Q541reverseStr {
    //指针直接遍历即可,又费劲，逻辑虽然简单但是位置关系复杂难以锁定，最终处理速度也一般
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();
        char[] sChars = s.toCharArray();
        int len = sChars.length;
        for (int i = 0; i < len; i ++) {
            //属于2k中的第几个索引
            int index = i % (2 * k);
            if (index < k) {
                if (i + k - 1 - index < len) {
                    sb.append(sChars[i + k - 1 - 2 * index]);
                } else {
                    sb.append(sChars[len - index - 1]);
                }
            } else {
                sb.append(sChars[i]);
            }
        }
        return sb.toString();
    }

    //官解暴力算法
    public String reverseStr2(String s, int k) {
        char[] sChars = s.toCharArray();
        for (int i = 0; i < sChars.length; i += 2 * k) {
            //需要考虑是否超过数组长度,每次都是在[i,i+k-1]或者[i,len-1]区间内交换
            int start = i, end = Math.min(i + k - 1, sChars.length - 1);
            //这个区间内元素都交换位置
            while (start < end) {
                char temp = sChars[start];
                sChars[start ++] = sChars[end];
                sChars[end --] = temp;
            }
        }
        return new String(sChars);
    }
}
