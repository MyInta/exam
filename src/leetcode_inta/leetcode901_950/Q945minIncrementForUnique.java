package leetcode_inta.leetcode901_950;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/3/22
 * @describe 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 *
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 *
 * 示例 1:
 *
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 *
 * 示例 2:
 *
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 *
 * 提示：
 *
 *     0 <= A.length <= 40000
 *     0 <= A[i] < 40000
 *
 */
public class Q945minIncrementForUnique {
    public int minIncrementForUnique(int[] A) {
        int[] counts = new int[80000];
        int res = 0;
        for (int a : A) {
            counts[a] ++;
        }
        for (int i = 0; i < counts.length; i++) {
            //从左到右，遇到数量大于1的，说明是需要被修改的元素
            if (counts[i] > 1) {
                //我们要修改至元素数量为1才行
                while (counts[i] > 1) {
                    //找该元素后面的数，因为修改操作是递增的
                    for (int j = i + 1; j < counts.length; j++) {
                        //找到空位就填充以及累加修改操作次数，并跳出这一次的查找，去判断i位置元素数量可否修为1
                        if (counts[j] == 0) {
                            counts[j] ++;
                            res += j - i;
                            break;
                        }
                    }
                    counts[i] --;
                }
            }
        }
        return res;
    }

    //看了题解后，脑洞大开
    public int minIncrementForUnique2(int[] A) {
        if (A.length == 0) return 0;
        Arrays.sort(A);
        int res = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                res += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return res;
    }
}
