package leetcode_inta.leetcode951_1000;

import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/7
 * @describe 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。
 * 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 *
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 *
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 *
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
public class Q989addToArrayForm {
    // 直接加，需要考虑两种特殊情况A不够用或K不够用
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new LinkedList<>();
        int high = 0;

        // 用来记录遍历A数组的索引位置
        int i = A.length - 1;
        while (K > 0) {
            // 考虑A是否到头了
            int sum = i >= 0 ? high + A[i] + (K % 10) : high + (K % 10);
            // 升位
            high = sum / 10;
            // 在首位添加元素
            res.add(0, sum % 10);
            // K缩减
            K /= 10;
            // A索引缩减
            i--;
        }

        // 跳出循环说明K到头了，要考虑A有没有到头，以及high是否为0
        for (int j = i; j >= 0; j--) {
            int sum = high + A[j];
            high = sum / 10;
            res.add(0, sum % 10);
        }
        if (high != 0) res.add(0, high);
        return res;
    }

    // 评论区的大神，在我考虑使用一个变量记录高位的时候，他直接利用K来取代高位计算
    public List<Integer> addToArrayForm2(int[] A, int K) {
        int cur = K;
        List<Integer> res = new LinkedList<>();
        int i = A.length - 1;
        while (i >= 0 || cur > 0) {
            if (i >= 0) {
                cur += A[i];
            }
            res.add(0, cur % 10);
            cur /= 10;
            i--;
        }
        return res;
    }

    // 时隔一年后来做，思路顺畅许多
    public List<Integer> addToArrayForm3(int[] A, int K) {
        List<Integer> res = new LinkedList<>();
        // more为进位时候的值，如8+9=17时候，more记录1
        int more = 0;

        // 从字符串最后往前遍历
        for (int i = A.length - 1; i >= 0; i--) {
            int sum = A[i] + (K % 10) + more;
            res.add(0, sum % 10); // 这里可以不在首位添加，但最后返回答案记得reverse反转
            more = sum / 10;
            K /= 10;
        }

        // 考虑A数组遍历完后，K仍有余
        while (K != 0) {
            int sum = more + (K % 10);
            res.add(0, sum % 10);
            more = sum / 10;
            K /= 10;
        }

        // 极端情况，A和K都遍历好了，但是还有进位值more，这时候要往前新开辟一个位置,如999+1=1000
        if (more != 0) {
            res.add(0, more);
        }
        return res;
    }
}
