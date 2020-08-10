package leetcode_inta.exam_main.leetcode_exam2020.D199;

/**
 * @author inta
 * @date 2020/7/26
 * @describe
 */
public class Q4 {
    //感觉难点，在于落单统计，10 100 1000这些情况长度下的统计
    public int getLengthOfOptimalCompression(String s, int k) {
        //统计压缩过程中，落单数，10位数以及100位数
        int count_one = 0, count_10 = 0, count_100 = 0;
        //先统计压缩后长度
        int compress_length = s.length() == 1 ? 1 : 0;
        int[] counts = new int[101];
        char temp = s.charAt(0);
        int count_cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != temp) {
                if (count_cur == 1) {
                    count_one++;
                } else if (count_one == 10) {
                    count_10++;
                } else if (count_one == 100) {
                    count_100++;
                }
                if (count_cur < 10) {
                    compress_length += 2;
                } else if (count_cur < 100) {
                    compress_length += 3;
                } else {
                    compress_length += 4;
                }
                //等优先队列时候用
                counts[count_cur]++;
                temp = s.charAt(i);
                count_cur = 1;
            } else {
                count_cur++;
            }
        }
        if (count_cur == 1) {
            count_one++;
        } else if (count_one == 10) {
            count_10++;
        } else if (count_one == 100) {
            count_100++;
        }
        if (count_cur < 10) {
            compress_length += 2;
        } else if (count_cur < 100) {
            compress_length += 3;
        } else {
            compress_length += 4;
        }
        //等优先队列时候用
        counts[count_cur]++;
        System.out.println(count_one + "--" + count_10 + "--" + count_100);
        if (k >= count_one) {
            compress_length -= count_one;
            k -= count_one;
        } else {
            compress_length -= k;
            return compress_length;
        }
        if (k >= count_10) {
            compress_length -= count_10;
            k -= count_10;
            counts[10] = 0;
            counts[9] += count_10;
        } else {
            compress_length -= k;
            return compress_length;
        }
        if (k >= count_100) {
            compress_length -= count_100;
            k -= count_100;
        } else {
            compress_length -= k;
            return compress_length;
        }
        for (int i = 2; i < 100; i++) {
            while (k >= i && counts[i] > 0) {
                k -= i;
                counts[i] --;
                compress_length -= (i >= 10 ? 3 : 2);
            }
            if (k < i) break;
        }
        return compress_length;
    }
}
