package tencent.leetcode551_600;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/1/9
 * @describe 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，
 * 每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 *
 * 示例 1:
 *
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 * 示例 2 :
 *
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * 注意:
 *
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 * 数组中数字的大小在范围[-100,000, 100,000]内。
 *
 */
public class Q575distributeCandies {
    //题意是找数组中最大非重复元素，并且小于等于数组长度的一半,发现效率低
    public int distributeCandies(int[] candies) {
        Arrays.sort(candies);
        int res = 1;
        for (int i = 0; i < candies.length - 1; i ++) {
            if (candies[i + 1] != candies[i]) {
                res ++;
            }
        }
        return res > candies.length / 2 ? candies.length / 2 : res;
    }

    //如果使用set会怎么样
    public int distributeCandies2(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int c : candies) set.add(c);
//        return set.size() > candies.length / 2 ? candies.length / 2 : set.size();
        return Math.min(set.size(), candies.length / 2);
    }
}
