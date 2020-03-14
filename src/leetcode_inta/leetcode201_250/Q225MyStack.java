package leetcode_inta.leetcode201_250;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/12/11
 * @describe 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 *
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 */
public class Q225MyStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    /** Initialize your data structure here. */
    public Q225MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = queue1.size();
        for (int i = 0; i < size - 1; i ++) {
            queue2.add(queue1.poll());
        }
        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        int size = queue1.size();
        for (int i = 0; i < size - 1; i ++) {
            queue2.add(queue1.poll());
        }
        int temp = queue1.peek();
        queue2.add(queue1.poll());
        while (!queue2.isEmpty()) {
            queue1.add(queue2.poll());
        }
        return temp;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
