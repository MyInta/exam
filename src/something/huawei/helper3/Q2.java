package something.huawei.helper3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/12
 * @describe
 */
public class Q2 {
    public static int method(int c, int b, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        // 对元素arr[i]计算获取四个字节相加值
        for (int num : arr) {
            int calcRes = calcValue(num, b);
//            System.out.println(calcRes);
            int value = map.getOrDefault(calcRes, 0) + 1;
            if (calcRes < c) {
                res = Math.max(res, value);
            }
            map.put(calcRes, value);
        }
        return res;
    }

    // 计算四字节大小和并取模
    private static int calcValue(int num, int b) {
        int mask = 0b1111;
        int sum = 0;
        int time = 0;
//        System.out.print(num + "--->");
        while (time < 4) {
            sum += mask & num;
            num >>= 4;
            time++;
        }
//        System.out.println(sum);
        return sum % b;
    }

    // 3 4 256 257 258 259 260 261 262 263 264 265
    // 1 4 256 257 258 259 260 261 262 263 264 265
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 第一个为c（limit限定值）、第二个为b（取模值）
        String[] arrStr = sc.nextLine().split(" ");
        int c = Integer.valueOf(arrStr[0]);
        int b = Integer.valueOf(arrStr[1]);
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.valueOf(arrStr[i + 2]);
        }
        System.out.println(method(c, b, arr));
    }
}
