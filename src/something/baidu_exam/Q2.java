package something.baidu_exam;

import java.util.*;

/**
 * @author inta
 * @date 2020/9/3
 * @describe
 */
public class Q2 {


    private static List<List<Integer>> method(Map<Integer, List<List<Integer>>> map) {
        List<List<Integer>> res = new ArrayList<>();
        for (int key : map.keySet()) {
            List<List<Integer>> value = map.get(key);
            List<Integer> res_one = new ArrayList<>();
            HashSet<Integer> hs = new HashSet<>();
            for (List<Integer> v : value) {
                int from = v.get(0);
                int to = v.get(1);
            }


            res.add(res_one);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        while (in.hasNextLine()) {
            int key = in.nextInt();
            List<List<Integer>> value = new ArrayList<>();
            int cownum = in.nextInt();
            int size = in.nextInt();
            while (size > 0) {
                int temp = in.nextInt();
                for (int i = 0; i < temp; i++) {
                    List<Integer> col = new ArrayList<>();
                    col.add(in.nextInt());
                    col.add(in.nextInt());
                    value.add(col);
                }
                size --;
            }
            map.put(key, value);
        }
        List<List<Integer>> res = method(map);
        for (List<Integer> list : res) {
            System.out.println(list.size());
            for (Integer i : list) {
                System.out.print(i + " ");
            }
        }
    }

}
