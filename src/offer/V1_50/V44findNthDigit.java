package offer.V1_50;

/**
 * @author inta
 * @date 2020/6/2
 * @describe 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *
 *
 *
 * 限制：
 *
 *     0 <= n < 2^31
 *
 */
public class V44findNthDigit {
    //找规律的题目，纸上获得规律，n是由1+1x9+20x9+300x9+...+900_000_000x9组成
    public int findNthDigit(int n) {
        //找出n所在哪一个x9的段位
        if ( n <= 9) return n;
        n --;
        long base = 9, digits = 1;
        while (n - base * digits > 0) {
            n -= base * digits;
            base *= 10;
            digits ++;
        }
        //此时n代表属于该段位第几个数字,digits代表位数
        long realNum = (int)Math.pow(10, digits - 1) + n / digits;
        String str = realNum + "";
        return str.charAt((int)(n % digits)) - '0';
    }
}
