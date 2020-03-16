package interview.I1_5;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/3/16
 * @describe 请设计一个栈，除了常规栈支持的pop与push函数以外，
 * 还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
 *
 * 示例：
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class I0302MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> stackMin;
    /** initialize your data structure here. */
    public I0302MinStack() {
        stack = new Stack<>();
        stackMin = new Stack<>();
        stackMin.add(Integer.MAX_VALUE);
    }

    public void push(int x) {
        if (x <= stackMin.peek()) stackMin.add(x);
        stack.add(x);
    }

    public void pop() {
        if (stack.pop() <= stackMin.peek()) stackMin.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stackMin.peek();
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */