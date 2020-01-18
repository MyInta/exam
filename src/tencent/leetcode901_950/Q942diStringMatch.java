package tencent.leetcode901_950;

/**
 * @author inta
 * @date 2020/1/14
 * @describe 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
 *
 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
 *
 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
 *  
 *
 * 示例 1：
 *
 * 输出："IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 *
 * 输出："III"
 * 输出：[0,1,2,3]
 * 示例 3：
 *
 * 输出："DDI"
 * 输出：[3,2,0,1]
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 1000
 * S 只包含字符 "I" 或 "D"。
 *
 */
public class Q942diStringMatch {
    //减从最大开始，增从最小开始
    public int[] diStringMatch(String S) {
        int n = S.length();
        int[] res = new int[n + 1];
        int left = 0, right = n;
        for (int i = 0; i < n; i ++) {
            if (S.charAt(i) == 'I') {
                res[i] = left ++;
            } else {
                res[i] = right --;
            }
        }
        res[n] = right;
        return res;
    }
}
