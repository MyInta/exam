package something.huawei.helper9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/27
 * @describe
 */
public class Q3 {
    public static int method(int n, List<String> source, String[] targets) {
        // 数据量未100 * 100，不会超过10000
        final int maxValue = 1001;
        // 制作邻接矩阵
        int[][] adjArr = new int[n + 1][n + 1];
        for (int[] arr : adjArr) {
            Arrays.fill(arr, maxValue);
        }

        for (String str : source) {
            String[] curStr = str.split(" ");
            // u v w
            int[] tempArr = {Integer.valueOf(curStr[0]), Integer.valueOf(curStr[1]), Integer.valueOf(curStr[2])};
            adjArr[tempArr[0]][tempArr[1]] = tempArr[2];
        }
        // 简单实现，不考虑dijstra算法，直接使用floyd算法
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (adjArr[i][k] < maxValue && adjArr[k][j] < maxValue) {
                        adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
                    }
                }
            }
        }
        int[] targetArr = {Integer.valueOf(targets[0]), Integer.valueOf(targets[1])};
        return adjArr[targetArr[0]][targetArr[1]] == maxValue ? -1 : adjArr[targetArr[0]][targetArr[1]];
    }

    // u v w, 输入 n m 然后m行记录 以及源宿节点标号，要求输出最短时延值
    // 3 3
    //1 2 11
    //2 3 13
    //1 3 50
    //1 3
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int n = Integer.valueOf(firstLine[0]);
        int m = Integer.valueOf(firstLine[1]);
        List<String> source = new ArrayList<>();
        while (m > 0) {
            source.add(scanner.nextLine());
            m--;
        }
        String[] targets = scanner.nextLine().split(" ");
        System.out.println(method(n, source, targets));
    }
}
