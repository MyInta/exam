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
public class D0126_3 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i ++) {
            findWay(edges, distanceThreshold, i, list);
        }
        for (int i = 0; i < n; i ++) {
            int temp = list.get(i);
            if (temp <= min) {
                res = i;
                min = temp;
            }
        }
        return res;
    }
    private void findWay(int[][] edges, int dis, int target, List<Integer> list) {
        for (int[] edge : edges) {
            if (edge[0] == target && edge[2] <= dis) {
                list.set(edge[0], list.get(edge[0]) + 1);
                list.set(edge[1], list.get(edge[1]) + 1);
                if (edge[2] < dis) {
                    findWay(edges, edge[2] - dis, edge[1], list);
                }
            }
        }
    }
}
