package tencent.leetcode501_550;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/22
 * @describe 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 */
public class Q503nextGreaterElements {
    //关于这个问题，我在2020年趣味竞赛狼人杀设计中思考过，用空间换逻辑复杂度，拷贝3倍体实现前后循环，这里2倍即可
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] threeNums = new int[2 * len];
        for (int i = 0; i < threeNums.length; i ++) {
            threeNums[i] = nums[i % len];
        }
        //结果集长度和原数组元素数量一致
        int[] res = new int[len];
        for (int i = 0; i < res.length; i ++) {
            res[i] = findMax(threeNums, nums[i], i, len);
        }
        return res;
    }
    private int findMax(int[] threeNums, int target, int start, int len) {
        int cur = start;
        while (cur < len + start) {
            if (threeNums[cur] > target) {
                return threeNums[cur];
            }
            cur ++;
        }
        return -1;
    }

    //官解是直接维护一个单调栈，从尾刷到头刷两遍实现,效率其实不高,不高原因在取余操作费时，改成下面方法3就好了
    public int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        //stack保存索引位置
        Stack<Integer> stack = new Stack<>();
        //两倍长度和上面解法类似，都需要取余来实现矫正到正确数组元素所在位置上
        for (int i = 2 * len - 1; i >= 0; i --) {
            int temp = nums[i % len];
            while (!stack.isEmpty() && temp >= nums[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res[i % len] = nums[stack.peek()];
            } else {
                res[i % len] = -1;
            }
            stack.push(i % len);
        }
        return res;
    }

    public int[] nextGreaterElements3(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        //stack保存索引位置
        Stack<Integer> stack = new Stack<>();
        //第一圈遍历，只是为了将数组中按逆序遍历下最大值在栈底，小的在栈顶方式排置
        for (int i = len - 1; i >= 0; i --) {
            int temp = nums[i];
            while (!stack.isEmpty() && temp >= stack.peek()) {
                stack.pop();
            }
            stack.push(temp);
        }
        //第二遍遍历就是将真正的值与排序栈内最大值对比，并选取合理值附上
        for (int i = len - 1; i >= 0; i --) {
            int temp = nums[i];
            while (!stack.isEmpty() && temp >= stack.peek()) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek();
            } else {
                res[i] = -1;
            }
            stack.push(temp);
        }
        return res;
    }
}
