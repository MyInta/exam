package leetcode_inta.leetcode;

/**
 * @author inta
 * @date 2019/7/14
 * @describe 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *
 */
public class KthSmallest {

    public int kthSmallest(int[][] matrix,int k){

        int n = matrix[0].length;
        //由题意知最后一个数最大
        int R = matrix[n-1][n-1];
        int L = matrix[0][0];
        int ans = 0;
        while(L<=R){
            int mid = (int)(((long)L+R)>>1);
            if(guess(matrix,mid,k)){//true表示传入的mid比它小的数数量比k小，即mid可能偏小
                L = mid + 1;
                //ans放上面是考虑重复数值情况下，guess出来的值有可能比k小，但其实已经是正确解了
                ans = mid;
            }else{
                R = mid-1;
            }
        }
        return ans;
    }
    //二分法模板
    public boolean guess(int[][] matrix,int mid,int k){
        int n = matrix[0].length;
        //记录比mid小的数有多少
        int sum =0;
        for(int i=0;i<n;i++){
            int ans = -1;
            int left =0,right = n-1;
            while(left<=right){
                int gMid = (left+right)>>1;
                //每一行进行二分查找，与设定的mid值大小比较判断
                if(matrix[i][gMid]<mid){
                    ans = gMid;
                    left = gMid +1;
                }else{
                    right = gMid -1;
                }
            }
            sum +=(ans+1);

        }
        return k>sum;
    }

}
