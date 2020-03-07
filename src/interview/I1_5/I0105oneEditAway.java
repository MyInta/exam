package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/7
 * @describe 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
 * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *
 *  
 *
 * 示例 1:
 *
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *  
 *
 * 示例 2:
 *
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 *
 */
public class I0105oneEditAway {
    public boolean oneEditAway(String first, String second) {
        int len1 = first.length();
        int len2 = second.length();
        //位差2位以上，肯定不能一次编辑得到
        if (Math.abs(len1 - len2) > 1) return false;
        if (len1 == len2) {
            int count = 0;
            for (int i = 0; i < len1; i ++) {
                if (first.charAt(i) != second.charAt(i)) count ++;
            }
            return count < 2;
        } else {
            int min = Math.min(len1, len2);
            String minStr = min == len1 ? first : second;
            second = minStr.equals(first) ? second : first;
            //此时minStr为最短，second为最长
            int index = 0;
            for (int i = 0; i < min; i ++) {
                if (index >= second.length()) return false;
                if (minStr.charAt(i) != second.charAt(index ++)) {
                    //遇到不等，i回退
                    i --;
                }
            }
            return true;
        }
    }

}
