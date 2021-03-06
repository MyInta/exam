package leetcode_inta.leetcode601_650;

/**
 * @author inta
 * @date 2020/2/4
 * @describe 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 *
 * 示例1:
 *
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *  
 *
 * 示例2:
 *
 * 输入: 3
 * 输出: False
 *
 */
public class Q633judgeSquareSum {
    // 双指针
    public boolean judgeSquareSum(int c) {
        long i = 0;
        long j = (int) Math.sqrt(c);
        while (i <= j) {
            long sum = i * i + j * j;
            if (sum == c) {
                return true;
            }
            if (sum > c) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    // 官解的sqrt函数
    public boolean judgeSquareSum2(int c) {
        for (int i = 0; i * i < c; i++) {
            double j = Math.sqrt(c - i * i);
            if (j == (int)j) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum3(int c) {
        int mid = (int)Math.sqrt(c);
        for (int i = 0; i <= mid; i++) {
            int resume = c - i * i;
            int resumeSqrt = (int)Math.sqrt(resume);
            if (resumeSqrt * resumeSqrt == resume) {
                return true;
            }
        }
        return false;
    }
}
