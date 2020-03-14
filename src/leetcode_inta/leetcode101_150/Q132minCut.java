package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2020/2/11
 * @describe 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 */
public class Q132minCut {

    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len];
        for (int i = 0; i < len; i ++) {
            dp[i] = i;
        }
        char[] sChars = s.toCharArray();
        for (int i = 1; i < len; i ++) {
            //如果在区间0，i内为回文，就说明dp[i]是不用切割的，为0次
            if (isPalindrome(sChars, 0, i)) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j ++) {
                if (isPalindrome(sChars, j + 1, i)) {
                    //如果[j+1,i)为回文，说明[0,i)可以提供切割为[0,j]和[j+1,i)两区间，前者的切割最少次数为dp[j]
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    //判断是否回文
    private boolean isPalindrome(char[] sChars, int left, int right) {
        while (left < right) {
            if (sChars[left] != sChars[right]) return false;
            left ++;
            right --;
        }
        return true;
    }


    //看了大神的优化，佩服的五体投地，从简入繁，在上面基础上优化，借鉴了leetcode5的最长回文串,但还不是最优解
    public int minCut2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len];
        for (int i = 0; i < len; i ++) {
            dp[i] = i;
        }
        boolean[][] valid = new boolean[len][len];
        //添加所有索引位置对应的回文情况
        for (int i = 0; i < len; i ++) {
            //这里等于，是因为判断是否回文，我们得把一个字符的也算上，不然默认为false就冤枉他了
            for (int j = 0; j <= i; j ++) {
                //这里我原先担心有越界可能，但看了下，越界情况不会出现
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || valid[j + 1][i - 1])) {
                    //说明该索引对也是符合回文的
                    valid[j][i] = true;
                }
            }
        }
        //此刻，我们已经获得了所有索引对对应回文信息,开始遍历字符串，第一层指针标记位置，第二层是在标记位置以前动规最小次数
        for (int i = 1; i < len; i ++) {
            //如果在区间0，i内为回文，就说明dp[i]是不用切割的，为0次
            if (valid[0][i]) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j ++) {
                //如果[j+1,i)为回文，说明[0,i)可以提供切割为[0,j]和[j+1,i)两区间，前者的切割最少次数为dp[j]
                if (valid[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }


}
