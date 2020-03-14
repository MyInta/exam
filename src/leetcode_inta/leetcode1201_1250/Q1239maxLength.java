package leetcode_inta.leetcode1201_1250;

import java.util.List;

/**
 * @author inta
 * @date 2020/2/1
 * @describe 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，
 * 如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 *
 * 请返回所有可行解 s 中最长长度。
 * 示例 1：
 *
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 *
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 *
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 *
 */
public class Q1239maxLength {
    //遍历每个索引为起点，dfs查找可能的最大长度，我的解法效率一般，看大神都是用位压缩优化，回头再看
    private int[] count = new int[26];
    public int maxLength(List<String> arr) {
        dfs(arr, 0, 0);
        return res;
    }
    private int res = 0;
    private void dfs(List<String> arr, int start, int len) {
        if (start == arr.size()) {
            res = Math.max(res, len);
            return;
        }
        char[] chars = arr.get(start).toCharArray();
        //需要考虑单词内有重复
        int[] c_count = new int[26];
        for (char c : chars) {
            //如果有重复，直接进入下一个，跳过它
            if (c_count[c - 'a'] > 0) {
                dfs(arr, start + 1, len);
                return;
            }
            c_count[c - 'a'] ++;
        }
        //考虑字符非重复情况下，有无和之前字符重复，重复直接返回
        for (char c : chars) {
            if (count[c - 'a'] > 0) {
                dfs(arr, start + 1, len);
                return;
            }
        }
        //没有return，说明都符合要求,添加到count
        for (char c : chars) {
            count[c - 'a'] ++;
        }
        dfs(arr, start + 1,len + chars.length);
        //回溯
        for (char c : chars) {
            count[c - 'a'] --;
        }
        dfs(arr, start + 1, len);
    }
}
