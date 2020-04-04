package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/5
 * @describe 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 */
public class Q42trap {
    public int trap(int[] height) {
        //这里我们要确认三个变量，left、right以及当前右值高峰second_right(都记录的是索引)
        if (height == null || height.length < 3) return 0;
        int size = height.length;
        int left = 0, right = 1, second_right, res = 0;
        while (right < size) {
            //一开始第二高峰默认为right位置
            second_right = right;
            //如果出现凹下去的现象，就深入遍历，看能不能形成盆地
            if (height[right] < height[left]) {
                while (right < size) {
                    if (height[right] >= height[left]) {
                        second_right = right;
                        break;
                    }
                    if (height[right] >= height[second_right]) {
                        second_right = right;
                    }
                    right ++;
                }
                //此时统计出一个新的盆地，或者到了尽头，不管哪种，都先累加下盆地内水量
                res += count(height, left, second_right);
            }
            //遍历下一个位置
            left = second_right;
            right = left + 1;
        }
        return res;
    }
    private int count(int[] height, int left, int right) {
        int sum = 0;
        int minHeight = Math.min(height[left], height[right]);
        for (int i = left + 1; i < right; i++) {
            sum += minHeight - height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Q42trap q42trap = new Q42trap();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(q42trap.trap(height));
    }

    //最初始的思考方式，很多可以精简的地方
    private class Q42trap2{
        public int trap(int[] height) {
            int res = 0;
            int range = height.length;
            if (range <= 2) return res;
            int left = 0;
            int right = 1;
            int temp;
            while (right < range) {
                if (height[left] > height[right]) {
                    temp = right;
                    int l_r_secondHigh = right;
                    while (right < range) {
                        //注意，需要考虑右边遇到相等高度的情况
                        if (height[right] >= height[left]) {
                            temp = right;
                            break;
                        }
                        if (height[right] >= height[l_r_secondHigh]) {
                            //记录右边遍历过去的最大值
                            l_r_secondHigh = right;
                        }
                        right ++;
                    }
                    //说明没有找到比left大的right了
                    if (right != temp) {
                        //右指针回归到右边遍历最大的值
                        right = l_r_secondHigh;
                    }
                }
                res += count(height, left, right);
                left = right;
                right ++;
            }
            return res;
        }
        //默认left为短板
        private int count(int[] height, int left, int right) {
            int sum = 0;
            int minHeight = Math.min(height[left], height[right]);
            for (int i = left + 1; i < right; i ++) {
                sum += minHeight - height[i];
            }
            return sum;
        }
    }
}
