package leetcode_inta.exam_main.leetcode_exam2020.D177;

/**
 * @author inta
 * @date 2020/2/23
 * @describe
 */
public class Q4 {
    public String largestMultipleOfThree(int[] digits) {
        int[] counts = new int[10];
        int sum = 0;
        for (int digit : digits) {
            counts[digit] ++;
            sum += digit;
        }
        if (sum % 3 == 0) {
            return build(counts);
        } else if (sum % 3 == 1) {
            //找存在的余数为1的一个数去除，若不存在则去除余数为2的两个数
            for (int i = 1; i < 8; i += 3) {
                if (counts[i] > 0) {
                    counts[i] --;
                    return build(counts);
                }
            }
            boolean del = false;
            for (int i = 2; i < 9; i += 3) {
                if (counts[i] > 0) {
                    if (counts[i] > 1) {
                        counts[i] -= 2;
                        return build(counts);
                    } else {
                        counts[i] --;
                        if (del) {
                            //如果是之前删过一次，这是第二次删除了，符合要求直接返回
                            return build(counts);
                        }
                        del = true;
                    }
                }
            }
        } else {
            //此时余数为2，去除余数为2的一个数，不存在考虑去除余数为1的两个数
            for (int i = 2; i < 9; i += 3) {
                if (counts[i] > 0) {
                    counts[i] --;
                    return build(counts);
                }
            }
            boolean del = false;
            for (int i = 1; i < 8; i += 3) {
                if (counts[i] > 0) {
                    if (counts[i] > 1) {
                        counts[i] -= 2;
                        return build(counts);
                    } else {
                        counts[i] --;
                        if (del) {
                            //如果是之前删过一次，这是第二次删除了，符合要求直接返回
                            return build(counts);
                        }
                        del = true;
                    }
                }
            }
        }
        return "";
    }
    private String build(int[] counts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i --) {
            int num = counts[i];
            if (num > 0) {
                while (num > 0) {
                    sb.append(i);
                    num --;
                }
            }
        }
        String str = sb.toString();
        return str.startsWith("0") ? "0" : str;
    }
}
