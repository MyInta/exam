package leetcode_inta.leetcode401_450;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/3
 * @describe 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * 案例:
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * 注意事项:
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 */
public class Q401readBinaryWatch {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn > 8) {
            return res;
        }
        int[] nums = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
        int[] visited = new int[10];
        dfs(res, visited, nums, 0, 0, turnedOn);
        return res;
    }

    // 参数分别为结果集、索引遍历记录数组、当前起始点， 累计数量和总目标数。
    private void dfs(List<String> res, int[] visited, int[] nums, int start, int count, int target) {
        if (count == target) {
            int h = 0;
            int m = 0;
            for (int i = 0; i < 4; i++) {
                h += visited[i] * nums[i];
            }
            for (int j = 4; j < 10; j++) {
                m += visited[j] * nums[j];
            }
            if (h < 12 && m < 60) {
                res.add(String.format("%d:%02d", h, m));
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                visited[i] = 1;
                dfs(res, visited, nums, i + 1, count + 1, target);
                visited[i] = 0;
            }
        }
    }
}
