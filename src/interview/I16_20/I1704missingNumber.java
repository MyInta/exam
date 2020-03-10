package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/10
 * @describe 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 *
 * 注意：本题相对书上原题稍作改动
 *
 * 示例 1：
 *
 * 输入：[3,0,1]
 * 输出：2
 *  
 *
 * 示例 2：
 *
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 *
 */
public class I1704missingNumber {
    public int missingNumber(int[] nums) {
        int[] counts = new int[nums.length + 1];
        for (int num : nums) {
            counts[num] ++;
        }
        for (int i = 0; i < counts.length; i ++) {
            if (counts[i] == 0) return i;
        }
        return -1;
    }

    //用数学解更优，这里不赘述了，就是求和公式-nums和为缺失
}
