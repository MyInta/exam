package tencent.leetcode;

/**
 * @author inta
 * @date 2019/10/3
 * @describe 获取数组序列排序后的索引数组
 * 如    数组      3241
 * 返回   索引数组  3102
 */
public class GainSortedIndex {
    //合并数组与计数功能
    private void merge(int[] nums, int[] indexes, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = ((right - left) >> 1) + left;
        //分割为前后两半log(n)的由来
        merge(nums, indexes, left, mid);
        merge(nums, indexes, mid + 1, right);
        //创建一个新数组，用于临时存储排序后的序列位置，最后给回到索引indexes去
        int[] cache = new int[right - left + 1];
        //以及对应新数组的索引
        int cIndex = 0;
        //创建两个指针，分别在前后段移动比较
        int p1 = left;
        int p2 = mid + 1;
        //再来个记录右边比较后符合逆数的数量
        int rightCount = 0;
        //当指针各自没有移出自己所在区域时
        while (p1 <= mid && p2 <= right) {
            //如果发现逆数
            if (nums[indexes[p1]] > nums[indexes[p2]]) {
                //把索引排序（相当于数组排序了）
                cache[cIndex++] = indexes[p2++];
            } else {
                cache[cIndex++] = indexes[p1++];
            }
        }
        //当有指针越界，就只能是两种情况，一种右边越界，一种左边越界，指针没有同时移动，不存在第三种情况
        while (p1 <= mid) {
            //右边越界，说明这一整排都会比左指针往后找到的元素要小，直接计数
            cache[cIndex++] = indexes[p1++];
        }
        while (p2 <= right) {
            //左边越界，说明这一路过来都比右指针元素要小，没有逆数
            cache[cIndex++] = indexes[p2++];
        }
        for (int i = right; i >= left; i--) {
            //因为cache是生成在新的一段上，为比较段落的长度，而indexes是全局的，在赋值上需要注意索引位置
            indexes[i] = cache[--cIndex];
        }
    }
}
