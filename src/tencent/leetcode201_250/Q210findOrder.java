package tencent.leetcode201_250;

import java.util.LinkedList;

/**
 * @author inta
 * @date 2019/10/22
 * @describe 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 *
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 *
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 *
 */
public class Q210findOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
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
            res[numCourses - 1] = first;
            for (int[] pre : prerequisites) {
                if (pre[1] == first) {
                    degrees[pre[0]]--;
                    //如果该位置结点入度变为零了，就将元素（对应课程编号）入队
                    if (degrees[pre[0]] == 0) {
                        queue.add(pre[0]);
                    }
                }
            }
        }
        if (numCourses == 0) {
            return res;
        } else {
            return new int[0];
        }
    }
}
