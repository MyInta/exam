package leetcode_inta.leetcode401_450;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author inta
 * @date 2019/10/6
 * @describe 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，
 * k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class Q406reconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        //排序的时候，按照int[0]身高降序 int[1]数量升序排列
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[people.length][2]);
    }

    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[]o1, int[]o2){
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        LinkedList<int[]> res = new LinkedList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        int[][] queue = new int[people.length][];
        for (int i = 0; i < queue.length; i++) {
            queue[i] = res.get(i);
        }
        return queue;
    }

}
