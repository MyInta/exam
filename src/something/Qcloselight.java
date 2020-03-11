package something;

/**
 * @author inta
 * @date 2020/3/1
 * @describe
 */
public class Qcloselight {

    public int getMax(int n, int m, int[][] grids) {
        //先找小明工位坐标
        int x = -1,y = -1;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grids[i][j] == 3) {
                    x = i;
                    y = j;
                }
            }
        }
        //如果连工位都莫得，那就没有路径可言
        if (x == -1) return -1;
        //从工位开始bfs考虑
        bfs(x, y, grids, n, m, 0);
        return res;
    }
    //统计能顺利回家前提下关掉的灯数量
    private int res = -1;
    //用来记录关过灯的信息
    private void bfs(int x, int y, int[][] grid,int n, int time, int count) {
        if (x < 0 || x >= n || y < 0|| y >= n || grid[x][y] == 1 || time < 0) return;
        //如果遍历到出口，就是进行结算，考虑回家
        if (grid[x][y] == 4) {
            res = Math.max(res, count);
            return;
        }
        //该位置认为已经关灯过，标记下，防止重复访问
        boolean changed = false;
        //遇见灯，关了个，数量加一下
        if (grid[x][y] == 2) {
            count ++;
            grid[x][y] = 0;
            changed = true;
        }
        //bfs四周，考虑时间消耗一格
        bfs(x - 1, y, grid, n, time - 1, count);
        bfs(x + 1, y, grid, n, time - 1, count);
        bfs(x, y - 1, grid, n, time - 1, count);
        bfs(x, y + 1, grid, n, time - 1, count);
        if (changed) grid[x][y] = 2;
    }

    public static void main(String[] args) {
        Qcloselight q = new Qcloselight();
        int n1 = 3;
        int m1 = 10;
        int[][] grids1 = {{3,1,2},{0,1,0},{0,1,4}};
        int n2 = 4;
        int m2 = 3;
        int[][] grids2 = {{0,0,0,0},{0,0,0,2},{0,0,4,0},{0,0,0,3}};
        int n3 = 5;
        int m3 = 8;
        int[][] grids3 = {{0,0,0,0,0},{0,3,0,2,0},{0,0,2,0,0},{0,0,0,0,0},{0,0,0,0,4}};
        int n4 = 5;
        int m4 = 10;
        int[][] grids4 = {{0,0,0,0,0},{0,1,1,1,1},{0,1,2,1,1},{4,0,2,3,1},{0,0,2,0,1}};
        int res1 = q.getMax(n1, m1, grids1);
        int res2 = q.getMax(n2, m2, grids2);
        int res3 = q.getMax(n3, m3, grids3);
        int res4 = q.getMax(n4, m4, grids4);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
    }
}
