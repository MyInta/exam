package something.didi;

import java.util.Scanner;

/**
 * @author inta
 * @date 2020/9/13
 * @describe
 */
public class Q1 {

    private static String solution(String str, String size) {
        int len = Integer.valueOf(size);
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (cur < str.length()) {
            int end = cur + len <= str.length() ? cur + len : str.length();
            String cut = str.substring(cur, end);
            cur += len;
            sb.append(new StringBuilder(cut).reverse());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String n = in.nextLine();
            String str = in.nextLine();
            System.out.println(solution(str, n));
        }
    }
}
