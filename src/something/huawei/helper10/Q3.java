package something.huawei.helper10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2022/1/8
 * @describe
 */
public class Q3 {
    private static int[] parents;

    public static void method(List<List<String>> list, int n, int m) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (List<String> curList : list) {
            int[] curArr = new int[3];
            for (int i = 0; i < 3; i++) {
                curArr[i] = Integer.valueOf(curList.get(i));
            }
            if (curArr[0] < 1 || curArr[0] > n || curArr[1] < 1 || curArr[1] > n) {
                System.out.println("da pian zi");
                continue;
            }
            if (curArr[2] == 0) {
                int pA = parents[curArr[0]];
                int pB = parents[curArr[1]];
                parents[pA] = pB;
            } else if (curArr[2] == 1) {
                if (find(curArr[0]) == find(curArr[1])) {
                    System.out.println("we are a team");
                } else {
                    System.out.println("we are not a team");
                }
            } else {
                System.out.println("da pian zi");
            }
        }
    }

    private static int find(int child) {
        if (parents[child] == child) {
            return child;
        }
        return find(parents[child]);
    }

    // 5 6
    //1 2 0
    //1 2 1
    //1 3 0
    //2 3 1
    //2 3 1
    //1 3 2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] firstLine = sc.nextLine().split(" ");
        int n = Integer.valueOf(firstLine[0]);
        int m = Integer.valueOf(firstLine[1]);
        List<List<String>> list = new ArrayList<>();
        while (m > 0) {
            List<String> tempList = new ArrayList<>();
            String[] next = sc.nextLine().split(" ");
            for (String str : next) {
                tempList.add(str);
            }
            list.add(tempList);
            m--;
        }
        method(list, n, m);
    }
}
