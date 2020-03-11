package leetcode_inta.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/9/26
 * @describe 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 *
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 */
public class Q89grayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size()-1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;
        }
        return res;
    }
   /* private void solution(List<Integer> temp, int start, int end) {
        if (start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            if (start == 1) {
                temp.add(start);
                continue;
            }
            int test = i^temp.get(temp.size() - 1);
            if (test == 1 || test%2 == 0) {
                temp.add(i);
            } else {
                temp.remove(temp.size()-1);
                solution(temp, i + 1, end);
            }
        }
    }*/
}
