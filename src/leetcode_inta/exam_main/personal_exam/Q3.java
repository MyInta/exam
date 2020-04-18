package leetcode_inta.exam_main.personal_exam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/4/18
 * @describe
 */
public class Q3 {
    //暴力法试一下
    public int[] getTriggerTime2(int[][] increase, int[][] requirements) {
        int inc_len = requirements.length;
        int[] res = new int[inc_len];
        Arrays.fill(res, -2);
        for (int i = 1; i < increase.length; i++) {
            increase[i][0] += increase[i - 1][0];
            increase[i][1] += increase[i - 1][1];
            increase[i][2] += increase[i - 1][2];
        }
        int[] end = increase[increase.length - 1];
        for (int j = 0; j < inc_len; j++) {
            int[] temp = requirements[j];
            if (temp[0] < end[0] || temp[1] < end[1] || temp[2] < end[2]) res[j] = -1;
        }
        int x = 0, y = 0, z = 0;
        for (int j = 0; j < inc_len; j++) {
            int[] requirement = requirements[j];
            if (x >= requirement[0] && y >= requirement[1] && z >= requirement[2]) {
                res[j] = 0;
            }
        }
        for (int i = 0; i < increase.length; i++) {
            int[] target = increase[i];
            x = target[0];
            y = target[1];
            z = target[2];
            for (int j = 0; j < inc_len; j++) {
                if (res[j] > -2) continue;
                int[] requirement = requirements[j];
                if (x >= requirement[0] && y >= requirement[1] && z >= requirement[2]) {
                    res[j] = i + 1;
                }
            }
        }
        for (int i = 0; i < inc_len; i++) {
            if (res[i] == -2) res[i] = -1;
        }
        return res;
    }

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int length = requirements.length;
        int[][] newR = new int[length][4];
        int index = 0;
        for (int[] require : requirements) {
            newR[index][0] = require[0];
            newR[index][1] = require[1];
            newR[index][2] = require[2];
            newR[index][3] = index ++;
        }
        Arrays.sort(newR, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] <= o2[0]) {
                    if (o1[1] <= o2[1]) {
                        return o1[2] - o2[2];
                    }
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int[] res = new int[length];
        Arrays.fill(res, -1);
        int x = 0, y = 0, z = 0;
        for (int k = 0; k < length; k++) {
            int[] requirement = newR[k];
            if (x >= requirement[0] && y >= requirement[1] && z >= requirement[2]) {
                res[k] = 0;
            }
        }
        for (int i = 0; i < increase.length; i++) {
            x += increase[i][0];
            y += increase[i][1];
            z += increase[i][2];
            for (int j = 0; j < length; j++) {
                int[] requirement = newR[j];
                if (res[requirement[3]] != -1) continue;
                if (x >= requirement[0] && y >= requirement[1] && z >= requirement[2]) {
                    res[requirement[3]] = i + 1;
                }
            }
        }
        return res;
    }
}
