package something.huawei.helper3;

import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/12
 * @describe
 */
public class Q1 {
    public static int method(int power, int len, String[] arrStr) {
        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.valueOf(arrStr[i]);
        }
        int res = 0;
        int resume = 0;
        for (int num : arr) {
            if (num + resume > power) {
                resume = (num + resume) - power;
            } else {
                resume = 0;
            }
            res++;
        }
        if (resume > 0) {
            res += resume % power == 0 ? resume / power : resume / power + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int power = Integer.valueOf(sc.nextLine());
        int len = Integer.valueOf(sc.nextLine());
        String[] arrStr = sc.nextLine().split(" ");
        System.out.println(method(power, len, arrStr));
    }
}
