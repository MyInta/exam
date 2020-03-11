package leetcode_inta.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/1
 * @describe
 */
public class D1201_1 {
    //题意在9宫格内玩的,直接先暴力破解下
    public String tictactoe(int[][] moves) {
        int[] row_A = new int[3];
        int[] col_A = new int[3];
        int[] other_A = new int[2];
        int[] row_B = new int[3];
        int[] col_B = new int[3];
        int[] other_B = new int[2];
        boolean isA = true;
        for (int[] move : moves) {
            int x = move[0];
            int y = move[1];
            if (isA) {
                if (x == 0 && y == 0 || x == 2 && y == 2) {
                    other_A[0] ++;
                }
                if (x == 0 && y == 2 || x == 2 && y == 0) {
                    other_A[1] ++;
                }
                if (x == 1 && y == 1) {
                    other_A[0] ++;
                    other_A[1] ++;
                }
                if (row_A[x] == 2 || col_A[y] == 2 || other_A[0] == 3 || other_A[1] == 3) {
                    return "A";
                }
                row_A[x] ++;
                col_A[y] ++;
                isA = false;
            } else {
                if (x == 0 && y == 0 || x == 2 && y == 2) {
                    other_B[0] ++;
                }
                if (x == 0 && y == 2 || x == 2 && y == 0) {
                    other_B[1] ++;
                }
                if (x == 1 && y == 1) {
                    other_B[0] ++;
                    other_B[1] ++;
                }
                //如果为B
                if (row_B[x] == 2 || col_B[y] == 2 || other_B[0] == 3 || other_B[1] == 3) {
                    return "B";
                }
                row_B[x] ++;
                col_B[y] ++;
                isA = true;
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }
}
