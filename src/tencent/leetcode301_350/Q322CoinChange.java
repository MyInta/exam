package tencent.leetcode301_350;

/**
 * @author inta
 * @date 2019/7/24
 * @describe 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 */
public class Q322CoinChange {
    public int coinChange(int[] coins, int amount) {
        results = new int[coins.length][amount+1];
        for(int i=1;i<coins.length;i++){
            for(int j=0;j<amount;j++){
                results[i][j] = -1;
            }
        }
        int res = search(0,coins,amount);
        if(res<maxV){
            return res;
        }else{
            return -1;
        }
    }

    public static int maxV = 1_0000_0000;
    int[][] results;

    private int search(int idx,int[] coins,int amount){
        //先是边界条件
        if(idx>=coins.length){
            return maxV;
        }
        if(amount<0){
            return maxV;
        }
        if(amount==0){
            return 0;
        }
        if(results[idx][amount]>0){
            return results[idx][amount];
        }
        results[idx][amount] = Math.min(search(idx,coins,amount-coins[idx])+1
                ,search(idx+1,coins,amount));
        return results[idx][amount];
    }

}
