package tencent.leetcode401_450;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author inta
 * @date 2019/11/18
 * @describe 给定一组字符，使用原地算法将其压缩。
 *
 * 压缩后的长度必须始终小于或等于原数组长度。
 *
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 *
 * 在完成原地修改输入数组后，返回数组的新长度。
 *
 *  
 *
 * 进阶：
 * 你能否仅使用O(1) 空间解决问题？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["a","a","b","b","c","c","c"]
 *
 * 输出：
 * 返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]
 *
 * 说明：
 * "aa"被"a2"替代。"bb"被"b2"替代。"ccc"被"c3"替代。
 * 示例 2：
 *
 * 输入：
 * ["a"]
 *
 * 输出：
 * 返回1，输入数组的前1个字符应该是：["a"]
 *
 * 说明：
 * 没有任何字符串被替代。
 * 示例 3：
 *
 * 输入：
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *
 * 输出：
 * 返回4，输入数组的前4个字符应该是：["a","b","1","2"]。
 *
 * 说明：
 * 由于字符"a"不重复，所以不会被压缩。"bbbbbbbbbbbb"被“b12”替代。
 * 注意每个数字在数组中都有它自己的位置。
 * 注意：
 *
 * 所有字符都有一个ASCII值在[35, 126]区间内。
 * 1 <= len(chars) <= 1000。
 *
 */
public class Q443compress {
    public int compress(char[] chars) {
        int cur = 0;
        int index = 0;
        while (cur < chars.length && index < chars.length) {
            chars[cur ++] = chars[index];
            int temp = index;
            while (index < chars.length && chars[index] == chars[cur - 1]) {
                index ++;
            }
            //当char遍历的指针与初始元素的距离大于1，即说明有重复字符
            if (index - temp > 1) {
                for (char c : String.valueOf(index - temp).toCharArray()) {
                    chars[cur ++] = c;
                }
            }
        }
        return cur;
    }

    //使用新的指针来指引位置
    public int compress2(char[] chars) {
        //旧数组索引
        int old_i = 0;
        //新数组索引
        int new_i = 0;
        //当前所在位置
        int cur = 0;
        while (old_i < chars.length) {
            //当索引在数组最后一位或者遇到不同元素的时候，都需要添加到新数组的操作
            if (old_i == chars.length - 1 || chars[old_i] != chars[old_i + 1]) {
                //新数组元素+1，并且附上当前索引位置的值
                chars[new_i ++] = chars[cur];
                //如果遍历下来的旧数组元素索引比当前元素索引大，说明存在重复数组，我们添加数字
                if (old_i > cur) {
                    for (char c : String.valueOf(old_i - cur + 1).toCharArray()) {
                        chars[new_i ++] = c;
                    }
                }
                //如果上述情况都处理好后，当前索引永远是记录的就数组索引后一位
                cur = old_i + 1;
            }
            old_i ++;
        }
        //返回新数组长度
        return new_i;
    }
}
