package tencent.exam_main.leetcode_exam2020;

/**
 * @author inta
 * @date 2020/1/12
 * @describe
 */
public class D0112_2 {
    //统计1的数量
    public int minFlips(int a, int b, int c) {
        String a_str = Integer.toBinaryString(a);
        String b_str = Integer.toBinaryString(b);
        String c_str = Integer.toBinaryString(c);
        int max = Math.max(Math.max(a_str.length(), b_str.length()), c_str.length());
        int[] counts_ab = new int[max];
        int index = counts_ab.length - 1;
        for (int i = a_str.length() - 1; i >= 0; i --) {
            if (a_str.charAt(i) == '1') counts_ab[index] ++;
            index --;
        }
        index = counts_ab.length - 1;
        for (int i = b_str.length() - 1; i >= 0; i --) {
            if (b_str.charAt(i) == '1') counts_ab[index] ++;
            index --;
        }
//        for (int co : counts_ab) {
//            System.out.print(co + " --");
//            System.out.println();
//        }
        index = counts_ab.length - 1;
        for (int i = c_str.length() - 1; i >= 0; i --) {
            if (c_str.charAt(i) == '1' && counts_ab[index] != 0) {
                counts_ab[index] = 0;
            } else if (c_str.charAt(i) == '1' && counts_ab[index] == 0) {
                counts_ab[index] ++;
            }
            index --;
        }
        int res = 0;
        for (int count : counts_ab) {
            res += count;
        }
        return res;
    }
}
