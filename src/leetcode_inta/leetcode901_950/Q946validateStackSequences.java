package leetcode_inta.leetcode901_950;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/6/11
 * @describe 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
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
 *
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 *
 *
 * 提示：
 *
 *     0 <= pushed.length == popped.length <= 1000
 *     0 <= pushed[i], popped[i] < 1000
 *     pushed 是 popped 的排列。
 *
 */
public class Q946validateStackSequences {
    //维持一个栈，依据pushed入栈，碰到popped的出栈，没碰到就继续入栈，直到最后返回栈是否空
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        //分别保存压栈和出栈的遍历时索引
        int index_push = 0, index_pop = 0;
        //只要索引没越界，就一直进行
        while (index_push < pushed.length && index_pop < popped.length) {
            //如果栈为空，或者压栈遍历到的元素并非需要出栈的
            if (stack.isEmpty() || pushed[index_push] != popped[index_pop]) {
                stack.push(pushed[index_push ++]);
            } else {
                //否则，说明碰到需要出栈的
                stack.push(pushed[index_push ++]);
            }
            while (!stack.isEmpty() && index_pop < popped.length && stack.peek() == popped[index_pop]) {
                stack.pop();
                index_pop ++;
            }
        }
        //遍历结束，我们看需要出栈的元素是否出栈了
        return index_pop == popped.length;
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        //记录pop的索引
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                j ++;
                stack.pop();
            }
        }
        return j == popped.length;
    }
}
