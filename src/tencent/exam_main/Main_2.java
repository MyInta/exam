package tencent.exam_main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author inta
 * @date 2019/9/21
 * @describe 数组每两个为一组，要求各子组和间差值最为接近，输入为数组元素数量 和数组元素，首行代表有几行要输入
 *          如 4
 *             1 2
 *             2 4
 *             1 5
 *             2 6
 *             代表为 2 4 4 5 6 6，并且默认元素总数目是偶数个，然后两两分组求其和在满足题干下的最大值
 *             输出 10
 */
public class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res = 0;
        int row_num = in.nextInt();
        int m = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < row_num; i++) {
            int n = in.nextInt();
            int t = in.nextInt();
            m += n;
            for (int j = 0; j < n; j++) {
                arrayList.add(t);
            }
        }
        int person_num = m>>1;
        int[] nums = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            nums[i] = arrayList.get(i);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length >> 1; i++) {
            int temp = nums[i] + nums[nums.length-1-i];
            res = Math.max(res, temp);
        }
        System.out.println(res);
    }
}
