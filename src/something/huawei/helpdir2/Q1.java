package something.huawei.helpdir2;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/11
 * @describe
 */
public class Q1 {
    // 字符有一定得顺序性
    public static String[] method(String[] strArr) {
        // 给牌一个和数组得映射关系，再使用双指针找
        String[] source = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
//            System.out.println("put进去:" + source[i]);
            map.put(source[i], i);
        }
        int[] counts = new int[12];
        for (String str : strArr) {
            if (!map.containsKey(str)) {
                continue;
            }
//            System.out.println("当前str：" + str);
            counts[map.get(str)]++;
        }
        // 至此统计出每张牌的数量，然后双指针来求
        List<String> res = new ArrayList<>();
        int left = 0;
        int right = 0;
        while (left < counts.length - 4) {
            while (right < counts.length && counts[right] > 0) {
                right++;
            }
            // 观察是否满足要求
            if (right - left < 5) {
                left = right + 1;
                right = left;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = left; i < right; i++) {
                    sb.append(source[i]).append(" ");
                    counts[i]--;
                }
                res.add(String.valueOf(sb).trim());
                right = left;
            }
        }
        if (res.size() == 0) {
            String[] resStr = new String[1];
            resStr[0] = "No";
            return resStr;
        } else {
            String[] resStr = new String[res.size()];
            for (int i = 0; i < res.size(); i++) {
                resStr[i] = res.get(i);
            }
            Arrays.sort(resStr);
            return resStr;
        }
    }

    // 有顺子输出，可能输出多个，没有就输出“NO”
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] res = method(line.split(" "));
        for (String re : res) {
            System.out.println(re);
        }
    }
}
