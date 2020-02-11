package tencent.leetcode1001_1050;

/**
 * @author inta
 * @date 2020/2/11
 * @describe 给定一个整数数组 A，只有我们可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 *
 * 形式上，如果我们可以找出索引 i+1 < j 
 * 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 
 * 就可以将数组三等分。
 *
 *  
 *
 * 示例 1：
 *
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 *
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 *
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *  
 *
 * 提示：
 *
 * 3 <= A.length <= 50000
 * -10000 <= A[i] <= 10000
 *
 */
public class Q1013canThreePartsEqualSum {

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        //连3都不能整除，你拿什么说爱我
        if (sum % 3 != 0) return false;
        int segment = sum / 3;
        //记录第一个找到要求值得片段
        int one = 0;
        int cur;
        int count = 0;
        for (cur = 0; cur < A.length; cur ++) {
            count += A[cur];
            if (count == segment) {
                count = 0;
                one ++;
                if (one == 2) return true;
            }
        }
        return false;
    }

    public boolean canThreePartsEqualSum2(int[] A) {
        //那考虑空间换速度？效率还是一般般
        int[] counts = new int[A.length];
        counts[0] = A[0];
        for (int i = 1; i < A.length; i ++) {
            counts[i] = counts[i - 1] + A[i];
        }
        if (counts[A.length - 1] % 3 != 0) return false;
        int part = counts[A.length - 1] / 3;
        for (int i = 0; i < A.length; i ++) {
            //找到等于1/3的区间，就深入遍历，看看有没有和为2倍part的，有就直接返回答案，没有继续
            if (counts[i] == part) {
                int part2 = 2 * part;
                for (int j = i + 1; j < A.length; j ++) {
                    if (counts[j] == part2) return true;
                }
            }
        }
        return false;
    }
}
