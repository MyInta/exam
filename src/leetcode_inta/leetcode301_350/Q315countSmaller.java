package leetcode_inta.leetcode301_350;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/8/23
 * @describe 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 */
public class Q315countSmaller {
    //耗时过长
//    public List<Integer> countSmaller(int[] nums) {
//        List<Integer> list = new ArrayList<>();
//        for(int i=0;i<nums.length;i++){
//            int temp = 0;
//            for(int j= i + 1;j<nums.length;j++){
//                if(nums[j]<nums[i]){
//                    temp++;
//                }
//            }
//            list.add(temp);
//        }
//        return list;
//    }

    private int[] count;
    public List<Integer> countSmaller(int[] nums) {
        //结果集
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        //用于记录右边小于当前元素个数
        count = new int[len];
        int[] indexes = new int[len];
        //填充数组序列
        for (int i = 0; i < len; i ++) {
            indexes[i] = i;
        }
        int left = 0;
        int right = len - 1;
        merge(nums, indexes, left, right);
        for (int ct : count) {
            res.add(ct);
        }
        return res;
    }
    //用于二分，划分开数组，然后调用方法实现计数
    private void merge(int[] nums, int[] indexes, int left, int right) {
        //当分割到小于2个元素时无比较，直接返回
        if (left >= right) {
            return;
        }
        int mid = ((right - left) >> 1) + left;
        //分割为前后两半log(n)的由来
        merge(nums, indexes, left, mid);
        merge(nums, indexes, mid + 1, right);
        //只要多于两个元素，就可以开始比较并计数,优化：当已排好序的数组前段小于等于右端，就没有计数必要了
        if (nums[indexes[mid]] > nums[indexes[mid + 1]]) {
            mergeAndCount(nums, indexes, left, right);
        }
    }
    //合并数组与计数功能
    private void mergeAndCount(int[] nums, int[] indexes, int left, int right) {
        //创建一个新数组，用于临时存储排序后的序列位置，最后给回到索引indexes去
        int[] cache = new int[right - left + 1];
        //以及对应新数组的索引
        int cIndex = 0;
        int mid = ((right - left) >> 1) + left;
        //创建两个指针，分别在前后段移动比较
        int p1 = left;
        int p2 = mid + 1;
        //再来个记录右边比较后符合逆数的数量
        int rightCount = 0;
        //当指针各自没有移出自己所在区域时
        while (p1 <= mid && p2 <= right) {
            //如果发现逆数
            if (nums[indexes[p1]] > nums[indexes[p2]]) {
                //记录逆数+1
                rightCount ++;
                //把索引排序（相当于数组排序了）
                cache[cIndex] = indexes[p2];
                //然后后侧指针继续移动，继续找逆数
                p2 ++;
                //新数组索引也往后移
                cIndex ++;
            } else {
                //否则，说明移动到了没有逆数的位置，需要把一直以来记录的数量上交count
                count[indexes[p1]] += rightCount;
                cache[cIndex] = indexes[p1];
                //并且开始移动前侧的指针，换一个新元素找其逆数
                p1 ++;
                cIndex ++;
            }
        }
        //当有指针越界，就只能是两种情况，一种右边越界，一种左边越界，指针没有同时移动，不存在第三种情况
        while (p1 <= mid) {
            //右边越界，说明这一整排都会比左指针往后找到的元素要小，直接计数
            count[indexes[p1]] += rightCount;
            cache[cIndex ++] = indexes[p1 ++];
        }
        while (p2 <= right) {
            //左边越界，说明这一路过来都比右指针元素要小，没有逆数
            cache[cIndex ++] = indexes[p2 ++];
        }
        //把排序后的索引缓存内容转移到indexes中
//        for (int i = left; i <= right; i++) {
//            //因为cache是生成在新的一段上，为比较段落的长度，而indexes是全局的，在赋值上需要注意索引位置
//            indexes[i] = cache[i - left];
//        }
        for (int i =  right; i >= left; i--) {
            //因为cache是生成在新的一段上，为比较段落的长度，而indexes是全局的，在赋值上需要注意索引位置
            indexes[i] = cache[--cIndex];
        }
    }
}
