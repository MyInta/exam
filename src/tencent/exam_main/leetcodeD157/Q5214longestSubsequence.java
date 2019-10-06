package tencent.exam_main.leetcodeD157;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/6
 * @describe 给你一个整数数组 arr 和一个整数 difference，
 * 请你找出 arr 中所有相邻元素之间的差等于给定 difference 的等差子序列，并返回其中最长的等差子序列的长度。
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 *
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 *
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 */
public class Q5214longestSubsequence {
    //hashmap记录当前值减去差值后的值，并且将长度信息作为val传递下去
    public int longestSubsequence(int[] arr, int difference) {
        int res = 0;
        if (arr.length == 0) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            int val = 1;
            if (!map.containsKey(i - difference)) {
                map.put(i, val);
            } else {
                val += map.get(i - difference);
                map.put(i, val);
            }
            res = Math.max(res, val);
        }
        return res;
    }
//    public int longestSubsequence(int[] arr, int difference) {
//        int len = arr.length;
//        HashSet<Integer> hs = new HashSet<>();
//        int res = 0;
//        for (int i = 0; i < len; i ++) {
//            while (!hs.contains(arr[i])) {
//                int pre = i;
//                hs.add(arr[i]);
//                int count = 1;
//                for (int j = i + 1; j < len; j ++) {
//                    if (arr[j] - arr[pre] == difference) {
//                        count ++;
//                        hs.add(arr[j]);
//                        pre = j;
//                    }
//                }
//                res = Math.max(res, count);
//            }
//        }
//        return res;
//    }
}
