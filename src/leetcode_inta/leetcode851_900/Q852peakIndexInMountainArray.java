package leetcode_inta.leetcode851_900;

/**
 * @author inta
 * @date 2019/11/7
 * @describe 我们把符合下列属性的数组 A 称作山脉：
 *
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[0,1,0]
 * 输出：1
 * 示例 2：
 *
 * 输入：[0,2,1,0]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 *
 */
public class Q852peakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 0; i < A.length - 1; i ++) {
            if (A[i + 1] < A[i]) {
                return i;
            }
        }
        return -1;
    }
    //之前在想，如果是非排序数组，不可以用二分查找，只能用并归，看了题解之后，哎。。。
    public int peakIndexInMountainArray2(int[] A) {
        int left = 0;
        int right = A.length;
        //题意只，必有解，且A的长度大于3，不用考虑mid数组越界
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) {
                return mid;
            } else if (A[mid] > A[mid + 1] && A[mid] < A[mid - 1]) {
                right = mid;
            } else if (A[mid] < A[mid + 1] && A[mid] > A[mid - 1]) {
                left = mid + 1;
            }
        }
        return left;
    }
}
