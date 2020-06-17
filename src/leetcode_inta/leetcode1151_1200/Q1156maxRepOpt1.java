package leetcode_inta.leetcode1151_1200;

/**
 * @author inta
 * @date 2020/6/17
 * @describe 如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。
 *
 * 给你一个字符串 text，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：text = "ababa"
 * 输出：3
 *
 * 示例 2：
 *
 * 输入：text = "aaabaaa"
 * 输出：6
 *
 * 示例 3：
 *
 * 输入：text = "aaabbaaa"
 * 输出：4
 *
 * 示例 4：
 *
 * 输入：text = "aaaaa"
 * 输出：5
 *
 * 示例 5：
 *
 * 输入：text = "abcdef"
 * 输出：1
 *
 *
 *
 * 提示：
 *
 *     1 <= text.length <= 20000
 *     text 仅由小写英文字母组成。
 *
 */
public class Q1156maxRepOpt1 {
    //看了大佬的题解，才有的思路，就是比较的时候，考虑是否是第二次遇到的不一样，遇到就进行下一个比较
    public int maxRepOpt1(String text) {
        int res = 0;
        int[] counts = new int[26];
        //先统计整个列表中每个单词总数
        for (char t : text.toCharArray()) counts[t - 'a'] ++;
        //遍历字符串
        for (int i = 0; i < text.length(); i++) {
            //当前单词的数量
            int sum = 1;
            //记录下遍历到哪个索引位置了
            int mark = i;
            //标识是否是第一次遇到的不同
            boolean flag = true;
            //当前单词
            char cur = text.charAt(i);
            while (i < text.length() - 1 && (flag || cur == text.charAt(i + 1))) {
                if (text.charAt(i + 1) != cur) {
                    //遇到不同，就修改第一次遇到情况不同
                    flag = false;
                    //标识遇到第一次不同的单词索引前位置
                    mark = i;
                }
                i ++;
                //累加相同情况的单词数量
                sum ++;
            }
            //我们遍历下一个不同的单词,此时mark是第一段相同单词最后一个元素，循环会i++，不需要提早加了
            i = mark;
            //因为循环里面我们假设后一个不同的单词被修改后成为相同单词，所以需要考虑下是否符合数组
            sum = Math.min(sum, counts[cur - 'a']);
            res = Math.max(sum , res);
        }
        return res;
    }
}
