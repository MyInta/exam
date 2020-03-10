package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/9
 * @describe 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *
 * 回文串不一定是字典当中的单词。
 *
 *  
 *
 * 示例1：
 *
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 *
 */
public class I0104canPermutePalindrome {
    //就是里面独立元素不能超过1个即为true，反之false
    public boolean canPermutePalindrome(String s) {
        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            counts[c] ++;
        }
        int flag = 0;
        for (int count : counts) {
            if ((count & 1) == 1) flag ++;
        }
        return flag < 2;
    }
}
