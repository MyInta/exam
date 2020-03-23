package interview.I16_20;

import java.time.temporal.Temporal;
import java.util.*;

/**
 * @author inta
 * @date 2020/3/23
 * @describe 给定N个人的出生年份和死亡年份，第i个人的出生年份为birth[i]，
 * 死亡年份为death[i]，实现一个方法以计算生存人数最多的年份。
 *
 * 你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，
 * 那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。
 *
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 *
 * 示例：
 *
 * 输入：
 * birth = {1900, 1901, 1950}
 * death = {1948, 1951, 2000}
 * 输出： 1901
 *
 * 提示：
 *
 *     0 < birth.length == death.length <= 10000
 *     birth[i] <= death[i]
 *
 */
public class I1610maxAliveYear {

    public int maxAliveYear(int[] birth, int[] death) {
        //用二维数组保存莫个人出生年份和死亡年份
        int[][] nums = new int[birth.length][2];
        for (int i = 0; i < birth.length; i++) {
            nums[i][0] = birth[i];
            nums[i][1] = death[i];
        }
        //按照他们的出生年份由早到晚排序
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //建立一个优先队列，队列里面存死亡年份，遍历出生，
        // 只要出生于死亡之后就剔除队列头，一直循环，跳出循环后添加该新成员的死亡日期
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = 0, year = -1;
        for (int[] num : nums) {
            while (!pq.isEmpty() && pq.peek() < num[0]) {
                pq.poll();
            }
            pq.add(num[1]);
            //若添加过程中的数量达到新高峰，就记录最大数量和对应年份,因为考虑>而非相等，不必考虑重复数量最早年份
            if (pq.size() > size) {
                size = pq.size();
                year = num[0];
            }
        }
        return year;
    }

    //看了大佬的解答有的思路，类似于公交题
    public int maxAliveYear2(int[] birth, int[] death) {
        //年份有限定
        int[] counts = new int[102];
        for (int i = 0; i < birth.length; i++) {
            counts[birth[i] - 1900] ++;
            counts[death[i] - 1899] --;
        }
        int maxNum = 0, year = -1, sum = 0;
        for (int j = 0; j < 102; j ++) {
            sum += counts[j];
            if (sum > maxNum) {
                maxNum = sum;
                year = 1900 + j;
            }
        }
        return year;
    }
}
