package leetcode_inta.leetcode501_550;

/**
 * @author inta
 * @date 2019/12/11
 * @describe 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，
 * 表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 示例 1:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 *
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 */
public class Q547findCircleNum {
    //类似岛屿问题，dfs以及存储遍历过对象的boolean[]即可
    public int findCircleNum(int[][] M) {
        int m = M[0].length;
        visited = new boolean[m];
        int res = 0;
        //只要考虑第一行的情况即可,深入下去可以包含了所有人信息
        for (int i = 0; i < m; i ++) {
            if (!visited[i]) {
                res ++;
                dfs(M, m, i);
            }
        }
        return res;
    }
    private boolean[] visited;
    private void dfs(int[][] M, int m, int i) {
        for (int j = 0; j < m; j ++) {
            if (!visited[j] && M[i][j] == 1) {
                visited[j] = true;
                //第几个列发现新值，就遍历该列同值的行，因为是正方形
                dfs(M, m, j);
            }
        }
    }

    // 1 0 0 1
    // 0 1 1 0
    // 0 1 1 1
    // 1 0 1 1

    //大神用的是并查集解法，我也试试   牛逼，效率杠杠的！
    public int findCircleNum2(int[][] M) {
        int m = M[0].length;
        //每一列都可以理解为一个成员，所以预先设帮派数量m个，也可以为行数，正方形，效果一样
        pre = new int[m];
        //帮派数量假定为m个
        count = m;
        for (int i = 0; i < m; i ++) {
            //很早以前，我们以为这些小伙都是自己的领导(自己是boss)
            pre[i] = i;
        }
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < i; j ++) {
                //i j希望打一架后火并为同一帮派的信号，将其合并
                if (M[i][j] == 1) {
                    merge(i, j);
                }
            }
        }
        return count;
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
    //用来记录帮派数量
    private int count;
    //融合两个帮派，不用计较谁来当老大，反正目的是火并减少帮派数量
    private void merge(int child1, int child2) {
        int x, y;
        //分别找两家的最终boss
        x = find(child1);
        y = find(child2);
        //如果boss不一样，火并
        if (x != y) {
            pre[x] = y;
            //每一次的火并，就有一个假定的帮派火并，数量-1
            count --;
        }
    }

}
