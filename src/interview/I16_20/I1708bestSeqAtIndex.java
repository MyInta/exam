package interview.I16_20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/3/23
 * @describe 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，
 * 在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 *
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 *
 * 提示：
 *
 *     height.length == weight.length <= 10000
 *
 */
public class I1708bestSeqAtIndex {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        //用二维数组保存莫个人身高和体重
        int[][] nums = new int[height.length][2];
        for (int i = 0; i < height.length; i++) {
            nums[i][0] = height[i];
            nums[i][1] = weight[i];
        }
        //按照他们的身高由低到高排序
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0] == 0 ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] dp = new int[height.length];
        int res = 0;
        for (int[] num : nums) {
           int index = Arrays.binarySearch(dp, 0, res, num[1]);
           if (index < 0) index = -(index + 1);
           dp[index] = num[1];
           if (index == res) res ++;
        }
        return res;
    }
}
