package interview.I1_5;

/**
 * @author inta
 * @date 2020/4/22
 * @describe 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，
 * 打印它的二进制表达式。如果该数字不在0和1之间，或者无法精确地用32位以内的二进制表示，则打印“ERROR”。
 *
 * 示例1:
 *
 *  输入：0.625
 *  输出："0.101"
 *
 * 示例2:
 *
 *  输入：0.1
 *  输出："ERROR"
 *  提示：0.1无法被二进制准确表示
 *
 * 提示：
 *
 *     32位包括输出中的"0."这两位。
 *
 */
public class I0502printBin {
    //一开始没理解啥意思，看了讨论区理解了，转化为二进制就是*2判断是否进位，进就取1，并减去进位，否则为0
    public String printBin(double num) {
//        if (num > 0 && num < 1) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("0.");
//            for (int i = 0; i < 30; i++) {
//                num = num * 2;
//                if (num >= 1) {
//                    sb.append("1");
//                    num --;
//                } else if (num > 0) {
//                    sb.append("0");
//                } else {
//                    return sb.toString();
//                }
//            }
//        }
//        return "ERROR";
        if (num > 0 && num < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("0.");
            for (int i = 0; i < 30; i++) {
                num = num * 2;
                if (num >= 1) {
                    sb.append("1");
                    num --;
                } else {
                    sb.append("0");
                }
                if (num == 0) return sb.toString();
            }
        }
        return "ERROR";
    }
}
