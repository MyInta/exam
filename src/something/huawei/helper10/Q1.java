package something.huawei.helper10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2022/1/8
 * @describe
 */
public class Q1 {
    private static boolean checkValid(String str) {
        if (str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static String method(List<Integer> list) {
        int res = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int curValue = list.get(i) << (32 - 8 * (i + 1));
            res += curValue;
        }
        return String.valueOf(res);
    }

    // 100#101#1#5
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split("#");
        if (strs.length != 4) {
            System.out.println("Invalid IP");
        } else {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                boolean valid = checkValid(strs[i]);
                if (!valid) {
                    System.out.println("Invalid IP");
                    break;
                }
                Integer curNum = Integer.valueOf(strs[i]);
                if ((i == 0 && (curNum < 1 || curNum > 128)) || (i > 0 && (curNum < 0 || curNum > 255))) {
                    System.out.println("Invalid IP");
                    break;
                }
                if (list.contains(curNum)) {
                    System.out.println("Invalid IP");
                    break;
                }
                list.add(curNum);
            }
            System.out.println(method(list));
        }
    }
}
