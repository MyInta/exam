package leetcode_inta.leetcode451_500;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/1/26
 * @describe 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 *
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 *
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 *
 * 说明:
 *
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 示例 1:
 *
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 *
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 *
 */
public class Q475findRadius {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int res = 0;
        for (int i = 0; i < houses.length; i ++) {
            res = Math.max(res, binarySearch(heaters, houses[i]));
        }
        return res;
    }
    //寻找离target最近的元素与target的距离
    private int binarySearch(int[] heaters, int target) {
        int left = 0, right = heaters.length - 1;
        int distance;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (heaters[mid] < target) {
                left = mid + 1;
            } else if (heaters[mid] == target) {
                return 0;
            } else {
                right = mid;
            }
        }
        if (left == 0) {
            distance = Math.abs(heaters[left] - target);
        } else {
            distance = Math.min(Math.abs(heaters[left] - target), Math.abs(heaters[left - 1] - target));
        }
        return distance;
    }
}
