package tencent.exam_main.leetcode_exam2020.D179;

import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/8
 * @describe
 */
public class Q2 {

    public int numTimesAllBlue(int[] light) {
        //使用栈，当最大值和栈尺寸一致时，res++
        List<Integer> list = new LinkedList<>();
        int res = 0;
        int max = 0;
        for (int i : light) {
            if (i > max) max = i;
            list.add(i);
            if (max == list.size()) res ++;
        }
        return res;
    }

}
