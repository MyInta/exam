package leetcode_inta.exam_main.leetcode_exam2021.D222;

import java.util.Arrays;

/**
 * @author inta
 * @date 2021/1/3
 * @describe
 */
public class Q1 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a,b)->b[1]-a[1]);
        int sum = 0;
        int index = 0;
        while (truckSize > 0 && index < boxTypes.length) {
            if (truckSize <= boxTypes[index][0]) {
                sum += boxTypes[index][1] * truckSize;
                break;
            } else {
                truckSize -= boxTypes[index][0];
                sum += boxTypes[index][1] * boxTypes[index][0];
                index++;
            }
        }
        return sum;
    }
}
