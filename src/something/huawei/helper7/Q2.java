package something.huawei.helper7;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/21
 * @describe
 */
public class Q2 {
    public static int method(int num, String[] strArr) {
        int[] tasks = new int[strArr.length];
        int index = 0;
        for (String str : strArr) {
            tasks[index++] = Integer.valueOf(str);
        }
        // 数据量不多，直接暴力解
        Arrays.sort(tasks);
        int[] counts = new int[num];
        int curIndex = 0;
        while (curIndex < tasks.length) {
            counts[curIndex % num] += tasks[curIndex];
            curIndex++;
        }
        int res = 0;
        for (int count : counts) {
            res = Math.max(res, count);
        }
        return res;
    }

    // 提供流水线数量，以及作业情况，作业需要升序，然后依次进行调度，最终返回最长耗时
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.valueOf(sc.nextLine().split(" ")[0]);
        String[] strArr = sc.nextLine().split(" ");
        System.out.println(method(num, strArr));
    }
}
