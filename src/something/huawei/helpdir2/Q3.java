package something.huawei.helpdir2;

import java.lang.annotation.Target;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/11
 * @describe
 */
public class Q3 {
    public static int method(int R, String[] arr, int limit) {
        // 获取数据通道尺寸、数量，然后尽可能小的安排出limit，最后返回可以安排的数量
        int[] counts = new int[R + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[i] = Integer.valueOf(arr[i]);
        }
        int[] values = new int[R + 1];
        for (int i = 0; i <= R; i++) {
            values[i] = 1 << i;
        }
        // 问题在于怎么从counts中取合理的值？凡是大于limit的value直接统计为数字即可
        int copyLimit = limit;
        int index = findMaxIndex(copyLimit);

        // index以右直接统计数量即可
        int res = 0;
        for (int i = index + 1; i < counts.length; i++) {
            res += counts[i];
        }
        // 难题是剩余的怎么取,按照最大最小组合，limit减去较大的value，剩下的从次value获取
        int target = limit;
        while (true) {
            int curIndex = findMaxIndex(target);
            int curTimes = 2;
            while (curTimes > 0 && curIndex >= 0) {
                if (counts[curIndex] > 0) {
                    curTimes--;
                    counts[curIndex]--;
                    target -= values[curIndex];
                    curIndex = findMaxIndex(target);
                } else {
                    curTimes *= 2;
                    curIndex--;
                }

            }
            if (target <= 0) {
                res++;
                target = limit;
            } else {
                break;
            }
        }
        return res;
    }

    private static int findMaxIndex(int target) {
        int index = 0;
        while (target > 1) {
            index++;
            target >>= 1;
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int R = Integer.valueOf(line);
        String nextLine = sc.nextLine();
        String[] nums = nextLine.split(" ");
        Integer limit = Integer.valueOf(sc.nextLine());
        System.out.println(method(R, nums, limit));
    }
}
