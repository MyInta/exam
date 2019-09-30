package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/9/30
 * @describe 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 */
public class Q9isPalindrome {
    //转化为字符串处理
    public boolean isPalindrome(int x) {
        String str = x+"";
        int len = str.length();
        int end = len >> 1;
        for (int i = 0; i < end; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    //不转化为字符串就进行处理，并且只比较一半的值即可
    public boolean isPalindrome2(int x) {
        //负数存在的-号必定非回文,并且除零外，所有零结尾数字都非回文
        if (x < 0 || x != 0 && x % 10 == 0) {
            return false;
        }
        int res = 0;
        while (x > res) {
            res = x % 10 + res*10;
            x = x / 10;
        }
        //还需要考虑到可能位数是奇数的情况，res去掉个位数与x相等
        return x == res || x == res/10;
    }

}
