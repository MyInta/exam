package something.baidu_exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2020/9/3
 * @describe
 */
public class Q1 {

    private static String solution(int n, List<Integer> nums) {
        long count_five = 0;
        for (int num : nums) {
            if (num == 5) count_five++;
        }
        long count_zero = n - count_five;
        long resume = count_five / 9;
        if (resume == 0 || count_zero == 0) return "-1";
        resume = 9 * resume;
        StringBuilder res = new StringBuilder();
        while (resume > 0) {
            res.append("5");
            resume --;
        }
        while (count_zero > 0) {
            res.append("0");
            count_zero --;
        }
        return res.toString();
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(in.nextInt());
        }
        System.out.println(solution(n, nums));
    }


}
