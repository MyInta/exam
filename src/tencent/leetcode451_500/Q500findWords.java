package tencent.leetcode451_500;

import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2019/12/2
 * @describe 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 * 示例：
 *
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 *  
 *
 * 注意：
 *
 * 你可以重复使用键盘上同一字符。
 * 你可以假设输入的字符串将只包含字母。
 *
 */
public class Q500findWords {
    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        Set<Character> set3 = new HashSet<>();
        char[] c1 = {'q','w','e','r','t','y','u','i','o','p','Q','W','E','R','T','Y','U','I','O','P'};
        char[] c2 = {'a','s','d','f','g','h','j','k','l','A','S','D','F','G','H','J','K','L'};
        char[] c3 = {'z','x','c','v','b','n','m','Z','X','C','V','B','N','M'};
        for (char c : c1) {
            set1.add(c);
        }
        for (char c : c2) {
            set2.add(c);
        }
        for (char c : c3) {
            set3.add(c);
        }
        for (String word : words) {
            boolean[] nums = new boolean[3];
            boolean isV = true;
            for (char c : word.toCharArray()) {
                if (set1.contains(c)) {
                    nums[0] = true;
                } else if (set2.contains(c)) {
                    nums[1] = true;
                } else if (set3.contains(c)) {
                    nums[2] = true;
                }
                if (nums[0] && nums[1] || nums[0] && nums[2] || nums[1] && nums[2]) {
                    isV = false;
                    break;
                }
            }
            if (isV) res.add(word);
        }
        return res.toArray(new String[res.size()]);
    }
}
