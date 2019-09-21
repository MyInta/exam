package tencent.exam_main;

/**
 * @author inta
 * @date 2019/9/21
 * @describe 要求符合11位，且首位为8的电话号码
 * 输入 首个代表有几组号码，每组第一行代表号码长度，第二行代表号码
 * 并且允许对号码进行删减，要求在有限次操作内，可以变成正常号码就返回YES，否则返回NO
 * 如输入2
 *      11
 *      88888888888
 *      3
 *      000
 *   返回 YES
 *        NO
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.nextLine();
        int n_value = Integer.valueOf(n);
        ArrayList<String> res = new ArrayList<>(n_value);
        for (int i = 0; i < n_value; i++) {
            String len = in.nextLine();
            int sum_len = Integer.valueOf(len);
            if (sum_len < 11) {
                res.add("NO");
                in.nextLine();
                continue;
            }
            String s = in.nextLine();
            boolean flag = false;
            for (int j = 0; j <= sum_len-11; j++) {
                if (s.charAt(j) == '8') {
                    res.add("YES");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                res.add("NO");
            }
        }
        for (String str:res) {
            System.out.println(str);
        }
    }
}
