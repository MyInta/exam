package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/19
 * @describe 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 *
 * 示例：
 *
 * 输入： a = 1, b = 2
 * 输出： 2
 */
public class I1607maximum {
    //a-b如果是负数，首位为1，右移63可以缩减到首位
    public int maximum(int a, int b) {
        int cut = (int) (((long)a - (long)b) >>> 63);
        int[] temp = {a, b};
        return temp[cut];
    }
}
