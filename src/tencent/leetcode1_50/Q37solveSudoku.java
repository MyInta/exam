package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/12/16
 * @describe 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * Note:
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class Q37solveSudoku {

    public void solveSudoku(char[][] board) {
        //前[]代表几行或几列 后一[]表示数字几
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][][] block = new boolean[3][3][10];
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                int num = board[i][j] - '0';
                //当该元素为1-9之间，那么给每个区域设定该位置已被占用
                if (num >= 1 && num <= 9) {
                    //这里也解释了为什么用长度10来填充，只是为了表述方便
                    rows[i][num] = true;
                    cols[j][num] = true;
                    block[i / 3][j / 3][num] = true;
                }
            }
        }
        isValidSudoku(board, rows, cols, block, 0, 0);
    }

    //用来递归填充元素
    private boolean isValidSudoku(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][][] block, int row, int col) {
        //边界
        if (col == board[0].length) {
            //将列归位到开头0，我们每次填充选择一行一行从左到右填充
            col = 0;
            //进入下一行
            row ++;
            if (row == board.length) {
                //如果已经遍历完所有行了，直接返回即可
                return true;
            }
        }
        //如果该位置为空，需要填充，我们进入判断1-9的数字是否是未被填充过的，挨个遍历
        if (board[row][col] == '.') {
            //挨个填充数字k（1-9） 尝试
            for (int k = 1; k <= 9; k ++) {
                //判断将要填充的数字是否是未被占用的
                boolean used = !(rows[row][k] || cols[col][k] || block[row / 3][col / 3][k]);
                if (used) {
                    //如果没有被占用过，我们就可以开始填充数据
                    board[row][col] = (char)(k + '0');
                    //并且在对应位置附上已经被填充过的信息
                    rows[row][k] = true;
                    cols[col][k] = true;
                    block[row / 3][col / 3][k] = true;

                    //进入下一层尝试
                    if (isValidSudoku(board, rows, cols, block, row, col + 1)) {
                        //如果始终为true，说明这一行填充是没问题的
                        return true;
                    }
                    //上面语句没有返回，就说明该位置需要重置
                    rows[row][k] = false;
                    cols[col][k] = false;
                    block[row / 3][col / 3][k] = false;
                    board[row][col] = '.';
                }
            }
        } else {
            //如果该位置原先已经有值了，我们只需要填充下一个元素即可
            return isValidSudoku(board, rows, cols, block, row, col + 1);
        }
        //如果都不行，说明有问题，不存在
        return false;
    }
}
