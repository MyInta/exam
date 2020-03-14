package leetcode_inta.leetcode1151_1200;

import java.util.*;

/**
 * @author inta
 * @date 2019/11/19
 * @describe 给你一个字符串 s，请你对 s 的子串进行检测。
 *
 * 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。
 * 我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。 
 *
 * 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
 *
 * 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
 *
 * 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，
 * 如果 s[left..right] = "aaa" 且 k = 2，我们只能替换其中的两个字母。
 * （另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）
 *
 *  
 *
 * 示例：
 *
 * 输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * 输出：[true,false,false,true,true]
 * 解释：
 * queries[0] : 子串 = "d"，回文。
 * queries[1] : 子串 = "bc"，不是回文。
 * queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。
 * queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，
 * 先重新排序变成 "bacd"，然后把 "cd" 替换为 "ab"。
 * queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
 *  
 *
 * 提示：
 *
 * 1 <= s.length, queries.length <= 10^5
 * 0 <= queries[i][0] <= queries[i][1] < s.length
 * 0 <= queries[i][2] <= s.length
 * s 中只有小写英文字母
 *
 */
public class Q1177canMakePaliQueries {
    //超时。。。
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> answer = new ArrayList<>(queries.length);
        //用来存储每个检索中字符出现个数
        int[] nums;
        for (int[] querie : queries) {
            nums = new int[26];
            int left = querie[0];
            int right = querie[1];
            int k = querie[2];
            for (int i = left; i <= right; i ++) {
                nums[s.charAt(i) - 'a'] ++;
            }
            //用来计数 保存的字符中个数为奇数的个数
            int count = 0;
            for (int num : nums) {
                //如果是奇数
                if ((num & 1) == 1) {
                    count ++;
                }
            }
            //如果num为偶数，k与num/2比较，反之与（num - 1）/2比较
            if ((count & 1) == 0) {
                if (k >= count / 2) {
                    answer.add(true);
                } else {
                    answer.add(false);
                }
            } else {
                if (k >= (count - 1) / 2) {
                    answer.add(true);
                } else {
                    answer.add(false);
                }
            }
        }
        return answer;
    }

    //能通过，但有待优化
    public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
        List<Boolean> answer = new ArrayList<>(queries.length);
        //用来存储每个检索中字符出现个数,体现填充好
        int[][] nums = new int[s.length() + 1][26];
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            //将之前数组的值拷贝到新的数组上(后面数组都在原数组基础上累加的)
            System.arraycopy(nums[i], 0, nums[i + 1], 0, 26);
            //操作新的数组
            nums[i + 1][c - 'a'] ++;
        }
        for (int[] querie : queries) {
            int left = querie[0];
            int right = querie[1];
            int k = querie[2];
            int[] new_nums = new int[26];
            for (int i = 0; i < 26; i ++) {
                //利用已保存的数组信息，更新新数组的元素
                new_nums[i] = nums[right + 1][i] - nums[left][i];
            }
            //用来计数 保存的字符中个数为奇数的个数
            int count = 0;
            for (int new_num : new_nums) {
                //如果是奇数
                if ((new_num & 1) == 1) {
                    count ++;
                }
            }
            //如果num为偶数，k与num/2比较，反之与（num - 1）/2比较
            if ((count & 1) == 1) count --;
            if (k >= count / 2) {
                answer.add(true);
            } else {
                answer.add(false);
            }
        }
        return answer;
    }
}
