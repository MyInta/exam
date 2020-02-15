package tencent.leetcode701_750;

import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/2/14
 * @describe 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，
 * 它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 */
public class Q703KthLargest {

    //维护一个最大优先数组就可以了吧
    private PriorityQueue<Integer> p;
    private int K;
    public Q703KthLargest(int k, int[] nums) {
        p = new PriorityQueue<>((a,b)->a - b);
        this.K = k;
        for (int num : nums) {
            p.add(num);
            //因为题意k>=1，所以我没担心p空指针
            if (p.size() > K) {
                p.poll();
            }
        }
    }

    public int add(int val) {
        if (p.size() < K) {
            p.add(val);
        } else if (p.peek() < val) {
            p.poll();
            p.add(val);
        }
        return p.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */