package something.huawei.helpdir;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/5
 * @describe
 */
public class Q3 {
    public static int method(int[] arr) {
        Arrays.sort(arr);
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // 当完全能被最大元素分割时
        if (sum % arr[arr.length - 1] == 0) {
            if (check(arr, arr[arr.length - 1])) {
                return sum / arr[arr.length - 1];
            }
        }

        // 否则从最小层高开始考虑补一块，遇到有解的直接返回，因为后面考虑的层数只会更少
        for (int i = 0; i < arr.length - 1; i++) {
            if (check(arr, arr[arr.length - 1] + arr[i])) {
                return sum / (arr[arr.length - 1] + arr[i]);
            }
        }
        return -1;
    }

    // 判断数组能否由target值构成
    private static boolean check(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[right] == target) {
                right--;
            } else if (arr[right] + arr[left] == target) {
                right--;
                left++;
            } else {
                return false;
            }
        }
        if (left == right) {
            return arr[right] == target;
        }
        return true;
    }

    // 这道题目有歧义，每层是最多两个积木还是可以多个积木拼接构成，后者这题会变得巨难
    public static void main(String[] args) {
        // 如果可以被固定值划分，那么数组中随意选取元素只要能凑出这个值即可，剩余的以此类推，若剩余凑不出-1或者判断下一个
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] arrStr = line.split(" ");
        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.valueOf(arrStr[i]);
        }
        System.out.println(method(arr));
    }
}
