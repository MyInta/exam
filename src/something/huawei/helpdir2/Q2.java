package something.huawei.helpdir2;

import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/11
 * @describe
 */
public class Q2 {
    // 将提供的数组排序，按照题意，挨个考虑移动
    public static String method(String[] arr) {
        // 只能先按照贪心算法思想来整，难点在于这个交换是向前交换还是向后交换,a<b<c那么ab交换
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            try {
                res[i] = Integer.valueOf(arr[i]);
            } catch (Exception e) {
                return "[]";
            }
        }
        boolean flag = false;
        for (int i = 0; i < res.length; i++) {
            if (flag) {
                if (i < res.length - 1 && res[i + 1] < res[i]) {
                    swap(res, i, i + 1);
                }
            } else {
                if (i < res.length - 1 && res[i + 1] > res[i]) {
                    swap(res, i, i + 1);
                }
            }
            flag = !flag;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

    private static void swap(int[] res, int i, int j) {
        int temp = res[i];
        res[i] = res[j];
        res[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        System.out.println(method(line.split(" ")));
    }
}
