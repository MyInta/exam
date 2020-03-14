package leetcode_inta.leetcode201_250;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author inta
 * @date 2019/9/20
 * @describe 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 */
public class Q207Topology_canFinish {
    //整体思路使用拓扑排序，队列辅助，计算出队列个数是否和题目要求课程数量相等
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //设置一个入度数组
        int[] degrees = new int[numCourses];
        //遍历每个数组(每个小数组长度固定为2)，并将所有小数组1号位置的数值对应的入度数组值++
        for (int[] pre : prerequisites) {
            degrees[pre[0]]++;
        }
        //找出所有入度为0的元素，入队，作为前驱节点
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                queue.add(i);
            }
        }
        //当队列有元素的时候，就进行出队列与削减其对应后结点入度值得操作
        while (!queue.isEmpty()) {
            int first = queue.removeFirst();
            //每次出队列后都要把课程数量减1，相当于去掉前驱得同时把范围缩小了
            numCourses--;
            for (int[] pre : prerequisites) {
//                if (pre[1] != first) continue;
//                //如果该位置结点入度变为零了，就将元素（对应课程编号）入队
//                if (--degrees[pre[0]] == 0) {
//                    queue.add(pre[0]);
//                }
                if (pre[1] == first) {
                    degrees[pre[0]]--;
                    //如果该位置结点入度变为零了，就将元素（对应课程编号）入队
                    if (degrees[pre[0]] == 0) {
                        queue.add(pre[0]);
                    }
                }
            }
        }
        return numCourses == 0;
    }

    //看完210题解后，对原先思路的优化(使用了HashSet，寻找映射的元素的时间复杂降为1)，拓扑 连接表 + 入度表
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return false;
        //创建一个连接表
        HashSet<Integer>[] nextNums = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i ++) {
            nextNums[i] = new HashSet<>();
        }
        //创建入度表
        int[] degree = new int[numCourses];
        for (int[] pre : prerequisites) {
            nextNums[pre[1]].add(pre[0]);
            degree[pre[0]] ++;
        }
        //使用一个队列来记录入度0的数值
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++) {
            if (degree[i] == 0) queue.add(i);
        }
        //用来记录出队列了多少个
        int count = 0;
        while (!queue.isEmpty()) {
            Integer temp = queue.pop();
            count ++;
            //将这个出队的队列元素连接的所有元素对应的入度-1
            HashSet<Integer> tempHS = nextNums[temp];
            for (Integer i : tempHS) {
                degree[i] --;
                //如果入度成为0，就加入到队列中
                if (degree[i] == 0) queue.add(i);
            }
        }
        //最后看出队列的数量是否和课程数量一致即可
        return count == numCourses;
    }
}
