package tencent.leetcode951_1000;

import java.util.HashSet;

/**
 * @author inta
 * @date 2019/12/19
 * @describe 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 *
 * 返回重复了 N 次的那个元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,3,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：[2,1,2,5,3,2]
 * 输出：2
 * 示例 3：
 *
 * 输入：[5,1,5,2,5,3,5,4]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length 为偶数
 *
 */
public class Q961repeatedNTimes {
    public int repeatedNTimes(int[] A) {
        HashSet<Integer> hs = new HashSet<>();
        hs.add(A[0]);
        for (int i = 1; i < A.length; i ++) {
            if (hs.contains(A[i])) return A[i];
            hs.add(A[i]);
        }
        return -1;
    }

    //大神解答，3个3个找，必定存在重复的解
    public int repeatedNTimes2(int[] A) {
        //已知A长度大于等于4
        int first = A[0];
        int second = A[1];
        for (int i = 2; i < A.length; i ++) {
            int third = A[i];
            if (first == second || first == third) {
                //当三个数里面右重复，就找到解了
                return first;
            } else {
                //不排除可能三个都不重复的情况，这时候往后找，最坏情况是前面N个都不重复
                first = second;
                second = third;
            }
        }
        //都找不到，可能是出现a1b1c1的情况
        return second;
    }
}
