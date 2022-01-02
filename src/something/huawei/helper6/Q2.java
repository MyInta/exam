package something.huawei.helper6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author inta
 * @date 2021/12/20
 * @describe
 * 相对开音节构成结构为辅音+元音（aeiou）+ 辅音（r除外） + e,常见的单词有bike、cake等
 * 给定一个字符串，以空格为分隔符，反转每个单词中的字母，若单词中包含如数字等其他非字母时不进行反转，
 * 反转后计算其中含有相对开音节结构的子串个数（连续的子串中部分字符可以重复）
 * 输入：
 * 字符串 以空格分割的多个单词 长度小于10000 字母只考虑小写
 * 输出：
 * 含有相对开音节结构的子串个数
 * 输入：
 * ekam a ekac
 * 输出：
 *  2
 * 说明：反转后为 make a cake ,其中 make cake为相对开音节子串，返回2
 *
 */
public class Q2 {
    public static int method(String[] words) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        // 按照题意，如果字符中有其他非字母时不反转
        int res = 0;
        for (String word : words) {
            // 如果包含非字母就不反转
            if (check(word)) {
                for (int i = 3; i < word.length(); i++) {
                    if (word.charAt(i) == 'e') {
                        if (checkSingleChar(word.charAt(i - 1)) || checkSingleChar(word.charAt(i - 2)) || checkSingleChar(word.charAt(i - 3))) {
                            continue;
                        }
                        if (!set.contains(word.charAt(i - 1)) && word.charAt(i - 1) != 'r' && set.contains(word.charAt(i - 2)) && !set.contains(word.charAt(i - 3))) {
                            res++;
                        }
                    }
                }
            } else {
                // 否则反转
                for (int i = word.length() - 4; i >= 0; i--) {
                    if (word.charAt(i) == 'e') {
                        if (!set.contains(word.charAt(i + 1)) && word.charAt(i + 1) != 'r' && set.contains(word.charAt(i + 2)) && !set.contains(word.charAt(i + 3))) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    private static boolean check(String word) {
        for (char c : word.toCharArray()) {
            if (c < 'a' || c > 'z') {
                return true;
            }
        }
        return false;
    }

    private static boolean checkSingleChar(char c) {
        return c < 'a' || c > 'z';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        System.out.println(method(words));
    }
}
