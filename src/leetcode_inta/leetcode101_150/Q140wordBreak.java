package leetcode_inta.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2020/1/27
 * @describe 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 */
public class Q140wordBreak {
    //想到最蠢的方式，就是把所有字典放入set中，然后指针遍历字符串，找到一个就添加，直到末尾重头指针开始
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null) return new ArrayList<>();

        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
            dict.add(word);
        }
        return dfs(s, dict, 0);
    }
    //保存下所有索引位置可以取到的字符串集合
    private Map<Integer, List<String>> markIndexList = new HashMap<>();
    //找出字符串最大的长度，因为要减少不必要的判断(有时候一个字符串老长，没必要判断了)
    private int maxLen = 0;

    private List<String> dfs(String s, Set<String> dict, int cur) {
        //如果早就包含有该索引的集合，直接返回即可
        if (markIndexList.containsKey(cur)) {
            return markIndexList.get(cur);
        }
        //否则新建k/v，准备添加
        List<String> list = new ArrayList<>();
        //如果当前索引到了字符串末端
        if (cur == s.length()) {
            list.add("");
        }
        //在最大单个单词的长度和字符串长度限值下
        for (int i = 1; i <= maxLen && i + cur <= s.length(); i ++) {
            String sub = s.substring(cur, cur + i);
            //如果字典中包含有该字符片段，我们需要寻找它往下遍历可返回的集合
            if (dict.contains(sub)) {
                List<String> dfsReturns = dfs(s, dict, cur + i);
                //对于所有子集合，我们要把找到的sub添加上去，添加前判断是否不为空
                for (String dfsReturn : dfsReturns) {
                    list.add(sub + (dfsReturn.equals("") ? "" : " " + dfsReturn));
                }
            }
        }
        markIndexList.put(cur, list);
        return list;
    }
}
