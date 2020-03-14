package leetcode_inta.leetcode1101_1150;

/**
 * @author inta
 * @date 2019/11/27
 * @describe 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *  
 *
 * 提示：
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class Q1122relativeSortArray {
    //先使用蠢办法进行尝试
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        //根据题意得到最长数组单位
        int[] all = new int[1001];
        //遍历所有arr1 将数量全部确认好
        for (int i : arr1) {
            all[i] ++;
        }
        //开始遍历arr2里面的元素，给res添加值
        int index = 0;
        for (int i : arr2) {
            while (all[i] > 0) {
                res[index ++] = i;
                all[i] --;
            }
        }
        //然后再次遍历all数组，这个时候长度大于0的只剩下非arr2的，并且按顺序排列着
        for (int i = 0; i < all.length; i ++) {
            while (all[i] > 0) {
                res[index ++] = i;
                all[i] --;
            }
        }
        return res;
    }
}
