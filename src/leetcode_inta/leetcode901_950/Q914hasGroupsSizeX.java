package leetcode_inta.leetcode901_950;

/**
 * @author inta
 * @date 2020/2/15
 * @describe 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 *
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 3：
 *
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 4：
 *
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 * 示例 5：
 *
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 *
 * 提示：
 *
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 *
 */
public class Q914hasGroupsSizeX {
    //核心在于最大公约数
    public boolean hasGroupsSizeX(int[] deck) {
        int[] counts = new int[10_001];
        for (int d : deck) {
            counts[d] ++;
        }
        //找最小出现个数
        int min = Integer.MAX_VALUE;
        for (int count : counts) {
            if (count != 0 && count < min) min = count;
        }
        //不满足题意，直接返回
        if (min < 2) return false;
        //如果满足>=2，考虑每个独立元素数量之间的最大公约数，无大于1的公约数就说明不能被切割
        for (int i = 1; i < counts.length; i ++) {
            min = gcd(min, counts[i]);
            if (min <= 1) return false;
        }
        return true;
    }
    //最大公约数
    private int gcd(int p, int q) {
        if (q == 0) return p;
        return gcd(q, p % q);
    }
}
