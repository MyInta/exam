package something.huawei;

import java.util.Scanner;

/**
 * @author inta
 * @date 2020/11/8
 * @describe a c x v c_a
 */
public class Q1 {

    //1-a 2-c 3-x 4-v 5-c_a
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0, temp = 0, copy = 0;
        while (sc.hasNextLine()) {
            String new_line = sc.nextLine();
            String[] splits = new_line.split(" ");
            for (String split : splits) {
                if (split.equals("1")) {
                    res = res - temp + 1;
                    temp = 0;
                } else if (split.equals("2")) {
                    if (temp == 0) continue;
                    copy = temp;
                } else if (split.equals("3")) {
                    if (temp == 0) continue;
                    copy = temp;
                    temp = 0;
                    res = res - copy;
                } else if (split.equals("4")) {
                    res = res - temp + copy;
                    temp = 0;
                } else if (split.equals("5")) {
                    if (res == 0) continue;
                    temp = res;
                }
            }
            System.out.println(res);
        }

    }
}
