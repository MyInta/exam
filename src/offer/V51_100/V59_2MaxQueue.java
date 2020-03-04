package offer.V51_100;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/3/4
 * @describe 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 */
public class V59_2MaxQueue {

    //网友提供的思路，维持一个单调递减栈
    private LinkedList<Integer> queue;
    private LinkedList<Integer> helper;
    public V59_2MaxQueue() {
        queue = new LinkedList<>();
        helper = new LinkedList<>();
    }

    public int max_value() {
        if (helper.isEmpty()) return -1;
        return helper.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        //维持一个单调递减的队列
        while (!helper.isEmpty() && helper.peekLast() < value) {
            //如果队尾元素比当前值小，就一直削掉队尾
            helper.pollLast();
        }
        helper.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) return -1;
        int pop_f = queue.poll();
        if (!helper.isEmpty() && helper.peek() <= pop_f) {
            //如果去除的队首元素，是单调栈中最大元素，去除单调栈顶元素
            helper.poll();
        }
        return pop_f;
    }

}
/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
