package interview.I1_5;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/4/20
 * @describe 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
 * 最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 *
 * 示例1:
 *
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：
 * [null,null,null,1,null,2]
 *
 * 示例2:
 *
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：
 * [null,null,null,null,null,true]
 *
 * 说明:
 *
 *     栈中的元素数目在[0, 5000]范围内。
 *
 */
public class I0305SortedStack {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public I0305SortedStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int val) {
        if (!stack1.isEmpty() && stack1.peek() >= val) {
            stack1.push(val);
        } else {
            while (!stack1.isEmpty() && stack1.peek() < val) {
                stack2.push(stack1.pop());
            }
            stack1.push(val);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
    }

    public void pop() {
        //如果空pop会怎么样,会返回null
        if (!stack1.isEmpty()) stack1.pop();
    }

    public int peek() {
        return stack1.isEmpty() ? -1 : stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}
/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */
