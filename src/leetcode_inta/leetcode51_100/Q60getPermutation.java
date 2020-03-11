package leetcode_inta.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/27
 * @describe 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Q60getPermutation {
    //拷贝的k值，方便直接增减操作
    private int k_temp;
    private int getFactorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i ++) {
            res *= i;
        }
        return res;
    }
    //获取需要集合中的第几个元素开头，假设还剩n个元素,总数量为k个
    private int get_num(int n) {
        //每个元素对应数量最大为(n - 1)!
        int sum = getFactorial(n - 1);
        int num = 1;
        //挨个遍历，数量超过某元素开头数量，就找下一个元素，并缩小总数量k_temp值
        while (k_temp > sum) {
            num ++;
            k_temp -= sum;
        }
        //此时，剩余k_temp值在num号元素为开头位置的支线中
        return num;
    }
    //前面num只是号码牌，我们需要获取其真实的元素值（内部n为集合中的第几个，集合随着元素的删减会动态变化，而次数关系是固定的）
    private int get_realNum(int n, List<Integer> list) {
        int index = n - 1;
        Integer real_num = list.get(index);
        //既然已经获取真实值，用过后面不会再出现，所以list缩减掉,相当于进入下一位
        list.remove(real_num);
        return real_num;
    }
    public String getPermutation(int n, int k) {
        this.k_temp = k;
        StringBuilder sb = new StringBuilder();
        //用来存放1->n的所有元素
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i ++) {
            list.add(i);
        }
        for (int i = n; i >= 1; i --) {
            sb.append(get_realNum(get_num(i), list));
        }
        return sb.toString();
    }
}
