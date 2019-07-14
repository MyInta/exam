package tencent.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/7/9
 * @describe 找出孤立的小岛数（上下左右连接的为一个岛,1陆地，0水）
 *  example 1:
 *          11110
 *          11010
 *          11000
 *          00000
 *          Answer: 1
 *  example 2:
 *          11000
 *          11000
 *          00100
 *          00011
 *          Answer :3
 */
public class BFSDemo {
    private boolean[][] visited;
    private int numRow;
    private int numCol;
    private int total;

    public int numIsland(char[][] grid){
        total = 0;
        //validation input
        if(grid==null||grid.length<=0||grid[0].length<=0){
            return total;
        }
        numRow = grid.length;
        numCol = grid[0].length;
        visited = new boolean[numRow][numCol];
        //search for entry point
        for(int i=0;i<numRow;++i){
            for(int j=0;j<numCol;++j){
                //找到未访问过的陆地，即为小岛
                if(isNewLand(grid,i,j)){
                    //并且把和这块陆地相连接的陆地全部设为已访问
                    ++total;
                    bfs(grid,i,j);
                }
            }
        }
        return total;
    }

    private boolean isNewLand(char[][] grid, int i, int j) {
        //未访问过，且数值为1陆地的，即新陆地
        return !visited[i][j]&&grid[i][j]=='1';
    }

    /**
     * 最主要目的就是将一次访问到的陆地附近连接地，排除同一块陆地上未访问信息
     * @param grid
     * @param i
     * @param j
     */
    private void bfs(char[][] grid, int i, int j) {
        //使用一个Queue将一层一层坐标保存下来，然后先进先出原则，即层层遍历
        visited[i][j] = true;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i,j));
        Pair curIdx;
        while(!queue.isEmpty()){
            curIdx = queue.poll();
            int cw = curIdx.row;
            int cc = curIdx.col;
            //>0和<xx-1是考虑该点左右上下有没有格子了
            if(cw>0&&isNewLand(grid,cw-1,cc)){
                queue.offer(new Pair(cw-1,cc));
                visited[cw-1][cc] = true;
            }
            if(cw<numRow-1&&isNewLand(grid,cw+1,cc)){
                queue.offer(new Pair(cw+1,cc));
                visited[cw+1][cc] = true;
            }
            if(cc>0&&isNewLand(grid,cw,cc-1)){
                queue.offer(new Pair(cw,cc-1));
                visited[cw][cc-1] = true;
            }
            if(cc<numCol-1&&isNewLand(grid,cw,cc+1)){
                queue.offer(new Pair(cw,cc+1));
                visited[cw][cc+1] = true;
            }
        }

    }

    /**
     * 构建一个节点类，用以给Queue保存
     */
    public class Pair{
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
