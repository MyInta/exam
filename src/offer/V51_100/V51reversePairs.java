package offer.V51_100;

/**
 * @author inta
 * @date 2020/2/13
 * @describe 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *  
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 */
public class V51reversePairs {
    //TLE
    public int reversePairs(int[] nums) {
        int res = 0;
        for (int i = nums.length - 2; i >= 0; i --) {
            int temp = nums[i];
            int count = 0;
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[j] < temp) count ++;
            }
            res += count;
        }
        return res;
    }

    //归并排序过程中进行统计,核心是统计归并过程中，遍历左半段元素，累加右半段中小于左半段元素的数量
    public int reversePairs2(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return res;
    }
    private int res = 0;
    private void merge(int[] nums, int left, int mid, int right) {
        //准备一个拷贝数组，用来比较，然后修改原数组
        int len = right - left + 1;
        int[] copyNums = new int[len];
        System.arraycopy(nums, left, copyNums, 0, len);
        //此时已经拷贝好数组截取的一段，开始融合操作
        //分别记录两段的起始索引位置
        int partOne = left, partTwo = mid + 1;
        for (int i = 0; i < len; i ++) {
            //需要考虑前后两段哪一方先遍历完，剩下的都是另一方的
            if (partOne > mid) {
                //先考虑左段遍历完吧
                nums[left + i] = copyNums[partTwo - left];
                partTwo ++;
            } else if (partTwo > right) {
                //如果右段提前遍历完了，剩下都是左段的
                nums[left + i] = copyNums[partOne - left];
                //注意如果是右边先出完，那么说明剩下的都是左段每个元素，相对于右段全是逆序
                res += right - mid;
                partOne ++;
            } else {
                //否则说明在各自区间内，只要优先保存较小一方即可
                if (copyNums[partOne - left] <= copyNums[partTwo - left]) {
                    //注意这里是因为遇到逆序，说明此刻左段该位置索引需要插入，也说明右段partTwo前面的元素都小于partOne，即逆序
                    res += partTwo - mid - 1;
                    nums[left + i] = copyNums[partOne - left];
                    partOne ++;
                } else {
                    nums[left + i] = copyNums[partTwo - left];
                    partTwo ++;
                }
            }
        }
    }
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = left + ((right - left) >> 1);
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        //如果切分之后的两个片段不是升序的
        if (nums[mid] > nums[mid + 1]) {
            //合并排序操作，如果是升序的，不需要融合，返回上一层就是自然合并了
            merge(nums, left, mid, right);
        }
    }
}
