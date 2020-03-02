package offer.V1_50;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/3/2
 * @describe 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 *  
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 *  LC155
 */
public class V30MinStack {

//    private PriorityQueue<Integer> pq;
//    private Stack<Integer> stack;
//
//    /** initialize your data structure here. */
//    public V30MinStack() {
//        pq = new PriorityQueue<>();
//        stack = new Stack<>();
//    }
//
//    public void push(int x) {
//        stack.add(x);
//        pq.add(x);
//    }
//
//    public void pop() {
//        pq.remove(stack.pop());
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int min() {
//        return pq.peek();
//    }

    //看到一个很有意思的想法，就是每压入一个栈元素的时候，考虑压入一个当前最小值
    private int min;
    private Stack<Integer> stack;
    /** initialize your data structure here. */
    public V30MinStack() {
        min = Integer.MAX_VALUE;
        stack = new Stack<>();
    }

    public void push(int x) {
        if (x <= min) {
            //如果要准备压入元素比当前最小值小于或等于，就先压入最小值
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //出栈的时候，需要考虑最小值更新情况
        if (stack.pop() == min) {
            //如果出栈元素和最小值一致，就要更换最小值为之前压入的最小值（注意不可能小于Min,因为前面push的设定）
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}
