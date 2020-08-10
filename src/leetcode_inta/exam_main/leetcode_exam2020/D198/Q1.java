package leetcode_inta.exam_main.leetcode_exam2020.D198;

/**
 * @author inta
 * @date 2020/7/19
 * @describe
 */
public class Q1 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int res = 0, resume = 0, temp = resume;
        if (numBottles < numExchange) return numBottles;
        while (numBottles + resume >= numExchange) {
            res += numBottles;
            temp = (resume + numBottles) % numExchange;
            numBottles = (resume + numBottles) / numExchange;
            resume = temp;
        }
        res += numBottles;
        return res;
    }
}
