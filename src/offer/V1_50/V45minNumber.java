package offer.V1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/29
 * @describe 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *  
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 */
public class V45minNumber {
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i ++) {
            strs[i] = nums[i] + "";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(0) == o2.charAt(0)) {
                    //比较两个字符首位到末尾，如果缺失，就假定该位置为字符末尾元素值
                    char[] o1_chars = o1.toCharArray();
                    char[] o2_chars = o2.toCharArray();
                    int len1 = o1_chars.length;
                    int len2 = o2_chars.length;
                    //比较范围为字符串中最大距离
                    int max = Math.max(len1, len2);
                    char o1c = o1_chars[0],o2c = o2_chars[0];
                    for (int i = 1; i < max; i ++) {
                        //如果越界，就跳出循环
                        if (i >= len1 - 1 || i >= len2 - 1) break;
                        o1c = o1_chars[i];
                        o2c = o2_chars[i];
                        //不相等情况就跳出循环
                        if (o1c != o2c) return o1c - o2c;
                    }
                    //到这里，说明一方是另一方的前缀
                    return (o1 + o2).compareTo(o2 + o1);
                }
                //否则返回头比较即可
                return o1.charAt(0) - o2.charAt(0);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : strs) sb.append(str);
        return sb.toString();
    }


    //看了别人的解法后，一言难尽，我写的好啰嗦啊
    public String minNumber2(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            //编码器自动做了优化
            list.add(num + "");
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }

    //优化解法，用的是快排，有空补上

}
