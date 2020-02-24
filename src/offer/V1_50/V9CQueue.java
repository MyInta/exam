package offer.V1_50;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/22
 * @describe 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 */
public class V9CQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public V9CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        //把杯子中可能的水先倒到另一个杯子中
        while (!stack1.isEmpty()) {
            stack2.add(stack1.pop());
        }
        //杯子储存水
        stack1.add(value);
        //将另一个杯子水倒回来
        while (!stack2.isEmpty()) {
            stack1.add(stack2.pop());
        }
    }

    public int deleteHead() {
        return stack1.isEmpty() ? -1 : stack1.pop();
    }
}
