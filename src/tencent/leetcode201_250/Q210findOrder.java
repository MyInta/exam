package tencent.leetcode201_250;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
    //拓扑，使用邻接表和入度表
    public int[] findOrder(int numCourses, int[][] prerequisites) {
       //去除边角
        if (numCourses <= 0) return new int[0];
        //创建邻接表
        HashSet<Integer>[] nextCourses = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i ++) {
            nextCourses[i] = new HashSet<>();
        }
        //创建入度表
        int[] degree = new int[numCourses];
        for (int[] pre : prerequisites) {
            //给邻接表创建 元素与元素之间的连接关系
            nextCourses[pre[1]].add(pre[0]);
            //入度表增加每个节点的入度值
            degree[pre[0]] ++;
        }
        //需要一个队列来记录入度为0的情况
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++) {
            if (degree[i] == 0) {
                queue.addLast(i);
            }
        }
        //需要一个数组来记录出队的值
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            //获得到队列里面为0的元素，并且消减队列
            int temp = queue.removeFirst();
            //记录出队的元素
            list.add(temp);
            //获得该元素对应的连接元素储存单元
            HashSet<Integer> tempHashSet = nextCourses[temp];
            //给这些元素都减度
            for (Integer i : tempHashSet) {
                degree[i] --;
                //如果减到0，立即入队
                if (degree[i] == 0) queue.addLast(i);
            }
        }
        //判断，如果出队的元素长度和课程数相等，说明可以实现，反之说明不能实现
        if (list.size() == numCourses) {
            int[] res = new int[numCourses];
            int res_index = 0;
            for (int i : list) {
                res[res_index ++] = i;
            }
            return res;
        } else {
            return new int[0];
        }
    }
}
