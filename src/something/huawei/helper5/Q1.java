package something.huawei.helper5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/18
 * @describe
 */
public class Q1 {
    public static List<String> method(List<String> list) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String curStr = list.get(i);
            StringBuilder sb = new StringBuilder();
            int[] arr = {1, 2, 4};
            int index = 0;
            int moveValue;
            while (index < curStr.length()) {
                if (index > 2) {
                    int temp = arr[0] + arr[1] + arr[2];
                    arr[0] = arr[1];
                    arr[1] = arr[2];
                    arr[2] = temp % 26;
                    moveValue = arr[2];
                } else {
                    moveValue = arr[index];
                }
                sb.append((char)('a' + (curStr.charAt(index) - 'a' + moveValue) % 26));
                index++;
            }
            res.add(sb.toString());
        }
        return res;
    }

    // 前三为固定偏移量1 2 4，后面为前三累加值
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = Integer.valueOf(sc.nextLine());
        List<String> list = new ArrayList<>();
        while (times > 0) {
            list.add(sc.nextLine());
            times--;
        }
        List<String> res = method(list);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
