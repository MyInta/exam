package leetcode_inta.leetcode51_100;


/**
 * @author inta
 * @date 2019/7/24
 * @describe 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 *
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class Q72MidDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1+1][len2+1];
        dp[0][0] = 0;
        //dp[0][x]即当word1为空时，成为word2只有添加操作
        for(int i=1;i<=len2;i++){
            dp[0][i] = i;
//            dp[0][i] = dp[0][i-1]+1;
        }
        //dp[x][0]即当word2为空时，word1变为word2只有删除操作
        for(int j=1;j<=len1;j++){
            dp[j][0] = j;
//            dp[j][0] = dp[j-1][0]+1;
        }
        //当考虑其他非空情况时
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                //需要考虑索引位置元素是否相等，相等没有操作，不相等进行操作
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    //操作次数保持原先一级索引位置的操作次数
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(
                            Math.min(
                                   dp[i-1][j],dp[i][j-1]
                            ),
                            dp[i-1][j-1]
                    )+1;
                }
            }
        }
        return dp[len1][len2];
    }
//改
//        minDistance(word1.substring(0,idx)+word2.charAt(idx)+word1.substring(idx+1),word2)+1,
    //加
//        minDistance(word1.substring(0,idx)+word2.charAt(idx)+word1.substring(idx),word2)
    //删
//        minDistance(word1.substring(0,idx)+word1.substring(idx+1),word2)+1

    //自顶向下尝试
    public int[][] res;
    private static int maxV=1_0000_0000;
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        res = new int[len1+1][len2+1];
        res[0][0] = 0;
        for(int i=1;i<=len1;i++){
            res[i][0] = i;
        }
        for(int j=1;j<=len2;j++){
            res[0][j] = j;
        }
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                res[i][j] = maxV;
            }
        }
        return solve(len1,len2,word1,word2);
    }

    private int solve(int st1,int st2,String word1,String word2){
        if(st1==0){
            return res[0][st2];
        }
        if(st2==0){
            return res[st1][0];
        }
        if(res[st1][st2]<maxV){
            return res[st1][st2];
        }

        if(word1.charAt(st1-1) == word2.charAt(st2-1)){
            res[st1][st2] = solve(st1-1,st2-1,word1,word2);
        }else{
            res[st1][st2] = Math.min(
              Math.min(
                      solve(st1-1,st2,word1,word2),
                      solve(st1,st2-1,word1,word2)
              ),
                    solve(st1-1,st2-1,word1,word2)
            )+1;

        }
        return res[st1][st2];
    }

    public static void main(String[] args) {
        Q72MidDistance q = new Q72MidDistance();
        int i = q.minDistance2("horse","ros");
        System.out.println(i);
    }
}
