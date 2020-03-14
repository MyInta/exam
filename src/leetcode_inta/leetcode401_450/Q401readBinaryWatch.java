package leetcode_inta.leetcode401_450;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/3
 * @describe 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 案例:
 *
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *  
 *
 * 注意事项:
 *
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 *
 */
public class Q401readBinaryWatch {

    private List<String> res = new ArrayList<>();
    public List<String> readBinaryWatch(int num) {
        int[] nums = {8,4,2,1,32,16,8,4,2,1};
        int[] match_nums = new int[10];
        dfs(nums, match_nums, num, 0, 0);
        return res;
    }
    private void dfs(int[] nums, int[] match_nums, int n, int curN, int start) {
        //当数量一致，说明可以看看读数了
        if (n == curN) {
            int h = 0;
            int m = 0;
            for (int i = 0; i < 4; i ++) {
                h += nums[i] * match_nums[i];
            }
            for (int j = 4; j < nums.length; j ++) {
                m += nums[j] * match_nums[j];
            }
            if (h < 12 && m < 60) {
                res.add(String.format("%d:%02d", h, m));
            }
        } else if (n > curN) {
            for (int i = start; i < nums.length; i ++) {
                match_nums[i] = 1;
                dfs(nums, match_nums, n, curN + 1, i + 1);
                match_nums[i] = 0;
            }
        }
    }
}
