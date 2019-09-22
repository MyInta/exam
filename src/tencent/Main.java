package tencent;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list;
        int v = arr[1] - arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] - arr[i] > v) {
                    break;
                } else if (arr[j] - arr[i] < v) {
                    v = arr[j] - arr[i];
                    res.clear();
                    list = new ArrayList<>(2);
                    list.add(arr[i]);
                    list.add(arr[j]);
                    res.add(list);
                    continue;
                }
                list = new ArrayList<>(2);
                list.add(arr[i]);
                list.add(arr[j]);
                res.add(list);
            }
        }
        return res;
    }
}
