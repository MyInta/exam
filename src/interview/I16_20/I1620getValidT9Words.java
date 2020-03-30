package interview.I16_20;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/30
 * @describe 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。[9宫格数字对应不同字符]
 * 每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。
 * 你会得到一张含有有效单词的列表。映射如下图所示：
 *
 * 示例 1:
 *
 * 输入: num = "8733", words = ["tree", "used"]
 * 输出: ["tree", "used"]
 *
 * 示例 2:
 *
 * 输入: num = "2", words = ["a", "b", "c", "d"]
 * 输出: ["a", "b", "c"]
 *
 * 提示：
 *
 *     num.length <= 1000
 *     words.length <= 500
 *     words[i].length == num.length
 *     num中不会出现 0, 1 这两个数字
 *
 */
public class I1620getValidT9Words {
    //简单实现方式：使用哈希表记录，暴力解法进行匹配求解
    public List<String> getValidT9Words(String num, String[] words) {
        //保存26个字母在9宫格内字符对应“数字”是啥
        char[] map = {'2','2','2','3','3','3','4','4','4',
                '5','5','5','6','6','6','7','7','7','7',
                '8','8','8','9','9','9','9'};
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            //对每个字符串的每个位置进行匹配，如果出现不符合九宫格对应字符表的，就不是符合要求的字符串
            //先立个旗帜，表明这个字符串是符合要求的
            boolean flag = true;
            //记录遍历到哪个字符串索引位置了
            int index = 0;
            for (char c : word.toCharArray()) {
                if (map[c - 'a'] != num.charAt(index ++)) {
                    flag = false;
                    break;
                }
            }
            //符合要求就添加到结果集内
            if (flag) ans.add(word);
        }
        return ans;
    }

    //这道题应该可以升华下，作为一个前缀树的题目
//    public List<String> getValidT9Words2(String num, String[] words) {
//
//    }
}
