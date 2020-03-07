package offer.V51_100;

/**
 * @author inta
 * @date 2020/3/5
 * @describe 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 *  
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *  
 *
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 */
public class V66constructArr {
    //既然所有乘积和不会溢出，那直接乘即可
    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n < 1) return new int[0];
        if (n == 1) return a;
        int[] res = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = a[0];
        for (int i = 1; i < n; i ++) {
            left[i] = left[i - 1] * a[i];
        }
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i --) {
            right[i] = right[i + 1] * a[i];
        }
        res[0] = right[1];
        res[n - 1] = left[n - 2];
        for (int i = 1; i < n - 1; i ++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        return res;
    }
}
