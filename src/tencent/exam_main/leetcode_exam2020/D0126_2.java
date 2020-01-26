package tencent.exam_main.leetcode_exam2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/26
 * @describe
 */
public class D0126_2 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        Arrays.sort(restaurants, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(restaurants, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        List<Integer> res = new ArrayList<>();
        int size = restaurants.length;
        for (int i = size - 1; i >= 0; i --) {
            int[] temp = restaurants[i];
            if (temp[2] <= veganFriendly && temp[3] <= maxPrice && temp[4] <= maxDistance) {
                res.add(temp[0]);
            }
        }
        return res;
    }
}
