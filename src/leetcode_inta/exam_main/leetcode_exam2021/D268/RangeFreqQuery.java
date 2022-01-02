package leetcode_inta.exam_main.leetcode_exam2021.D268;

import java.util.*;

/**
 * @author inta
 * @date 2021/11/21
 * @describe
 */
public class RangeFreqQuery {
    private Map<Integer, List<Integer>> countsMap;
    private Map<Integer, int[]> map;

    public RangeFreqQuery(int[] arr) {
        // 标记value索引有哪些
        this.countsMap = new HashMap<>();
        this.map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> val = countsMap.getOrDefault(arr[i], new ArrayList<>());
            val.add(i);
            countsMap.put(arr[i], val);
        }
    }

    // 统计每个元素依次出现的索引信息，在各自的索引数组中找left->right区间内元素数量
    public int query(int left, int right, int value) {
        if (map.containsKey(value)) {
            return findCount(left, right, map.get(value));
        }
        List<Integer> valueIndex = countsMap.getOrDefault(value, new ArrayList<>()); 
        if (valueIndex.size() == 0) {
            return 0;
        }
        int[] indexArr = new int[valueIndex.size()];
        for (int i = 0; i < indexArr.length; i++) {
            indexArr[i] = valueIndex.get(i);
        }
        map.put(value, indexArr);
        return findCount(left, right, indexArr);
    }

    private int findCount(int left, int right, int[] indexArr) {
        // 可以使用二分查找，找第一个大于等于left的索引
        int indexLeft = binaryLeftSearch(left, indexArr);
        // 找第一个大于right的索引
        int indexRight = binaryRightSearch(right, indexArr);
        return indexRight - indexLeft;
    }

    private int binaryRightSearch(int right, int[] indexArr) {
        int l = 0;
        int r = indexArr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (indexArr[mid] <= right) {
                l = mid + 1;
            } else if (indexArr[mid] > right) {
                r = mid;
            }
        }
        return l;
    }

    private int binaryLeftSearch(int left, int[] indexArr) {
        int l = 0;
        int r = indexArr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (indexArr[mid] >= left) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
