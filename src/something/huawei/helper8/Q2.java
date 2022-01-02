package something.huawei.helper8;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author inta
 * @date 2021/12/23
 * @describe
 */
public class Q2 {
    public static String method(String[] memoDic, String[] applyList) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (String memo : memoDic) {
            String[] memos = memo.split(":");
            int[] keyAndVal = new int[2];
            keyAndVal[0] = Integer.valueOf(memos[0]);
            keyAndVal[1] = Integer.valueOf(memos[1]);
            treeMap.put(keyAndVal[0], treeMap.getOrDefault(keyAndVal[0], 0) + keyAndVal[1]);
        }
        int[] applyNums = new int[applyList.length];
        int index = 0;
        for (String apply : applyList) {
            applyNums[index++] = Integer.valueOf(apply);
        }
        StringBuilder sb = new StringBuilder();
        for (int num : applyNums) {
            Map.Entry<Integer, Integer> entry = treeMap.ceilingEntry(num);
            if (entry != null) {
                sb.append("true");
                if (entry.getValue() == 1) {
                    treeMap.remove(entry.getKey());
                } else {
                    treeMap.put(entry.getKey(), entry.getValue() - 1);
                }
            } else {
                sb.append("false");
            }
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    // 64:2,128:1,32:4,1:128
    // 50,36,64,128,127
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] memoDic = sc.nextLine().split(",");
        String[] applyList = sc.nextLine().split(",");
        System.out.println(method(memoDic, applyList));
    }
}
