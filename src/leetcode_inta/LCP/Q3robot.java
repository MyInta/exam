package leetcode_inta.LCP;

/**
 * @author inta
 * @date 2020/1/8
 * @describe 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。
 * 小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 *
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 *
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 * 示例 2：
 *
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 * 示例 3：
 *
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *  
 *
 * 限制：
 *
 * 2 <= command的长度 <= 1000
 * command由U，R构成，且至少有一个U，至少有一个R
 * 0 <= x <= 1e9, 0 <= y <= 1e9
 * 0 <= obstacles的长度 <= 1000
 * obstacles[i]不为原点或者终点
 *
 */
public class Q3robot {
    //暴力解法，超时
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        char[] chars = command.toCharArray();
        int start_x = 0, start_y = 0;
        while (start_x <= x && start_y <= y) {
            for (char c : chars) {
                for (int[] obstacle : obstacles) {
                    if (obstacle[0] == start_x && obstacle[1] == start_y) return false;
                }
                if (start_x == x && start_y == y) return true;
                if (c == 'U') {
                    start_y ++;
                } else {
                    start_x ++;
                }
            }
        }
        return false;
    }


    public boolean robot2(String command, int[][] obstacles, int x, int y) {
        for (int[] obstacle : obstacles) {
            int o_x = obstacle[0];
            int o_y = obstacle[1];
            if (o_x + o_y > x + y) continue;
            //如果可以到达该点
            if (getCount(o_x, o_y, command)) {
                return false;
            }
        }
        //遍历完，都没有返回，就说明在终点范围内没有碰到障碍，最后考虑有没有到达终点即可
        return getCount(x, y, command);
    }
    //统计命令中U和R的数量，U为count[0] R为count[1]
    private int[] getCommandCount(String command, int length) {
        int[] count = new int[]{0, 0};
        char[] chars = command.toCharArray();
        for (int i = 0; i < length; i ++) {
            if (chars[i] == 'U') {
                count[0] ++;
            } else {
                count[1] ++;
            }
        }
        return count;
    }
    private boolean getCount(int x, int y, String command) {
        int times = (x + y) / command.length();
        int resume = (x + y) % command.length();
        int[] step = getCommandCount(command, command.length());
        int[] res_step = getCommandCount(command, resume);
        step[0] = step[0] * times + res_step[0];
        step[1] = step[1] * times + res_step[1];
        return y == step[0] && x == step[1];
    }


}