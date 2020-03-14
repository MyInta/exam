package leetcode_inta.leetcode251_300;

import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/10/17
 * @describe 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 */
public class Q295MedianFinder {

    private PriorityQueue<Double> minQueue;
    private PriorityQueue<Double> maxQueue;
    private int size;
    //使用大小堆，来实现
    /** initialize your data structure here. */
    public Q295MedianFinder() {
        this.minQueue = new PriorityQueue<Double>((x, y) -> (int) (x - y));
        this.maxQueue = new PriorityQueue<Double>((x, y) -> (int) (y - x));
        this.size = 0;
    }

    public void addNum(int num) {
        //按照size奇偶数划分add方式，保证大顶堆最大不超过小顶堆最小值，当为偶时，小顶堆移动数给大顶堆
        size ++;
        maxQueue.offer((double) num);
        //小顶堆永远添加比大顶堆不小的数
        minQueue.offer(maxQueue.poll());
        if ((size & 1) == 1) {
            //说明是奇数时，小顶堆要移动一元素给大顶堆
            maxQueue.offer(minQueue.poll());
        }
    }

    public double findMedian() {
        if ((size & 1) == 0) {
            //如果是偶数，就返回两个顶堆的顶之平均
            return (minQueue.peek() + maxQueue.peek()) / 2;
        } else {
            //奇数的话就直接返回大顶堆上的顶即可
            return maxQueue.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
