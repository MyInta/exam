package something.huawei.helpdir;

import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/5
 * @describe
 */
public class Q2 {
    public static String method(String[] strArr, int k) {
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strArr[0]);
        StringBuilder temp = new StringBuilder();
        // 每三个切割，为何不直接先将字符拼接方便计算呢？
        for (int i = 1; i < strArr.length; i++) {
            temp.append(strArr[i]);
        }
        char[] chars = temp.toString().toCharArray();

        for (int i = 0; i < chars.length; i += k) {
            sb.append("-").append(dfs(chars, i, k));
        }
        return sb.toString();
    }

    private static String dfs(char[] chars, int start, int k) {
        int right = Integer.min(chars.length - 1, start + k - 1);
        int countMin = 0;
        for (int i = start; i <= right; i++) {
            // 如果是小写
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                countMin++;
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                countMin--;
            }
        }
        // 按照coutMin判断小写和大写谁多,并按照规则拼接字符
        int size = right - start + 1;
        if (countMin > 0) {
            return String.valueOf(chars, start, size).toLowerCase();
        } else if (countMin < 0) {
            return String.valueOf(chars, start, size).toUpperCase();
        } else {
            return String.valueOf(chars, start, size);
        }
    }

    // 暴力求解，按照题以对每个进行转化考虑，可能会超时,存在特殊字符
    // 12abc-abCABc-4aB@
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String kStr = sc.nextLine();
        int k = Integer.valueOf(kStr);
        String lineStr = sc.nextLine();
        String[] strArr = lineStr.split("-");
        System.out.println(method(strArr, k));
    }
}
