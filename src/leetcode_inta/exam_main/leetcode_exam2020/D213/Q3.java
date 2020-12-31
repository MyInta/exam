package leetcode_inta.exam_main.leetcode_exam2020.D213;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/11/1
 * @describe
 */
public class Q3 {

    //滑动窗口，当前差距内的最大值数量限定在ladders内，查看剩余值能否在bricks内，不能就缩减
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        //使用集合保存上升空间索引信息，以及差值
        List<Integer> index = new ArrayList<>();
        List<Integer> dif = new ArrayList<>();
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[i - 1]) {
                index.add(i);
                dif.add(heights[i] - heights[i - 1]);
            }
        }
        if (dif.size() <= ladders) return heights.length - 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a-b);
        for (int k = 0; k < ladders; k++) {
            pq.add(dif.get(k));
        }
        int count = 0;
        for (int j = ladders; j < dif.size(); j++) {
            int v = dif.get(j);
            if (!pq.isEmpty() && pq.peek() < v) {
                count += pq.poll();
                pq.add(v);
            } else {
                count += v;
            }
            if (count > bricks) return index.get(j) - 1;
        }
        return heights.length - 1;
    }

}
