package leetcode_inta.leetcode201_250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/2/19
 * @describe 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 *
 * 示例 1:
 *
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2:
 *
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 */
public class Q241diffWaysToCompute {
    //看了题解才有思路
    //使用一个map记录保存过的字符串计算信息
    private Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (map.containsKey(input)) return map.get(input);
        for (int i = 0; i < input.length(); i ++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //如果是操作符号，就考虑前后两段的值集合
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
                //笛卡尔乘积
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+' :
                                res.add(l + r);
                                break;
                            case '-' :
                                res.add(l - r);
                                break;
                            case '*' :
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        //如果此时的res里面没有元素，说明是普通元素，非操作符
        if (res.isEmpty()) res.add(Integer.valueOf(input));
        //并且此时的string，我们计算过了，就保存信息，如果再碰到可以直接调用
        map.put(input, res);

        return res;
    }
}
