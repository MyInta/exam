package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2020/2/12
 * @describe 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 */
public class Q97isInterleave {

    //网友说了，遇到这种字符串题，用dfs就是弟弟中的弟弟，好吧，测试了下，效率是真低，1800ms
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        return dfs(s1, s2, s3, 0, 0, 0);
    }
    private boolean dfs(String s1, String s2, String s3, int start1, int start2, int cur) {
        if (cur == s3.length()) return true;
        if (start1 == s1.length()) return s2.substring(start2).equals(s3.substring(cur));
        if (start2 == s2.length()) return s1.substring(start1).equals(s3.substring(cur));
        char temp = s3.charAt(cur);
        if (s1.charAt(start1) == temp) {
            //如果s1与s2都找到和cur相同字符，考虑两条路线
            if (s2.charAt(start2) == temp) {
                return dfs(s1, s2, s3, start1 + 1, start2, cur + 1)
                        || dfs(s1, s2, s3, start1, start2 + 1, cur + 1);
            } else {
                //这种时候，只可能是属于s1
                return dfs(s1, s2, s3, start1 + 1, start2, cur + 1);
            }
        } else if (s2.charAt(start2) == temp) {
            //上面筛选过，说明不存在两个都相同的情况
            return dfs(s1, s2, s3, start1, start2 + 1, cur + 1);
        }
        //如果都不想等，说明为false，返回即可
        return false;
    }

    //尝试dp，怎么弄呢
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        //初始化
        for (int i = 1; i <= s1.length(); i ++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s2.length(); i ++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s1.length(); i ++) {
            for (int j = 1; j <= s2.length(); j ++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1 + i));
            }
        }
        return dp[s1.length()][s2.length()];
    }

    //一维dp，根据上面二维改，发现状态只和j相关
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i ++) {
            for (int j = 0; j <= s2.length(); j ++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }


    //但其实只要dfs加个memo就可以高效解决问题
    public boolean isInterleave4(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        //直觉告诉我，要添加一个记忆模块，防止重复过程造成的时间浪费
        valid = new int[s1.length() + 1][s2.length() + 1];
        return dfs(s1, 0, s2, 0, s3, 0);
    }
    //0 1 2 0代表未访问， 1可以实现 2不能实现
    private int[][] valid;
    private boolean dfs(String s1, int index_s1, String s2, int index_s2, String s3, int index_s3) {
        if (valid[index_s1][index_s2] != 0) return valid[index_s1][index_s2] == 1;
        if (index_s3 == s3.length()) return true;
        if (index_s1 == s1.length()) {
            valid[index_s1][index_s2] = s3.substring(index_s3).equals(s2.substring(index_s2)) ? 1 : 2;
            return valid[index_s1][index_s2] == 1;
        }
        if (index_s2 == s2.length()) {
            valid[index_s1][index_s2] = s3.substring(index_s3).equals(s1.substring(index_s1)) ? 1 : 2;
            return valid[index_s1][index_s2] == 1;
        }
        char cur = s3.charAt(index_s3);
        if (cur != s1.charAt(index_s1) && cur != s2.charAt(index_s2)) {
            valid[index_s1][index_s2] = 2;
            return false;
        }
        if (cur == s1.charAt(index_s1)) {
            valid[index_s1][index_s2] = dfs(s1, index_s1 + 1, s2, index_s2, s3, index_s3 + 1)
                    || (cur == s2.charAt(index_s2) && dfs(s1, index_s1, s2, index_s2 + 1, s3, index_s3 + 1)) ? 1 : 2;
            return valid[index_s1][index_s2] == 1;
        } else {
            valid[index_s1][index_s2] = dfs(s1, index_s1, s2, index_s2 + 1, s3, index_s3 + 1) ? 1 : 2;
            return valid[index_s1][index_s2] == 1;
        }
    }
}
