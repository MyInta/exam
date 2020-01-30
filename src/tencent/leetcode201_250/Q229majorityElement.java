package tencent.leetcode201_250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/30
 * @describe 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: [3]
 * 示例 2:
 *
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 *
 */
public class Q229majorityElement {
    //虽然要求O(n) 我先用nO(n)做一下
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0, right = 0;
        while (left < n - n / 3 && right < n - 1) {
            if (nums[right + 1] == nums[left]) {
                right ++;
                continue;
            } else {
                if (right - left + 1 > n / 3) res.add(nums[left]);
                right ++;
                left = right;
            }
        }
        //补上可能的尾端成立情况
        if (right - left + 1 > n / 3) res.add(nums[left]);
        return res;
    }

    //大神的思维是遍历两轮，一轮使用摩尔投票方式找出票数最多的两个候选人，二轮找出候选人票数
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int n = nums.length;
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0, countB = 0;
        for (int i = 0; i < n; i ++) {
            //如果和候选人相同，加票
            if (nums[i] == candidateA) {
                countA ++;
                continue;
            }
            if (nums[i] == candidateB) {
                countB ++;
                continue;
            }
            //如果不同，就查看候选人中是否为0，为零的要被新的候选人替换，数量统计重置为1
            if (countA == 0) {
                candidateA = nums[i];
                countA = 1;
                continue;
            }
            if (countB == 0) {
                candidateB = nums[i];
                countB = 1;
                continue;
            }
            //如果没有为零，那把两个候选人的票数均衡下都削去1
            countA --;
            countB --;
        }
        //一轮遍历已经找到了两位候选人，我们现在查看他们的票数进行统计并考虑添加到结果集,先重置票数0
        countA = 0;
        countB = 0;
        for (int num : nums) {
            //注意这里考虑到两位候选人可能会相同，在统计票数的时候，只要一方相同，直接统计下一个不考虑另一方
            if (num == candidateA) {
                countA ++;
            } else if (num == candidateB) {
                countB ++;
            }
        }
        //票数大于n/3的进入结果集
        if (countA > n / 3) res.add(candidateA);
        if (countB > n / 3) res.add(candidateB);
        return res;
    }
}
