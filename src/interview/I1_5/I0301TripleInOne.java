package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/16
 * @describe 三合一。描述如何只用一个数组来实现三个栈。
 *
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。
 * stackNum表示栈下标，value表示压入的值。
 *
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 *
 * 示例2:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, -1, -1]
 *
 */
public class I0301TripleInOne {

    private int[] stack;
    private int[] stackIndex;
    private int stackSize;
    //stackSize 限定了stack长度，不必考虑扩容等问题
    public I0301TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        stack = new int[stackSize * 3];
        stackIndex = new int[3];
    }

    public void push(int stackNum, int value) {
        stack[3 * stackIndex[stackNum] + stackNum] = value;
        stackIndex[stackNum] ++;
    }

    public int pop(int stackNum) {
        if (stackIndex[stackNum] == 0 ) return -1;
        stackIndex[stackNum] --;
        return stack[3 * stackIndex[stackNum] + stackNum];
    }

    public int peek(int stackNum) {
        if (stackIndex[stackNum] == 0 ) return -1;
        return stack[3 * stackIndex[stackNum] - 3 + stackNum];
    }

    public boolean isEmpty(int stackNum) {
        return stackIndex[stackNum] == 0;
    }

}
/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
