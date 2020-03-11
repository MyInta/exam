package leetcode_inta.leetcode;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/8/5
 * @describe 实现栈的逆序，但是只能使用递归函数和这个栈本身
 */
public class StackReverse {

    //该方法可以弹出栈的最先压入的一个元素（与pop原先功能方向刚好相反）
    private int get(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = get(stack);
            stack.push(result);
            return last;
        }
    }

    private void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = get(stack);
        reverse(stack);
        stack.push(i);
    }

}
