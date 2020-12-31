package something.lexin;

import java.util.*;

/**
 * @author inta
 * @date 2020/9/9
 * @describe
 */
public class Q2 {

    public int minCostConnectPoints(int[][] points) {
        int res = 0;
        int size = points.length;
        Set<Integer> set = new HashSet<>();
        for (int k = 0; k < points.length; k++) {
            if (set.contains(k)) continue;
            int[] temp = points[k];
            int dis = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < points.length; i++) {
                if (i == k) continue;
                int[] temp_point = points[i];
                int v = Math.abs(temp_point[1] - temp[1]) + Math.abs(temp_point[0] - temp[0]);
                if (v < dis) {
                    dis = v;
                    index = i;
                }
            }
            if (dis != Integer.MAX_VALUE) {
                res += dis;
//                System.out.println(dis);
                set.add(k);
                set.add(index);
            }
        }
        return res;
    }
}
