package something.huawei;

import java.util.*;

/**
 * @author inta
 * @date 2020/11/8
 * @describe
 */
public class Q3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<int[]> list = new ArrayList<>();
        while (sc.hasNextInt()) {
            int[] new_line = new int[2];
            new_line[0] = sc.nextInt();
            new_line[1] = sc.nextInt();
            list.add(new_line);
        }
        //此时获得一个数组集合list，遍历之，以获取交集
        Set<int[]> dic = new HashSet<>();
        Set<int[]> visited = new HashSet<>();
        for (int i = 0; i < list.size() - 1; i++) {
            int[] arr1 = list.get(i);
            if (visited.contains(arr1)) break;
            for (int j = i + 1; j < list.size(); j++) {
                int[] arr2 = list.get(j);
                if (arr1[0] > arr2[1] || arr1[1] < arr2[0]) continue;
                int[] new_temp = new int[2];
                new_temp[0] = Math.max(arr1[0], arr2[0]);
                new_temp[1] = Math.min(arr1[1], arr2[1]);
//                System.out.println(new_temp[0] + "----" + new_temp[1]);
                dic.add(new_temp);
            }
        }
        if (dic.size() == 0) System.out.println("None");
        //此时获得所有交集dic，合并之
        List<int[]> res = solution(dic);
        //输出
        for (int[] r : res) {
            System.out.println(r[0] + " " + r[1]);
        }
    }

    private static List<int[]> solution(Set<int[]> set) {
        List<int[]> res = new ArrayList<>();
        if (set.size() == 0) return res;
        int[][] map = new int[set.size()][];
        int index = 0;
        for (int[] s : set) {
            map[index++] = s;
        }
        Arrays.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int left = map[0][0], right = map[0][1];
        for (int i = 1; i < map.length; i++) {
//            System.out.println(map[i][0] +"----"+ map[i][1]);
            if (map[i][0] > right) {
                int[] temp = new int[2];
                temp[0] = left;
                temp[1] = right;
                res.add(temp);
                left = map[i][0];
                right = map[i][1];
            } else {
                right = map[i][1];
            }
        }
        int[] temp = new int[2];
        temp[0] = left;
        temp[1] = right;
        res.add(temp);
        return res;
    }
}
