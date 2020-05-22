package leetcode_inta.leetcode851_900;

import java.util.concurrent.BlockingDeque;

/**
 * @author inta
 * @date 2020/5/22
 * @describe 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入： A = "ab", B = "ba"
 * 输出： true
 *
 * 示例 2：
 *
 * 输入： A = "ab", B = "ab"
 * 输出： false
 *
 * 示例 3:
 *
 * 输入： A = "aa", B = "aa"
 * 输出： true
 *
 * 示例 4：
 *
 * 输入： A = "aaaaaaabc", B = "aaaaaaacb"
 * 输出： true
 *
 * 示例 5：
 *
 * 输入： A = "", B = "aa"
 * 输出： false
 *
 *
 *
 * 提示：
 *
 *     0 <= A.length <= 20000
 *     0 <= B.length <= 20000
 *     A 和 B 仅由小写字母构成。
 *
 */
public class Q859buddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        //用一个set保存不同时候的字符串
        int[] countA = new int[127];
        int[] countB = new int[127];
        int dif = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                dif ++;
            }
            countA[A.charAt(i)] ++;
            countB[B.charAt(i)] ++;
            //不匹配情况多于2不符合题意
            if (dif > 2) return false;
        }
        //如果没有不匹配位置，就考虑有无重复字符如aa匹配aa是符合题意的
        if (dif == 0) {
            for (int num : countA) {
                if (num > 1) return true;
            }
            return false;
        } else {
            //然后比较两者位置不同时保留的字符串，若不匹配，就是不能交换
            for (int i = 0; i < 127; i++) {
                if (countA[i] != countB[i]) return false;
            }
            return true;
        }
    }

    //官方思路和我差不多，就是实现起来比我简便，三个方向：长度不等；完全相同；长度相同但不完全相同。
    public boolean buddyStrings2(String A, String B) {
        //第一种，长度不等
        if (A.length() != B.length()) return false;
        //第二种，完全相同
        if (A.equals(B)) {
            //考虑是否有重复字符，如aa和aa是符合题意的
            int[] count = new int[127];
            for (char a : A.toCharArray()) {
                count[a] ++;
            }
            for (int i = 0; i < 127; i++) {
                if (count[i] > 1) return true;
            }
            //没有重复，那么完全相同时必定不能交换位置
            return false;
        } else {
            //否则就是第三种，不完全相同
            //用来记录第一个不匹配时候的位置，和第二个
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1) {
                        //记录第一个不匹配时候的索引位置
                        first = i;
                    } else if (second == -1) {
                        //记录第二个
                        second = i;
                    } else {
                        //如果都记录过，那就说明超过两个不匹配，不符合题意
                        return false;
                    }
                }
            }
            //最后，汇总下，看看是否交换后相等
            return second != -1 && A.charAt(first) == B.charAt(second)
                    && A.charAt(second) == B.charAt(first);
        }
    }
}
