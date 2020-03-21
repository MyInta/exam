package interview.I16_20;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/21
 * @describe 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，
 * 长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 *
 * 提示：
 *
 *     0 < shorter <= longer
 *     0 <= k <= 100000
 */
public class I1611divingBoard {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) return new int[0];
        int sum = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            sum += shorter;
        }
        int temp = sum;
        res.add(temp);
        while (k >= 0) {
            if (sum > temp) {
                res.add(sum);
                temp = sum;
            }
            sum = sum - shorter + longer;
            k --;
        }
        int[] ans = new int[res.size()];
        int index = 0;
        for (int i : res) {
            ans[index ++] = i;
        }
        return ans;
    }

    //看了题解后，发现原来这里是有规律的，k个只会有顶多k+1的解
    public int[] divingBoard2(int shorter, int longer, int k) {
        if (k < 1) return new int[0];
        //相等时只有一解
        if (shorter == longer) return new int[]{k * shorter};
        //不相等时有k+1解
        int[] res = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            res[i] = longer * i + shorter * (k - i);
        }
        return res;
    }
}
