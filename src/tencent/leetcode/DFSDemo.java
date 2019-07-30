package tencent.leetcode;

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
 *          Answer: 3
 */
public class DFSDemo {
    private boolean[][] visited;
    private int numRow;
    private int numCol;
    private int total;

    public int numIslands(char[][] grid){
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
                if(!visited[i][j]&&grid[i][j]=='1'){
                    //并且把和这块陆地相连接的陆地全部设为已访问
                    ++total;
                    dps(grid,i,j);
                }
            }
        }
        return total;
    }

    private void dps(char[][] grid, int i, int j) {
        if(i<0||i>=numRow||j<0||j>=numCol||grid[i][j]=='0'||visited[i][j]){
            return;
        }
        visited[i][j] = true;
        dps(grid,i+1,j);
        dps(grid,i-1,j);
        dps(grid,i,j+1);
        dps(grid,i,j-1);
    }


}
