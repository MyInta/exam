package leetcode_inta.leetcode1151_1200;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/8
 * @describe 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；
 * 该函数的功能是统计 s  中（按字典序比较）最小字母的出现频次。
 *
 * 例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
 *
 * 现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案，
 * 其中每个 answer[i] 是满足 f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：queries = ["cbd"], words = ["zaaaz"]
 * 输出：[1]
 * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
 * 示例 2：
 *
 * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * 输出：[1,2]
 * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
 *  
 *
 * 提示：
 *
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] 都是小写英文字母
 *
 */
public class Q1170numSmallerByFrequency {
    //题意就是queries里面每个单词从字典words中查找符合要求f(q) < f(w)的数量
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] res = new int[queries.length];
        int[] wordsCounts = new int[words.length];
        for (int i = 0; i < words.length; i ++) {
            wordsCounts[i] = findMin(words[i]);
        }
        Arrays.sort(wordsCounts);

        for (int i = 0; i < res.length; i ++) {
            res[i] = findWord(queries[i], wordsCounts);
        }
        return res;
    }
    //找字符串对应于字典符合要求的数量
    private int findWord(String s, int[] nums) {
        int sNum = findMin(s);
        //找第一个大于sNum的索引
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= sNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums.length - left;
    }
    //找字符串中最小字符出现次数
    private int findMin(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a'] ++;
        }
        for (int count : counts) {
            if (count > 0) return count;
        }
        return 0;
    }
}
