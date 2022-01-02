package something.huawei.helper5;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/18
 * @describe
 */
public class Q2 {
    public static List<String> method(String[] strArr) {
        List<String> res = new ArrayList<>();
        String[] dic = strArr[0].split(",");
        String[] visited = strArr[1].split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String vis : visited) {
            String[] curArr = vis.split(":");
            map.put(curArr[0], map.getOrDefault(curArr[0], 0) + Integer.valueOf(curArr[1]));
        }
        for (String d : dic) {
            String[] curArr = d.split(":");
            if (map.containsKey(curArr[0]) && Integer.valueOf(curArr[1]) <= map.get(curArr[0])) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(curArr[0]).append(":").append(Integer.valueOf(curArr[1]) - map.getOrDefault(curArr[0], 0));
            res.add(sb.toString());
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
