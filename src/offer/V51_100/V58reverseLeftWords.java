package offer.V51_100;

/**
 * @author inta
 * @date 2020/2/17
 * @describe 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 *
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *  
 *
 * 限制：
 *
 * 1 <= k < s.length <= 10000
 *
 */
public class V58reverseLeftWords {
    //最简单的方式就是字符串拼接
    public String reverseLeftWords(String s, int n) {
        //题意已经说明n(k)范围是在s长度内，也就不取余了
        return s.substring(n) + s.substring(0, n);
    }

    //上面效率是挺高，但如果不适用api，可否自己实现呢，如果不是字符串，是树、结点链表呢？
    public String reverseLeftWords2(String s, int n) {
        //解决这类问题，通用方式是使用魔法战胜魔法，三次反转实现
        //先整体反转，再反转要求的长度区间，最后再整体反转
        char[] chars = s.toCharArray();
//        reverseRang(chars, 0, chars.length - 1);
//        reverseRang(chars, chars.length - n, chars.length - 1);
//        reverseRang(chars, 0, chars.length - n - 1);
        //前面反转次数调换下，使语言更简练
        reverseRang(chars, 0, n - 1);
        reverseRang(chars, n, chars.length - 1);
        reverseRang(chars, 0, chars.length - 1);
        return String.valueOf(chars);
    }
    private void reverseRang(char[] chars, int start, int end) {
        while (start < end) {
            char c = chars[start];
            chars[start] = chars[end];
            chars[end] = c;
            start ++;
            end --;
        }
    }
}
