package leetcode_inta.exam_main.leetcode_exam2020.D176;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/16
 * @describe
 */
public class D2 {




}
class ProductOfNumbers {

    private List<Integer> sum;
    public ProductOfNumbers() {
        sum = new ArrayList<>();
    }

    public void add(int num) {
        sum.add(num);
//        System.out.println(sum.get(sum.size() - 1));
    }

    public int getProduct(int k) {
        int res = 1;
        for (int i = sum.size() - 1; i >= sum.size() - k; i --) {
            res *= sum.get(i);
            if (res == 0) return 0;
        }
        return res;
    }
}