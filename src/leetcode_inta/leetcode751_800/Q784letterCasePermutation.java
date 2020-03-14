package leetcode_inta.leetcode751_800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/28
 * @describe 给定一个字符串S，通过将字符串S中的每个字母转变大小写，
 * 我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 *
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 *
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class Q784letterCasePermutation {
    //dfs
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] s_chars = S.toCharArray();
        change(0, s_chars, res);
        return res;
    }
    private void change(int start, char[] chars, List<String> res) {
        //添加整个数组到res中
        res.add(String.valueOf(chars));
        //遍历整个数组
        for (int i = start; i < chars.length; i ++) {
            //只有在为字母时需要改变大小写
            if (chars[i] > '9') {
                //反转大小写
                reverse(chars, i);
                //每次改变后，结果集需要保存新数组
                change(i + 1, chars, res);
                //以及回溯复原
                reverse(chars, i);
            }
        }
    }
    //char数组莫元素的大小字母反转
    private void reverse(char[] chars, int index) {
        if (chars[index] > 'Z') {
            //当为小写时候，改成大写
            chars[index] = (char)('A' + (chars[index] - 'a'));
        } else {
            //题意说只有数字和字母，所有else为大写，改成小写
            chars[index] = (char)('a' + (chars[index] - 'A'));
        }
    }

    //听说递归很简单，试试,效率低
    public List<String> letterCasePermutation2(String S) {
        List<String> res = new ArrayList<>();
        List<StringBuilder> res_temp = new ArrayList<>();
        char[] s_chars = S.toCharArray();
        res_temp.add(new StringBuilder());
        for (char c : s_chars) {
            int size = res_temp.size();
            if (c <= '9') {
                //如果是数字，每个res中保存的sb都加一个
                for (int i = 0; i < size; i ++) {
                    res_temp.get(i).append(c);
                }
            } else if (c > 'Z') {
                //当为小写时,结果集中新添加含改变大小写后的元素
                for (int i = 0; i < size; i ++) {
                    res_temp.add(new StringBuilder(res_temp.get(i)));
                    //一个添加小写，一个添加大写
                    res_temp.get(i).append(c);
                    res_temp.get(i + size).append(Character.toUpperCase(c));
                }
            } else {
                //夹在中间为大写
                for (int i = 0; i < size; i ++) {
                    res_temp.add(new StringBuilder(res_temp.get(i)));
                    //一个添加小写，一个添加大写
                    res_temp.get(i).append(c);
                    res_temp.get(i + size).append(Character.toLowerCase(c));
                }
            }
        }
        for (StringBuilder sb : res_temp) {
            res.add(sb.toString());
        }
        return res;
    }
}
