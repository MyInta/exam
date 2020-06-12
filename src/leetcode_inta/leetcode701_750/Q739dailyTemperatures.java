package leetcode_inta.leetcode701_750;

import java.util.PriorityQueue;

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

    //隔了八个月，重新再做，有别的思路，保持一个优先队列（递增的队列，元素为值与索引）
    //看了网友的题解，发现我其实没必要用Pair引入k/v形式，只要保存索引信息就可以了，比较值时依索引找T元素
    public int[] dailyTemperatures3(int[] T) {
        PriorityQueue<Pair> p = new PriorityQueue<>((a,b)->a.v - b.v);
        int [] res = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--) {
            //注意这里比较的值是小于等于，因为我们找的是最近最大，不相等的情况
            while (!p.isEmpty() && p.peek().v <= T[i]) {
                p.poll();
            }
            if (!p.isEmpty()) {
                res[i] = p.peek().i - i;
            } else {
                //如果优先队列不存在，也就是没有比当前数大的，添加元素即可
                res[i] = 0;
            }
            //无论上述那种情况，新的节点肯定是要被加进去的，用于给左边新元素进行比较
            p.add(new Pair(T[i], i));
        }
        return res;
    }
    private class Pair{
        int v;
        int i;

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        Pair(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }
}
