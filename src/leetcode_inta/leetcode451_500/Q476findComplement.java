package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2019/12/21
 * @describe 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 *
 * 注意:
 *
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 * 示例 1:
 *
 * 输入: 5
 * 输出: 2
 * 解释: 5的二进制表示为101（没有前导零位），其补数为010。所以你需要输出2。
 * 示例 2:
 *
 * 输入: 1
 * 输出: 0
 * 解释: 1的二进制表示为1（没有前导零位），其补数为0。所以你需要输出0。
 *
 */
public class Q476findComplement {
    //自己实现的方法，真的慢。。。
    public int findComplement(int num) {
        String num_str = Integer.toBinaryString(num);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num_str.length(); i ++) {
            int complement = 1 ^ (num_str.charAt(i) - '0');
            sb.append(complement);
        }
        return Integer.valueOf(sb.toString(), 2);
    }


    public int findComplement2(int num) {
        int res = 0;
        int count = 0;
        while (num > 1) {
            int resume = num % 2;
            res += (1 ^ resume) * (1 << count);
            num >>= 1;
            count ++;
        }
        return res;
    }

    //大神的位操作
    public int findComplement3(int num) {
        int tmp = num;
        int num2 = 1;
        while (tmp > 0) {
            num2 <<= 1;
            tmp >>= 1;
        }
        return (num2 - 1) ^ num;
    }
}
