package leetcode_inta.leetcode951_1000;

import java.util.*;

/**
 * @author inta
 * @date 2020/6/8
 * @describe 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
 * 并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 *
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 *
 * 示例 2：
 *
 * 输出：["b==a","a==b"]
 * 输入：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 *
 * 示例 3：
 *
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 *
 * 示例 4：
 *
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 *
 * 示例 5：
 *
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *
 *
 *
 * 提示：
 *
 *     1 <= equations.length <= 500
 *     equations[i].length == 4
 *     equations[i][0] 和 equations[i][3] 是小写字母
 *     equations[i][1] 要么是 '='，要么是 '!'
 *     equations[i][2] 是 '='
 *
 */
public class Q990equationsPossible {
    //并查集？
    public boolean equationsPossible(String[] equations) {
        parent = new char[127];
        for (char ch = 'a'; ch <= 'z'; ch++) {
            //原先，每个人都是自己的主人
            parent[ch] = ch;
        }
        //遍历并考虑是否归并
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                //归并操作
                merge(equation.charAt(0), equation.charAt(3));
            }
        }
        //遍历并考虑是否归并
        for (String equation : equations) {
//            char first = equation.charAt(0);
//            char second = equation.charAt(3);
//            char flag = equation.charAt(1);
//            if (flag == '!') {
//                if (find(first) == find(second)) return false;
//            }
            if (equation.charAt(1) == '!') {
                if (find(equation.charAt(0)) == find(equation.charAt(3))) return false;
            }
        }
        //没有被超前返回，也就是说明其走得通
        return true;
    }
    //各元素对应父类
    private char[] parent;
    //寻找其帮派老大
    private char find(char c) {
        char child = c, temp;
        //一直追溯直到找到帮派老大
        while (c != parent[c]) {
            c = parent[c];
        }
        //路径压缩
        while (child != c) {
            temp = parent[child];
            parent[child] = c;
            child = temp;
        }
        return c;
    }
    //帮派火并
    private void merge(char a, char b) {
        char a_p = find(a);
        char b_p = find(b);
        if (a_p != b_p) {
            parent[a_p] = b_p;
        }
    }
}
