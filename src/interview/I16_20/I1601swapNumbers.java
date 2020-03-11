package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/11
 * @describe 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 *
 * 示例：
 *
 * 输入: numbers = [1,2]
 * 输出: [2,1]
 * 提示：
 *
 * numbers.length == 2
 *
 */
public class I1601swapNumbers {
    //又是一道位运算？
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }
}
