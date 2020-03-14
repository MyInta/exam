package leetcode_inta.leetcode1101_1150;

/**
 * @author inta
 * @date 2019/12/27
 * @describe 排排坐，分糖果。
 *
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 *
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 *
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，
 * 依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 *
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），
 * 直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），
 * 这些糖果也会全部发给当前的小朋友。
 *
 * 返回一个长度为 num_people、元素之和为 candies 的数组，
 * 以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 * 示例 2：
 *
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 *  
 *
 * 提示：
 *
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 */
public class Q1103distributeCandies {
    //照着题意写，是最直观的解答
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        //第几个小朋友
        int index = 0;
        //第几轮
        int n = 0;
        while (candies > 0) {
            int add = n * num_people + index + 1;
            //考虑是否够加
            res[index ++] += candies >= add ? add : candies;
            candies -= add;
            //保证轮次递增 , num_people不为0，放心
            n += index / num_people;
            //保证是在这几个小朋友内循环
            index %= num_people;
        }
        return res;
    }

    //或使用数学方式计算，性能应该会非常优吧
    public int[] distributeCandies2(int candies, int num_people) {
        int[] res = new int[num_people];
        //找到可以分到第几个
        int n = (int)(-1 + Math.sqrt(1 + 8 * (long)candies)) / 2;
        for (int i = 1; i <= n; i ++) {
            //挨个将该数量赋给小朋友
            res[(i - 1) % num_people] += i;
        }
        //余数
        int resume = candies - (1 + n) * n / 2;
        //添加余数
        res[n % num_people] += resume;
        return res;
    }
}
