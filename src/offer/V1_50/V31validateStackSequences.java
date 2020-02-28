package offer.V1_50;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/28
 * @describe 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1}
 * 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 *
 * 提示：
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 *  LC946
 */
public class V31validateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        Stack<Integer> temp = new Stack<>();
        int pushIndex = 0, popIndex = 0;
        for (;popIndex < len; popIndex ++) {
            //只要和出栈元素不一致，或者栈是空的情况下压栈
            while (pushIndex < len && (temp.isEmpty() || temp.peek() != popped[popIndex])) {
                temp.add(pushed[pushIndex ++]);
            }
            //此时说明两种情况，一种是没找到出栈元素，索引越界，另一种是找到出栈元素，我们进行出栈操作
            if (temp.peek() != popped[popIndex]) return false;
            temp.pop();
        }
        return true;
    }

    //大神简洁版
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int len = pushed.length;
        int popIndex = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i ++) {
            //正常情况就是一直压栈
            stack.push(pushed[i]);
            //遇到非正常情况，非空下发现栈顶和出栈元素一致，就出栈
            while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                //并且准备比较下一个出栈元素
                popIndex ++;
            }
            //如果要比较的出栈元素超标，就退出循环
            if (popIndex >= len) break;
        }
        //判断是否成功比较到了最后一个元素
        return popIndex == len;
    }
}
