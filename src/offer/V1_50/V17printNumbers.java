package offer.V1_50;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/17
 * @describe 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 */
public class V17printNumbers {
    public int[] printNumbers(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < (int)Math.pow(10, n); i ++) {
            res.add(i);
        }
//        int[] r = new int[res.size()];
//        for (int i = 0; i < r.length; i ++) {
//            r[i] = res.get(i);
//        }
//        return r;
        return res.stream().mapToInt(i->i).toArray();
    }

    public int[] printNumbers2(int n) {
        int temp = 1;
        while (n > 0) {
            temp *= 10;
            n --;
        }
        int[] res = new int[temp - 1];
        for (int i = 1; i < temp; i ++) {
            res[i - 1] = i;
        }
        return res;
    }
}
