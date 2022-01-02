package leetcode_inta.exam_main.leetcode_exam2021.D268;

/**
 * @author inta
 * @date 2021/11/21
 * @describe
 */
public class Q2 {
    // 看起来是模拟题，有别的技巧吗？
    public int wateringPlants(int[] plants, int capacity) {
        int step = 0;
        int markIndex = 0;
        int curCap = capacity;
        while (markIndex < plants.length) {
            if (plants[markIndex] > curCap) {
                step += 2 * markIndex;
                curCap = capacity;
            } else {
                curCap -= plants[markIndex];
                markIndex++;
                step++;
            }
        }
        return step;
    }
}
