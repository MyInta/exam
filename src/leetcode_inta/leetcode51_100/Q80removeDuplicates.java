package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/12/5
 * @describe 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 *
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 *
 * 为什么返回数值是整数，但输出的答案是数组呢?
 *
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class Q80removeDuplicates {
    //第一反应是双指针，一根是在实际索引位置+1，一根是遍历往后元素位置，结果长度位第一根索引+1
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        if (nums.length == 2) return 2;
        int left = 2;
        int right = 2;
        //用来存储目前遇到的数组元素，遇到辆次重复就跳指针和转换
        int one = nums[0];
        int two = nums[1];
        for (; right < nums.length; right ++) {
            int temp = nums[right];
            while (right < nums.length && one == two && temp == one) {
                right ++;
                if (right < nums.length) {
                    temp = nums[right];
                }
            }
            if (right < nums.length) {
                //更新one&two
                one = two;
                two = nums[right];
                //给left位置赋值right
                nums[left] = two;
                //left可以往后挪动下了
                left ++;
            }
        }
        return left;
    }

    //看了大神的解答之后，才发现差距是明显的！
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            //在前两个元素的时候，不用考虑重复问题，故i<2即赋值，当>=2的时候，
            // 考虑当前索引前2的元素是否和当前元素相等，相等就跳过，不相等，因为排序为小于，赋值
            if (i < 2 || num > nums[i - 2]) {
                nums[i ++] = num;
            }
        }
        return i;
    }
}
