package tencent.leetcode1101_1150;

/**
 * @author inta
 * @date 2019/11/21
 * @describe 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 *
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"
 * 示例 2：
 *
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 *  
 *
 * 提示：
 *
 * 给出的 address 是一个有效的 IPv4 地址
 */
public class Q1108defangIPaddr {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        char[] add_chars = address.toCharArray();
        for (char add_char : add_chars) {
            if (add_char == '.') {
                sb.append("[.]");
            } else {
                sb.append(add_char);
            }
        }
        return sb.toString();
    }
}
