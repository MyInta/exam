package offer.V51_100;

/**
 * @author inta
 * @date 2020/3/3
 * @describe 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *  
 *
 * 限制：
 *
 * 2 <= nums <= 10000
 *
 */
public class V56_2singleNumbers {
    //这是一道位运算很快能解出的题,目标是找到这个偶数数组中，
    // 按照那两个不同数第一个不相同的位(出现的第一个1)为标志，分成两组进行或运算
    public int[] singleNumbers(int[] nums) {
        int[] res = new int[2];
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        //求这两个数第一个不相同的位置，即位或为1的位置,
        // x&(-x)求的就是原有x位中最小1出现地方，并且结果是一个1剩余0数量和原有x末尾位0数量一致
        int mark = sum & (-sum);
        for (int num : nums) {
            //现在就可以分成两组运算了,那个前哪个后不重要了
            if ((mark & num) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
