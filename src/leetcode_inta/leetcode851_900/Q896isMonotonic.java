package leetcode_inta.leetcode851_900;

/**
 * @author inta
 * @date 2020/1/21
 * @describe 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 *
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 *
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 *
 * 输入：[1,1,1]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 *
 */
public class Q896isMonotonic {
    public boolean isMonotonic(int[] A) {
        boolean asc = false;
        boolean desc = false;
        for (int i = 0; i < A.length; i ++) {
            if (i > 0 && A[i - 1] > A[i]) {
                desc = true;
            } else if (i > 0 && A[i - 1] < A[i]) {
                asc = true;
            }
        }
        return !asc || !desc;
    }

    //网友提供取巧做法，统计正序与逆序情况用int类型,快不了多少
    public boolean isMonotonic2(int[] A) {
        //a代表正序，d代表逆序
        int a = 0, d = 0;
        for (int i = 0; i < A.length - 1; i ++) {
            if (A[i] < A[i + 1]) {
                a = 1;
            } else if (A[i] > A[i + 1]) {
                d = 1;
            }
        }
        return (a + d) != 2;
    }
    //在上诉方法中修改，在过程中判断,时间更优了
    public boolean isMonotonic3(int[] A) {
        int a = 0, d = 0;
        for (int i = 0; i < A.length - 1; i ++) {
            if (A[i] < A[i + 1]) {
//                if (d != 0) return false;
                a = 1;
            } else if (A[i] > A[i + 1]) {
//                if (a != 0) return false;
                d = 1;
            }
            if (a != 0 && d != 0) return false;
        }
        return true;
    }
}
