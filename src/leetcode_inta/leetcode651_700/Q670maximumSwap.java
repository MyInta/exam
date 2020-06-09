package leetcode_inta.leetcode651_700;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author inta
 * @date 2020/6/9
 * @describe 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 注意:
 *
 *     给定数字的范围是 [0, 108]
 *
 */
public class Q670maximumSwap {
    //暴力思路，从尾部往前遍历，遇到比首位大的就交换，再次遇到，先换回来，再交换
    //没有交换，就遍历下一位
    public int maximumSwap(int num) {
        int cur = 0;
        boolean changed = false;
        String str = String.valueOf(num);
        char[] num_chars = str.toCharArray();
        int index = 0;
        while (!changed && cur < str.length()) {
            index = cur;
            for (int i = str.length() - 1; i > cur; i--) {
                if (num_chars[i] > num_chars[cur] && num_chars[index] < num_chars[i]) {
                    index = i;
                    changed = true;
                }
            }
            cur ++;
        }
        //没有交换需求，就是原来的模样
        if (!changed) return num;
        //此时获得了index的值，也就是理应交换的位置
        char temp = num_chars[cur - 1];
        num_chars[cur - 1] = num_chars[index];
        num_chars[index] = temp;
        return Integer.valueOf(String.valueOf(num_chars));
    }

    //题解中有个老哥的思想特别优秀，简约
    //倒序使用数组存储下来每个位置，在它及它以后的最大数的索引
    //然后再正序从一个数开始遍历，如果它及它以后的最大数不是它本身，那么这个数就是我们需要交换的。

}
