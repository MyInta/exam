package leetcode_inta.leetcode851_900;

/**
 * @author inta
 * @date 2020/6/10
 * @describe 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 *
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 *
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。
 * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 *
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 *
 *
 * 示例：
 *
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *
 *
 *
 * 提示：
 *
 *     2 <= piles.length <= 500
 *     piles.length 是偶数。
 *     1 <= piles[i] <= 500
 *     sum(piles) 是奇数。
 *
 */
public class Q877stoneGame {
    //动规题
    public boolean stoneGame(int[] piles) {
        int size = piles.length, sum = 0;
        //表示从i堆到j堆，取完之后获得的利润
        int[][] dp = new int[size][size];
        //默认下i堆到i堆获得的利润是该堆原有的数量
        for (int i = 0; i < size; i++) {
            dp[i][i] = piles[i];
            sum += piles[i];
        }
        //开始遍历，每一圈缩拢
        for (int i = 1; i < size; i++) {
            //我们从后往前，所以一开始就只有两个元素
            for (int j = 0; j < size - i; j++) {
                //比较j->i+j这一段可以取的情况（j<size-i，所以不会越界）,取左边或者右边，获得的最大值
                dp[j][j + i] = Math.max(dp[j + 1][j + i] + piles[j], dp[j][j + i - 1] + piles[j + i]);
            }
        }
        //最后我们比较下获得的最大值情况dp[0][size-1]是否超过sum(piles)的一半
        return dp[0][size - 1] * 2 > sum;
    }
}
