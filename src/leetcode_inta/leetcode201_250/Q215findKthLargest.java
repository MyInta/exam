package leetcode_inta.leetcode201_250;

import java.awt.image.renderable.RenderableImage;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/9/29
 * @describe 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 */
public class Q215findKthLargest {
    //这是使用库函数的取巧方法，时间复杂度为jdk的默认排序方式，一般为快速排序nlogn
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    //使用最小堆 和最大堆两种方式解决问题（优化上取决于k的大小选取，或者在同一种方式上，反过来选取len-k+1的最小值）
    //最小堆即求第len-k +1小的数
    public int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        //lambda这种表达方式(a,b) -> a-b是最小堆表达方式（是默认的），如果是(a,b) -> b-a则是最大堆
        PriorityQueue<Integer> p = new PriorityQueue<>(len,(a,b) -> a - b);
        for (int i =0; i < len; i++) {
            p.add(nums[i]);
        }
        //现在排列好的是个顺金字塔，我们求其k大的数，即要去掉顶部len-k的数
        for (int j = 0; j < len - k; j++) {
            p.poll();
        }
        //也可以p.poll()，去不去掉无所谓了
        return p.peek();
    }
    //最大堆同理,只是再继续优化一下，让其可以依据k值选择最大或最小排序（使用数学思维就是len-k+1反向数）
    //还有一种是利用堆自行排序，在添加的过程中，限定堆长度，超过堆长度的时候，就比较栈顶大小，选择去并添加还是无操作
    public int findKthLargest3(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<Integer> p = null;
        //如果k小于一半长度，就选择最大堆，反之选择最小堆（或者找第len-k+1大的数）
        if ((len >> 1) > k) {
            p = new PriorityQueue<>(len, (a,b) -> b - a);
            for (int i = 0; i < len; i++) {
                p.add(nums[i]);
            }
            for (int j = 0; j < k - 1; j++) {
                p.poll();
            }
        } else {
            p = new PriorityQueue<>(len,(a,b) -> a - b);
            for (int i =0; i < len; i++) {
                p.add(nums[i]);
            }
            for (int j = 0; j < len - k; j++) {
                p.poll();
            }
        }
        return p.poll();
    }

    //最需要学习的还是分而治之的想法
    public int findKthLargest4(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int target = nums.length - k;
        int index = left + (right -left) >> 1;
        while (true) {
            int pivot = partition(left, right, nums);
            if (pivot == target) {
                return nums[pivot];
            } else if (pivot > target) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
    }
    private int partition(int left, int right, int[] nums) {
        int pivot = left;
        int pivotValue = nums[pivot];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivotValue) {
                //移动到pivot后一个位置，将该位置与我们找到的“较小的”数互换
                pivot++;
                swap(i, pivot, nums);
            }
        }
        //全都遍历后，最左边任然是我们的pivotValue，需要将其移动到它正确的位置（即划分大小的地方，也就是pivot）
        swap(pivot, left, nums);
        return pivot;
    }

    private int partition2(int left, int right, int[] nums) {
        int pivot = right;
        int pivotValue = nums[pivot];
        for (int i = right - 1; i >= left; i--) {
            if (nums[i] > pivotValue) {
                //移动到pivot后一个位置，将该位置与我们找到的“较小的”数互换
                pivot--;
                swap(i, pivot, nums);
            }
        }
        //全都遍历后，最左边任然是我们的pivotValue，需要将其移动到它正确的位置（即划分大小的地方，也就是pivot）
        swap(pivot, right, nums);
        return pivot;
    }

    private void swap(int i, int j, int[] nums) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    //20200320快排，重新整理实现下
    public int findKthLargest5(int[] nums, int k) {
        //题意k>1所以省去
        //if (k == 0) return -1;
        int target = nums.length - k;
        int index = partion(nums, 0, nums.length - 1);
        while (index != target) {
            index = index > target ? partion(nums, 0, index - 1) : partion(nums, index + 1, nums.length - 1);
        }
        return nums[index];
    }
    private int partion(int[] nums, int left, int right) {
        int i = left, j = right + 1, tmp = (int) (left + Math.random() * (right - left + 1));
        swap(tmp, left, nums);
        int pivot = nums[left];
        while (true) {
            while (i < right && nums[++ i] < pivot);
            while (j > left && nums[-- j] > pivot);
            if (i >= j) break;
            swap(i, j, nums);
        }
        swap(left, j, nums);
        return j;
    }

}
