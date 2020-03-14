package leetcode_inta.leetcode151_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/29
 * @describe 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 示例 1:
 *
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 *
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 *
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 */
public class Q166fractionToDecimal {
    //测试默认除数不为零,但需要考虑是否负数
    public String fractionToDecimal(int numerator, int denominator) {
        //排除边界条件
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        //只要双方不一致，就为true，即答案为负数
        if (numerator > 0 ^ denominator > 0) {
            sb.append("-");
        }
        //需要将除数与被除数转成正数，该过程需考虑负值极限造成的溢出，扩展为long消除
        Long num = Math.abs(Long.valueOf(numerator));
        Long den = Math.abs(Long.valueOf(denominator));
        sb.append(num / den);
        num = num % den;
        if (num == 0) return sb.toString();
        //若不是整除，那就需要考虑小数了
        sb.append(".");
        //记载除数和位于字符串解的索引
        Map<Long, Integer> map = new HashMap<>();
        while (num != 0) {
            //如果发现除数已经出现过，就直接返回带括号
            if (map.containsKey(num)) {
                sb.insert(map.get(num), "(");
                sb.append(")");
                //应该也可以直接返回sb的string
                break;
            }
            //若未出现过，就添加map信息
            map.put(num, sb.length());
            //将除数扩展下
            num *= 10;
            //并且再次添加sb
            sb.append(num / den);
            num %= den;
        }
        return sb.toString();
    }
}
