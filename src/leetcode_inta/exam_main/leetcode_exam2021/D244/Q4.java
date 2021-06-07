package leetcode_inta.exam_main.leetcode_exam2021.D244;

import sun.awt.SunHints;

import java.util.*;

/**
 * @author inta
 * @date 2021/6/6
 * @describe
 */
public class Q4 {
    // 找到第一个比其大的箱子，差值累加
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        int mod = 1_000_000_007;
        for (int[] box : boxes) {
            Arrays.sort(box);
        }
        Arrays.sort(boxes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int min = Integer.MAX_VALUE;
        int maxIndex = Integer.MAX_VALUE;
        for (int[] box : boxes) {
            if (box[0] > maxIndex) {
                break;
            }
            int sum = 0;
            for (int i = packages.length - 1; i >= 0; i--) {
                int pkg = packages[i];
                int value = findBox(box, pkg);
                if (value == -1) {
                    sum = Integer.MAX_VALUE;
                    break;
                }
                sum = (sum + value) % mod;
                if (i == packages.length - 1) {
                    maxIndex = Math.min(maxIndex, pkg + value);
                }
            }
            min = Math.min(min, sum);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int findBox(int[] box, int target) {
        int left = 0;
        int right = box.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (box[mid] == target) {
                return 0;
            } else if (box[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return box[right] >= target ? box[right] - target : -1;
    }
}
