package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/7/30
 * @describe 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，
 * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 */
public class Q200NumIsland {

    public int res;
    public boolean[][] isvisited;
    public int numIslands(char[][] grid) {
        res = 0;
        if(grid==null||grid.length==0||grid.length==0){
            return res;
        }
        int n = grid.length;
        int m = grid[0].length;
        isvisited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!isvisited[i][j]&&grid[i][j]=='1'){
                    res++;
                    solve(i,j,grid,n,m);
                }
            }
        }
        return res;
    }
    private void solve(int row,int col,char[][] grid,int n,int m){
        if(row>=0&&row<n&&col>=0&&col<m&&!isvisited[row][col]&&grid[row][col]=='1'){
            isvisited[row][col] = true;
            solve(row+1,col,grid,n,m);
            solve(row-1,col,grid,n,m);
            solve(row,col+1,grid,n,m);
            solve(row,col-1,grid,n,m);
        }else{
            return;
        }
    }
}
