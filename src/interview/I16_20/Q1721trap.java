package interview.I16_20;

import java.util.*;

/**
 * @author inta
 * @date 2021/4/2
 * @describe 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * 由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Q1721trap {
    public int trap(int[] height) {
        // 先用简单思路，挨个找容量，从左往右
        // 依次找第一根大于等于当前柱高的柱子，两者之间形成洼地，然后以新柱子为新高度依次遍历
        // 但是在找第一根比其高的柱子时，也有可能遍历到逆序的小洼地，这些洼地高度都小于目标高度
        // 所以考虑用栈保存递减信息，遇到逆序层层弹出，找比其高的元素
        // 这样考虑会出现大量重复计算，获取的两柱子索引信息，如果有被新的包裹就舍去，也就是需要一轮合并数组操作
        Stack<Integer> stack = new Stack<>();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] < height[stack.peek()]) {
                stack.push(height[i]);
            } else {
                int left = 0;
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    left = stack.pop();
                }
                if (!stack.isEmpty()) {
                    left = stack.peek();
                }
                lists.add(new int[]{left, i});
            }
        }
        // 此时获得洼地数组，但是考虑有嵌套，故来一波合并
        lists.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int left = -1;
        int res = 0;
        for (int i = 0; i < lists.size(); i++) {
            int[] temp = lists.get(i);
            if (temp[0] == left) {
                continue;
            }
            left = temp[0];
//            System.out.println(temp[0] + "-" + temp[1]);
            res += getTrap(height, temp[0], temp[1]);
        }
        return res;
    }

    private int getTrap(int[] height, int left, int right) {
        int res = 0;
        int target = height[left];
        for (int i = left + 1; i < right; i++) {
            res += target - height[i];
        }
        return res;
    }
}
