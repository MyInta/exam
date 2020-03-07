package offer.V51_100;

/**
 * @author inta
 * @date 2020/3/5
 * @describe 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 *  
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *  
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 */
public class V65add {
    //核心在于考虑 a&b计算的是进位 a^b计算的是无进位的求和
    public int add(int a, int b) {
        while (b != 0) {
            int temp = a ^ b;//求和（不计进位）. 相同位置0，相反位置1
            b = (a & b) << 1;//计算进位. 先保留同为1的位，都为1的位要向左进位，因此左移1位
            a = temp;
        }
        return a;
    }
}
