package something.huawei.helper9;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/27
 * @describe
 */
public class Q2 {
    public static List<String> method(String[] strArr) {
        List<String> res = new ArrayList<>();
        String[] source = strArr[0].split(",");
        String[] deadList = strArr[1].split(",");
        int[] counts = new int[127];
        for (String sou : source) {
            String[] curArr = sou.split(":");
            counts[curArr[0].charAt(0)] += Integer.valueOf(curArr[1]);
        }
        for (String d : deadList) {
            String[] curArr = d.split(":");
            counts[curArr[0].charAt(0)] -= Integer.valueOf(curArr[1]);
        }
        Set<String> visited = new HashSet<>();
        for (String sou : source) {
            String[] curArr = sou.split(":");
            if (visited.contains(curArr[0])) {
                continue;
            }
            visited.add(curArr[0]);
            if (counts[curArr[0].charAt(0)] > 0) {
                res.add(curArr[0] + ":" + counts[curArr[0].charAt(0)]);
            }
        }
        return res;
    }

    // a:3,b:5,c:2@a:1,b:2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strArr = line.split("@");
        if (strArr.length == 1) {
            String[] allStr = strArr[0].split(",");
            for (String str : allStr) {
                System.out.println(str);
            }
        } else {
            List<String> res = method(strArr);
            for (String str : res) {
                System.out.println(str);
            }
        }
    }
}
