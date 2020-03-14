package leetcode_inta.leetcode101_150;


import java.util.ArrayList;

/**
 * @author inta
 * @date 2019/7/31
 * @describe 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class Q130Surroundedarea {

    public void solve(char[][] board) {
        if(board==null||board.length==0||board[0].length==0){
            return;
        }
        int n = board.length;
        int m = board[0].length;
        for(int i = 0;i < n;i++){
            island(board,i,0,n,m);
            island(board,i,m-1,n,m);
        }
        for(int j= 1;j< m-1;j++){
            island(board,0,j,n,m);
            island(board,n-1,j,n,m);
        }
        for(int i =0;i<n;i++){
            for(int j =0;j<m;j++){
                //如果是O且没有被访问过，说明是孤岛的一员，需要修改为X
                if(board[i][j]=='-'){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }

    private void island(char[][] board,int i,int j,int n,int m){
        //越界和非O的都返回
        if(i<0||i>=n||j<0||j>=m||board[i][j]=='-'||board[i][j]=='X'){
            return;
        }
        //标记与边界O相连的O的位置
        board[i][j] = '-';
        island(board,i+1,j,n,m);
        island(board,i-1,j,n,m);
        island(board,i,j+1,n,m);
        island(board,i,j-1,n,m);
    }



    public static void main(String[] args) {
        Q130Surroundedarea q130Surroundedarea = new Q130Surroundedarea();
        char[][] c = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        q130Surroundedarea.solve(c);
        for(int i=0;i<c.length;i++){
            for(int j = 0;j<c[0].length;j++){
                System.out.print(c[i][j]+" ");
            }
            System.out.println();
        }
    }


    class MyTry{
        private boolean[][] visited;
        public void solve(char[][] board) {
            if(board==null||board.length==0||board[0].length==0){
                return;
            }
            int n = board.length;
            int m = board[0].length;
            visited = new boolean[n][m];
            flag = new boolean[n][m];
            for(int i = 0;i < n;i++){
                for(int j = 0;j < m;j++){
                    if(!visited[i][j]){
                        //重置arr数组和temp碰撞判断
                        arr = new ArrayList<>();
                        temp = false;
                        island(board,i,j,n,m,arr);
                        if(!temp){
                            //如果不存在过碰撞到O，遍历数组内的元素都为孤岛
                            for(Pair pair:arr){
                                board[pair.getN()][pair.getM()] = 'X';
                            }
                        }
                    }
//                if(!flag[i][j]&&visited[i][j]&&board[i][j] == 'O'){
//                        board[i][j] = 'X';
//                }
                }
            }

        }

        //默认为false表示为孤岛，true表示该区域有O触及到边界了
        private boolean[][] flag;
        private ArrayList<Pair> arr;
        //设置一个temp记录是否存在过边界碰撞为O的现象
        private boolean temp;
        private void island(char[][] board,int i,int j,int n,int m,ArrayList arrayList){
            if(i<0||i>=n||j<0||j>=m||visited[i][j]||board[i][j]=='X'){
                return;
            }
            if(i==0||i==n-1||j==0||j==m-1){
                if(board[i][j] == 'O'){
                    temp = true;
                }
            }
            visited[i][j] = true;
            //TODO 删除测试
            if(board[i][j]=='O'){
                arrayList.add(new Pair(i,j));
            }
            island(board,i+1,j,n,m,arrayList);
            island(board,i-1,j,n,m,arrayList);
            island(board,i,j+1,n,m,arrayList);
            island(board,i,j-1,n,m,arrayList);
        }

        class Pair{
            int n;
            int m;
            public Pair(int n, int m) {
                this.n = n;
                this.m = m;
            }
            public int getN() {
                return n;
            }
            public int getM() {
                return m;
            }
        }
    }
}
