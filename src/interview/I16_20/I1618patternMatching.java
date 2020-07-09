package interview.I16_20;

import java.awt.*;

/**
 * @author inta
 * @date 2020/6/22
 * @describe 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，
 * 用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"
 * 其中"cat"是"a"，"go"是"b"
 * ，该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 *
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 *
 * 示例 2：
 *
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 *
 * 示例 3：
 *
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 *
 * 示例 4：
 *
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 *
 * 提示：
 *
 *     0 <= len(pattern) <= 1000
 *     0 <= len(value) <= 1000
 *     你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 */
public class I1618patternMatching {
    public boolean patternMatching(String pattern, String value) {
        //如果匹配串为空，那么只有当被匹配串为空时true
        if (pattern.length() == 0) return value.length() == 0;
        //题意是被匹配的字符串可以是空，也就是单一空字符串，p='a'与v=''这种情况为true
        if (value.length() == 0) return pattern.length() == 1;
        //标记第一个字符串结束位置
        int cur = 0;
        //判断是否是a开头，用来后面添加时有依据添加第一个字符串还是第二个字符串
        boolean flag = pattern.charAt(0) == 'a';
        //统计a,b数量 计算字符串长度是否符合要求，用来优化
        int first_num = 0, second_num = 0;
        for (char p : pattern.toCharArray()) {
            if (p == 'a') {
                if (flag) {
                    first_num ++;
                } else {
                    second_num ++;
                }
            } else {
                if (flag) {
                    second_num ++;
                } else {
                    first_num ++;
                }
            }
        }

        while (cur <= value.length()) {
            //先通过切割获得第一个字符串
            String first = value.substring(0, cur);
            int mark = first.length();
            while (mark <= value.length()) {
                int index = mark;
                while (index <= value.length()) {
                    //切割前，统计下长度，看是否符合要求
                    if (first.length() * first_num + (index - mark) * second_num != value.length()) {
                        index ++;
                        continue;
                    }
                    //在字符串后切割获得第二个字符串
                    String second = value.substring(mark, index);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < pattern.length(); i++) {
                        if (pattern.charAt(i) == 'a') {
                            if (flag) {
                                sb.append(first);
                            } else {
                                sb.append(second);
                            }
                        } else {
                            if (flag) {
                                sb.append(second);
                            } else {
                                sb.append(first);
                            }
                        }
                    }
                    if (sb.toString().equals(value)) return true;
                    index ++;
                }
                mark += first.length() == 0 ? 1 : first.length();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pattern.length(); i++) {
                sb.append(first);
            }
            if (sb.toString().equals(value)) return true;
            cur ++;
        }
        return false;
    }
}
