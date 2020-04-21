package interview.I6_10;

/**
 * @author inta
 * @date 2020/4/20
 * @describe 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
 * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 *
 * 示例2:
 *
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 *
 * 提示:
 *
 *     arr 长度范围在[1, 1000000]之间
 *
 */
public class I1003search {
    public int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    //二分
    public int search2(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //如果mid小，说明左半段存在塌陷,右半段是升序的
            if (arr[mid] < arr[left]) {
                //如果左边开头就大于目标，
                if (arr[left] > target) {
                    //如果mid小于目标，那么目标在右半段
                    if (arr[mid] < target) {
                        left = mid + 1;
                    } else {
                        //否则就在左半段塌陷空间里
                        right = mid;
                    }
                } else {
                    //因为前提是mid比left小，如果left都比目标小，那么目标肯定不在右半段，因为右半段是升序，且最大值不大于left
                    right = mid;
                }
            } else if (arr[mid] > arr[left]) {
                //否则左半段必升序,如果left比目标大，肯定不在左半段
                if (arr[left] > target) {
                    left = mid + 1;
                } else {
                    //否则，考虑左半段可能会有，右半段塌陷处也可能有,如果mid小于目标，那么只能在右半段
                    if (arr[mid] < target) {
                        left = mid + 1;
                    } else {
                        //否则，因为前提是left小于等于目标
                        right = mid;
                    }
                }
            } else {
                if (arr[left] == target) {
                    return left;
                } else {
                    left ++;
                }
            }
        }
        if (left == arr.length) return -1;
        return arr[left] == target ? left : -1;
    }
}
