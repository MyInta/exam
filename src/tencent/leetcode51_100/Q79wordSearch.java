package tencent.leetcode51_100;

/**
 * @author inta
 * @date 2019/8/23
 * @describe 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class Q79wordSearch {
    int row;
    int col;
    boolean flag = false;
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        if(word == null){
            return true;
        }
        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];
        for(int i=0;i<row;i++){
            for(int j = 0;j<col;j++){
                if(word.charAt(0)==board[i][j]){
                    checkWord(board,word,0,i,j,visited);
                }
                if(flag){
                    break;
                }
            }
        }
        return flag;
    }
    private void checkWord(char[][] board,String word,int index,int i,int j,boolean[][] visited){
        if(index==word.length()){
            flag = true;
            return;
        }
        if(i<0||i>=row||j<0||j>=col||word.charAt(index)!=board[i][j]||visited[i][j]){
            return;
        }
        visited[i][j] = true;
        checkWord(board,word,index+1,i+1,j,visited);
        checkWord(board,word,index+1,i-1,j,visited);
        checkWord(board,word,index+1,i,j+1,visited);
        checkWord(board,word,index+1,i,j-1,visited);
        //就这个判断，卡了我好久，心累，没有它，运算超时！！！
        if(flag){
            return;
        }
        visited[i][j] = false;
    }

    public static void main(String[] args) {
        Q79wordSearch q79wordSearch = new Q79wordSearch();
        char[][] board = {
                {'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}
        };
        String word = "ABCESEEEFS";
        boolean test = q79wordSearch.exist(board,word);
        System.out.println(test);
    }
}
