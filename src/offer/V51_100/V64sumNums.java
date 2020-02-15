package offer.V51_100;

/**
 * @author inta
 * @date 2020/2/13
 * @describe 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 *  
 *
 * 限制：
 *
 * 1 <= n <= 10000
 *
 */
public class V64sumNums {
    public int sumNums(int n) {
        return (int)(Math.pow(n, 2) + n) >> 1;
    }
    //递归，难在于不用条件判断，就是用短路
    public int sumNums2(int n) {
        int sum = n;
        boolean falg = n > 0 && (sum += sumNums2(n - 1)) > 0;
        return sum;
    }
}
