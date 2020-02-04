package tencent.leetcode1251_1300;

/**
 * @author inta
 * @date 2020/2/1
 * @describe 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，
 * 它的出现次数超过数组元素总数的 25%。
 *
 * 请你找到并返回这个整数
 *
 *  
 *
 * 示例：
 *
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 *
 */
public class Q1287findSpecialInteger {
    //使用摩尔投票可以实现On复杂度 O1空间,结果发现那种思路做不了，那个是两个候选人超过6成，这里不一样
    public int findSpecialInteger(int[] arr) {
        int n = arr.length / 4;
        int candidate = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i ++) {
            if (arr[i] == candidate) {
                count ++;
            } else {
                candidate = arr[i];
                count = 1;
            }
            if (count > n) {
                break;
            }
        }
        //题意已知必定存在这个数,不需要考虑数量不符合要求
        return candidate;
    }

    //双指针
    public int findSpecialInteger2(int[] arr) {
        int left = 0, right = 0;
        int target = arr.length / 4;
        int temp = arr[left];
        while (right < arr.length) {
            if (arr[right] != temp) {
                if (right - left > target) return temp;
                temp = arr[right];
                left = right;
            }
            right ++;
        }
        //题意说一定有，那跳出循环还没返回，肯定是最后一段里，直接返回即可
        return temp;
    }

    //大神的两种思路都碾压我，一个是利用有序和题意得25%找区间相同，另一个是二分
    public int findSpecialInteger3(int[] arr) {
        int len = arr.length / 4;
        for (int i = 0; i < arr.length - len; i ++) {
            if (arr[i] == arr[i + len]) return arr[i];
        }
        return -1;
    }
    //二分思想是所有区间span = arr.length / 4 + 1内必定会有目标值，于是我们找0,span,span * 2, span * 3 ... ...
    //证明可以反证求出，这几个位置的元素必定包含我们要求的解，然后用二分找每个元素左界限和右界限，长度>=span元素为解
    public int findSpecialInteger4(int[] arr) {
        int span = arr.length / 4 + 1;
        for (int i = 0; i < arr.length; i += span) {
            int l = lower_bound(arr, 0, arr.length, arr[i]);
            int r = upper_bound(arr, 0, arr.length, arr[i]);
            if (r - l >= span) return arr[i];
        }
        return -1;
    }
    //找到key左边界（第一个大于等于key的索引位置）
    private int lower_bound(int[] arr, int l, int r, int key) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    //找到key右边界索引+1位置（第一个大于key的索引位置）
    private int upper_bound(int[] arr, int l, int r, int key) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
