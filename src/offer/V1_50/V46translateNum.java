package offer.V1_50;

/**
 * @author inta
 * @date 2020/3/3
 * @describe 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
 * 1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 *
 * 提示：
 *
 * 0 <= num < 2^31
 *
 */
public class V46translateNum {
    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        if (num < 10) return 1;
        int[] dp = new int[numStr.length()];
        dp[0] = 1;
        if ((numStr.charAt(0) - '0') * 10 + (numStr.charAt(1) - '0') < 26) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i < numStr.length(); i ++) {
//            System.out.println((numStr.charAt(i - 1) - '0') * 10 + (numStr.charAt(i) - '0'));
            int v = (numStr.charAt(i - 1) - '0') * 10 + (numStr.charAt(i) - '0');
            if (v < 26 && v > 9) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[numStr.length() - 1];
    }

    //语言精炼点
    public int translateNum2(int num) {
//        if (num < 10) return 1;
        String numStr = String.valueOf(num);
        int len = numStr.length();
        //num 范围0-231，长度范围0-10
        int[] dp = new int[len];
        //增设了一个点
        dp[0] = 1;
        if (len > 1) {
            int cutV = Integer.valueOf(numStr.substring(0, 2));
            dp[1] = (cutV > 9 && cutV < 26) ? 2 : 1;
        }
        for (int i = 2; i < len; i ++) {
            //截取2个长度片段观察可行的组成数量
            int n = Integer.valueOf(numStr.substring(i - 1, i + 1));
            dp[i] = dp[i - 1] + ((n > 9 && n < 26) ? dp[i - 2] : 0);
        }
        return dp[len - 1];
    }
}
