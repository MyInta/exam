package leetcode_inta.exam_main.leetcode_exam2020.D197;

import java.util.*;

/**
 * @author inta
 * @date 2020/7/12
 * @describe
 */
public class Q3 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        //建立关系圈
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        for (int[] edge : edges) {
            merge(edge[0], edge[1]);
        }
        //已经建立好关系圈了
        if (find(start) != find(end)) return 0;
        Queue<Pair> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        set.add(start);
        queue.add(new Pair(start, 1));
        double res = 0;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.v == end) res = Math.max(res, p.c);
            for (int i = 0; i < edges.length; i++) {
                int[] edge = edges[i];
                if (!set.contains(edge[1]) && edge[0] == p.v) {
                    queue.add(new Pair(edge[1], p.c * succProb[i]));
                    set.add(edge[1]);
                } else if (!set.contains(edge[0]) && edge[1] == p.v) {
                    queue.add(new Pair(edge[0],p.c * succProb[i]));
                    set.add(edge[0]);
                }
            }
        }
        return res;
    }
    private class Pair{
        int v;
        double c;
        Pair(int v, double c) {
            this.v = v;
            this.c = c;
        }
    }
    //总共有多少个帮派成员，就预设有多少个帮派，到时候再火并
    private int[] pre;
    //查找n这个位置的最终BOSS，并返回最终Boss，期间路径压缩，将每个点指向其上级
    private int find(int n) {
        int child, temp;
        child = n;
        //当查找的这个好汉他不是boss，我们去找他的boss
        while (n != pre[n]) {
            //他总能找到一个等于自身的时候，也就是帮派boss出现的时候
            n = pre[n];
        }
        //至此，n已经是帮派boss了，需要路径压缩下,把小兵罗罗都归于boss直属
        while (child != n) {
            //记录下原先小罗罗的上级
            temp = pre[child];
            //让这个上级转向boss，以后他跟着大boss干了，和之前的老领导说拜拜
            pre[child] = n;
            //然后我们去遍历原先的老领导，重复操作，让他也由boss直接领导
            child = temp;
        }
        //之前说过，此时的n已经是boss了
        return n;
    }

    //融合两个帮派，不用计较谁来当老大，反正目的是火并减少帮派数量
    private void merge(int child1, int child2) {
        int x, y;
        //分别找两家的最终boss
        x = find(child1);
        y = find(child2);
        //如果boss不一样，火并
        if (x != y) {
            pre[x] = y;
        }
    }
}
