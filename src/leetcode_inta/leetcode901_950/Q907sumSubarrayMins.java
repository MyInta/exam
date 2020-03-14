package leetcode_inta.leetcode901_950;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/11/1
 * @describe 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此返回答案模 10^9 + 7。(答案大于该部分，就与1000000007取余)
 *
 *  
 *
 * 示例：
 *
 * 输入：[3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 *  
 *
 * 提示：
 *
 * 1 <= A <= 30000
 * 1 <= A[i] <= 30000
 *
 */
public class Q907sumSubarrayMins {
    //思路两个，一个是找出每个元素它们各自为子数组最小值时候的数量，数量乘元素值和为最终解
    //思路二，试出来的，如 1 7 5 2 4 3 9
    //组成为 1 ； 7 1； 5 5 1； 2 2 2 1； 4 2 2 2 1； 3 3 2 2 2 1； 9 3 3 2 2 2 1
    //即挨个比较出最小值存储，遇到比临近储存小的，替换掉，数量+1；每次遍历都将数值累加和替换（减）即可
    //这里用思路二好写点
    public int sumSubarrayMins(int[] A) {
        if (A == null) return 0;
        //累加所有段的值，即为最终解
        int res = 0;
        //用来记录每段的值
        int temp = 0;
        Stack<Pair> stack = new Stack<>();
        //从头数到尾
        for (int i = 0; i < A.length; i ++) {
            int count = 1;
            while (!stack.isEmpty() && A[i] < stack.peek().val) {
                Pair cur = stack.pop();
                count += cur.count;
                //因为要进行替换，所以减去旧值
                temp -= cur.val * cur.count;
            }
            //因为题目条件，可以知道，段落最大值A[i]为30000乘数量30000也不大于1*10^9所以temp不用取余
            temp += A[i] * count;
            //但是总结果要取余
            res += temp;
            res %= 1_000_000_007;
            Pair newP = new Pair(A[i], count);
            stack.add(newP);
        }

        return res;
    }

    //用来记录每个元素和对应数量
    private class Pair{
        int val;
        int count;
        Pair(int v, int c) {
            this.val = v;
            this.count = c;
        }
    }
}
