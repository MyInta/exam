package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/11
 * @describe 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
 * 你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 * 示例1:
 *
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 * 示例2:
 *
 *  输入："abbccd"
 *  输出："abbccd"
 *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * 提示：
 *
 * 字符串长度在[0, 50000]范围内。
 *
 */
public class I0106compressString {
    public String compressString(String S) {
        if (S == null || S.length() < 3) return S;
        //照着意思写不就出来了
        StringBuilder sb = new StringBuilder();
        char temp = S.charAt(0);
        int count = 0;
        for (char c : S.toCharArray()) {
            if (c == temp) {
                count ++;
            } else {
                sb.append(temp).append(count);
                temp = c;
                count = 1;
            }
        }
        sb.append(temp).append(count);
        String str = sb.toString();
        return str.length() < S.length() ? str : S;
    }
}
