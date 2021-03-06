package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/14
 * @describe URL化。编写一种方法，将字符串中的空格全部替换为%20。
 * 假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 *
 * 示例1:
 *
 *  输入："Mr John Smith    ", 13
 *  输出："Mr%20John%20Smith"
 * 示例2:
 *
 *  输入："               ", 5
 *  输出："%20%20%20%20%20"
 * 提示：
 *
 * 字符串长度在[0, 500000]范围内。
 *
 */
public class I0103replaceSpaces {
    public String replaceSpaces(String S, int length) {
        char[] sChars = S.toCharArray();
        int countNull = 0;
        for (int i = 0; i < length; i ++) {
            if (sChars[i] == ' ') countNull ++;
        }
        //j是真实字符串长度
        int j = length + 2 * countNull;
        char[] chars = new char[j];
        for (int i = length - 1; i >= 0; i --) {
            if (sChars[i] != ' ') {
                chars[-- j] = sChars[i];
            } else {
                chars[-- j] = '0';
                chars[-- j] = '2';
                chars[-- j] = '%';
            }
        }
        return String.valueOf(chars);
    }
}
