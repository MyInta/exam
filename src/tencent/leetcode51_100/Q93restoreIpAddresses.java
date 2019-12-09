package tencent.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/9
 * @describe 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class Q93restoreIpAddresses {
    //理解为0-255为一段,看了大佬们的暴力算法
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int a = 1; a < 4; a ++) {
            for (int b = a + 1; b < a + 4; b ++) {
                for (int c = b + 1; c < b + 4; c ++) {
                    //当最后一段长度大于3或者等于大于给予的s长度的时候异常，需要修改长度
                    if (c < s.length() - 3 || c >= s.length()) continue;
                    int s1 = Integer.parseInt(s.substring(0, a));
                    int s2 = Integer.parseInt(s.substring(a, b));
                    int s3 = Integer.parseInt(s.substring(b, c));
                    int s4 = Integer.parseInt(s.substring(c));
                    if (s1 <= 255 && s2 <= 255 && s3<= 255 && s4 <= 255) {
                        sb.append(s1).append(".").append(s2).append(".").append(s3).append(".").append(s4);
                        if (sb.toString().length() == s.length() + 3) {
                            res.add(sb.toString());
                        }
                        //每次遍历都要讲sb复原
                        sb.delete(0, sb.length());
                    }
                }
            }
        }
        return res;
    }
}
