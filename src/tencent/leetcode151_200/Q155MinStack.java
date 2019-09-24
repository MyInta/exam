package tencent.leetcode151_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/9/24
 * @describe 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 */
public class Q155MinStack {

    //不用栈辅助，效率极低
    private int[] arr;
    private int size;

    /** initialize your data structure here. */
    public Q155MinStack() {
        arr = new int[10];
        size = 0;
    }
    private void extend(int newSize) {
        arr = Arrays.copyOf(arr, newSize);
    }

    public void push(int x) {
        if (size + 1 > arr.length) {
            extend( arr.length<<1);
        }
        arr[size++] = x;
    }

    public void pop() {
        size--;
    }

    public int top() {
        return arr[size-1];
    }

    public int getMin() {
        int temp = arr[0];
        for (int i = 1; i <= size; i++) {
            temp = Math.min(temp, arr[i]);
        }
        return temp;
    }


    //使用双栈下效率较高
    private class Q155MinStack2{
        //两个栈的操作都同步进行
        private Stack<Integer> data;
        private Stack<Integer> min;
        public Q155MinStack2() {
            data = new Stack<Integer>();
            min = new Stack<Integer>();
        }
        public void push(int x) {
            data.push(x);
            if (min.isEmpty() || min.peek() >= x) {
                min.push(x);
            } else {
                //如果栈顶不为空且新加入元素比栈顶大，就重复添加一个栈顶元素
                min.push(min.peek());
            }
        }
        public void pop() {
            if (data.isEmpty()) {
                return;
            }
            data.pop();
            min.pop();
        }
        public int top() {
            if (!data.isEmpty()) {
                return data.peek();
            }
            throw new RuntimeException("错误操作：栈为空不能进行top");
        }
        public int getMin() {
            if (!min.isEmpty()) {
                return min.peek();
            }
            throw new RuntimeException("错误操作：栈为空不能进行最小值取值操作");
        }
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