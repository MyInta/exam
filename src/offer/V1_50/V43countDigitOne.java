package offer.V1_50;

/**
 * @author inta
 * @date 2020/3/5
 * @describe 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：6
 *  
 *
 * 限制：
 *
 * 1 <= n < 2^31
 *  LC233
 */
public class V43countDigitOne {
    //理论可行，但耗时太久
//    public int countDigitOne(int n) {
//        int res = 0;
//        while (n > 0) {
//            res += countOne(n);
//            n --;
//        }
//        return res;
//    }
//    private int countOne(int num) {
//        String str = String.valueOf(num);
//        int res = 0;
//        int index = 0;
//        while (str.indexOf('1',index) != -1) {
//            res ++;
//            index = str.indexOf('1',index);
//        }
//        return res;
//    }

    //思考了一下，统计的1，应该是首位判是否！=0，是就加n-1位数据+1，类推，直到n为0
    public int countDigitOne(int n) {
        if (n < 1) return 0;
        if (n < 10) return 1;
        String str = String.valueOf(n);
        int len = str.length();
        int hight = str.charAt(0) - '0';
        int withoutHigh = n - hight * (int)Math.pow(10, len - 1);
        int first = hight == 1 ? withoutHigh + 1 : (int)Math.pow(10, len - 1);
        int second = hight * (len - 1) * (int)Math.pow(10, len - 2);
        return first + second + countDigitOne(withoutHigh);
    }
}
