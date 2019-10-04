package tencent.leetcode301_350;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/4
 * @describe 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 */
public class Q347topKFrequent {
    //时间nlogn 这个是自己想出来的map+优先队列
    public List<Integer> topKFrequent(int[] nums, int k) {
        //初始化掉省计算量（扩容过程一次搞定）
        List<Integer> res = new ArrayList<>(k);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            //如果不包含，创建
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                //如果有，在原先基础上添加
                map.put(num, map.get(num) + 1);
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Map.Entry entry : map.entrySet()) {
            priorityQueue.add(entry);
        }
        for (int i = 0; i < k; i ++) {
            res.add(priorityQueue.poll().getKey());
        }
        return res;
    }
    //使用桶排
    public List<Integer> topKFrequent2(int[] nums, int k) {
        //初始化掉省计算量（扩容过程一次搞定）
        List<Integer> res = new ArrayList<>(k);
        Map<Integer, Integer> map = new HashMap<>();
        //扩充索引可以是0-nums的数量的范围 0-n
        List<Integer>[] lists = new ArrayList[nums.length + 1];
        for (int num : nums) {
            //如果不包含，创建
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                //如果有，在原先基础上添加
                map.put(num, map.get(num) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //把频率作为桶排的索引
            int index = entry.getValue();
            if (lists[index] == null) {
                lists[index] = new ArrayList<>();
            }
            //把真正的数塞到桶里
            lists[index].add(entry.getKey());
        }
        //然后倒序遍历找出前k频率的数
        for (int i = lists.length - 1; i >= 0 && res.size() < k; i --) {
            //因为桶里存在空的
            if (lists[i] == null ) {
                continue;
            }
            //注释部分方法，效率和下面自己写的一样
//            res.addAll(lists[i]);
            for (int j = 0; j < lists[i].size(); j ++) {
                res.add(lists[i].get(j));
            }
        }
        return res;
    }
}
