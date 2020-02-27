package offer.V51_100;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/2/26
 * @describe 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *  LC239
 */
public class V59maxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int i = 0; i < k - 1; i ++) {
            pq.add(nums[i]);
        }
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int j = k - 1; j < nums.length; j ++) {
            pq.add(nums[j]);
            res[index ++] = pq.peek();
            pq.remove(nums[j - k + 1]);
        }
        return res;
    }

    //滑窗，切割成K块，比较从左到右块落内的最大值和从右到左块内最大值，两者取最大值为窗口的最大值，可理解为切割窗口
    //切割成两份，一份比较中间-左边的最值，一份比较中间-右边的最值，两者之最为窗口之最
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) return new int[0];
        int[] left = new int[len];
        int[] right = new int[len];
        for (int i = 0; i < len; i ++) {
            //从左到右
            if (i % k == 0) {
                //遇到下一个切割的位置，就更新为起始
                left[i] = nums[i];
            } else {
                //因为0的情况在前面出现了，i-1不为负
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            //考虑从右到左
            int j = len - 1 - i;
            if (j % k == 0) {
                right[j] = nums[j];
            } else {
                //考虑一开始如果len-1索引位置，初始值应该为本身
                if (j == len - 1) {
                    right[j] = nums[j];
                } else {
                    //因为len - 1前面有了，所以j + 1不会越界
                    right[j] = Math.max(right[j + 1], nums[j]);
                }
            }
        }
        int[] res = new int[len - k + 1];
        //截止目前，已经把K区间内左右最值都罗列好了，现在两者比较即可，比较k区间内左段right max和右段left max即right[i]和left[j]
        for (int i = 0; i < res.length; i ++) {
            res[i] = Math.max(right[i], left[i + k - 1]);
        }
        return res;
    }

    //双向队列保存数组索引信息，遇到队列尾大的元素，一直删队列尾到满足队列是从大到小即可，每次比较队首索引，如果在滑窗左边，就立马删除
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int len = nums.length;
        if (len * k == 0) return new int[0];
        int[] res = new int[len - k + 1];
        //队列我们只用来保存索引，用索引找数组元素值简单，反过来难
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; i ++) {
            //遇到队列尾还大的元素，为保证递减，需要狂删
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }
            //删完之后，安心添加该元素索引
            queue.addLast(i);
            //每次比较队首，一遇到越界(滑窗范围是[i-k+1,i])立马删除
            if (queue.peekFirst() < i - k + 1) queue.removeFirst();
            //当i到符合滑窗长度索引位置k-1开始，就考虑添加队首索引对应元素到结果集中
            if (i > k - 2) res[i - k + 1] = nums[queue.peekFirst()];
        }
        return res;
    }
}
