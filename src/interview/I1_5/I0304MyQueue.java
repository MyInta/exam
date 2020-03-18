package interview.I1_5;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/3/17
 * @describe 实现一个MyQueue类，该类用两个栈来实现一个队列。
 *
 * 示例：
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 *
 * 说明：
 *
 *     你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 *     你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *     假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 */
public class I0304MyQueue {

    private Stack<Integer> nums;
    private Stack<Integer> temp;
    /** Initialize your data structure here. */
    public I0304MyQueue() {
        nums = new Stack<>();
        temp = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        temp.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (empty()) return -1;
        if (nums.isEmpty()) {
            while (!temp.isEmpty()) {
                nums.push(temp.pop());
            }
        }
        return nums.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (empty()) return -1;
        if (nums.isEmpty()) {
            while (!temp.isEmpty()) {
                nums.push(temp.pop());
            }
        }
        return nums.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return nums.isEmpty() && temp.isEmpty();
    }
}
