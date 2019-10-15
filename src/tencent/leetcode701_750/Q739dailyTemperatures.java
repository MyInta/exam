package tencent.leetcode701_750;

/**
 * @author inta
 * @date 2019/10/15
 * @describe 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高
 * 超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 */
public class Q739dailyTemperatures {
    //暴力法 内存占用极小，但是费时的很
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i ++) {
            boolean flag = false;
            int count = 0;
            for (int j = i + 1; j < T.length; j ++) {
                count ++;
                if (T[j] > T[i]) {
                    //表明有升温的可能
                    flag = true;
                    break;
                }
            }
            if (flag) {
                res[i] = count;
            } else {
                res[i] = 0;
            }
        }
        return res;
    }

    //倒着排序，利用已知结果，减少重复比较（跳过比后一元素还要小的元素个数）
    public int[] dailyTemperatures2(int[] T) {
        int[] res = new int[T.length];
        for (int i = T.length - 1; i >= 0; i --) {
            for (int j = i + 1; j < T.length; j += res[j]) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0){
                    //res为0说明后面的元素都比当前元素小，且处于T[j] <= T[i]的状态，说明为0
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }
}
