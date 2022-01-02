package something.huawei.helper3;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/12
 * @describe
 */
public class Q3 {
    public static String method(List<List<Integer>> list, int dic) {
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = new int[]{list.get(i).get(0), list.get(i).get(1)};
        }
        // 至此获取到了所有目录的映射关系（子->父），可以开始构建关系
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int index = 0;
        // 添加映射
        for (int[] ar : arr) {
            Set<Integer> curSet = map.getOrDefault(ar[1], new HashSet<>());
            curSet.add(ar[0]);
            map.put(ar[1], curSet);
        }
        Set<Integer> allNum = new HashSet<>();
        for (int[] ar : arr) {
            for (int num : ar) {
                allNum.add(num);
            }
        }
        // 至此所有成员关系都已经梳理好，只需要将dic下所有元素清空即可
        Set<Integer> temp = map.getOrDefault(dic, new HashSet<>());
        Deque<Integer> deque = new LinkedList<>();
        if (!temp.isEmpty()) {
            deque.addAll(temp);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int nextInt = deque.poll();
                if (allNum.contains(nextInt)) {
                    allNum.remove(nextInt);
                }
                Set<Integer> nextSet = map.getOrDefault(nextInt, new HashSet<>());
                if (!nextSet.isEmpty()) {
                    deque.addAll(nextSet);
                }
            }
        }
        if (allNum.contains(dic)) {
            allNum.remove(dic);
        }
        if (allNum.contains(0)) {
            allNum.remove(0);
        }
        // 至此都清空了，返回结果即可
        List<Integer> resList = new ArrayList<>(allNum);
        Collections.sort(resList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resList.size() - 1; i++) {
            sb.append(resList.get(i)).append(" ");
        }
        sb.append(resList.get(resList.size() - 1));
        return sb.toString();
    }

/*
5
8 6
10 8
6 0
20 8
2 6
8
*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.valueOf(sc.nextLine());
        List<List<Integer>> list = new ArrayList<>();
        int index = 0;
        while (index < m) {
            String[] curLine = sc.nextLine().split(" ");
            List<Integer> tempList = new ArrayList<>();
            tempList.add(Integer.valueOf(curLine[0]));
            tempList.add(Integer.valueOf(curLine[1]));
            list.add(tempList);
            index++;
        }
        int dic = Integer.valueOf(sc.nextLine());
        System.out.println(method(list, dic));
    }
}
