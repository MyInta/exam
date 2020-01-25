package tencent.leetcode901_950;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/22
 * @describe 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 * 示例 1：
 *
 * 输入："ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 *
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 *
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 *
 * 提示：
 *
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122 
 * S 中不包含 \ or "
 *
 */
public class Q917reverseOnlyLetters {
    //我使用了指针，效率高，但是不容易理解
    public String reverseOnlyLetters(String S) {
        char[] sChars = S.toCharArray();
        int len = sChars.length;
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = len - 1; i >= 0; i --) {
            //题意只33-122之间不会超过122的z
            while (cur < len && (sChars[cur] < 65 || (sChars[cur] > 90 && sChars[cur] < 97))) {
                    //当非字母情况下，添加非字母
                    sb.append(sChars[cur]);
                    cur ++;
            }
            //如果倒序查找的字符为字母，添加之，否则不添加
            if ((sChars[i] >= 65 && sChars[i] <= 90) || (sChars[i] >= 97 && sChars[i] <= 122)) {
                sb.append(sChars[i]);
                cur ++;
            }
        }
        while (cur < len && (sChars[cur] < 65 || (sChars[cur] > 90 && sChars[cur] < 97))) {
                //当非字母情况下，添加非字母
                sb.append(sChars[cur]);
                cur ++;
        }
        return sb.toString();
    }

    //比较容易想的是两遍，一遍把所有字母信息保存，第二遍按要求穿插
    public String reverseOnlyLetters2(String S) {
        //保存方式按照栈或者数组又可以划分为两种表达方式
        //这里使用栈来浅显易懂些
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (Character.isLetter(c)) stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (Character.isLetter(c)) sb.append(stack.pop());
            else sb.append(c);
        }
        return sb.toString();
    }

    //官解的双指针，那才是双指针，我自己之前写的是xxxx
    public String reverseOnlyLetters3(String S) {
        //核心思想是使用一个指针来保存从后往前遍历的索引，需要添加字母就靠它
        int j = S.length() - 1;
        StringBuilder sb = new StringBuilder();
        char[] sChars = S.toCharArray();
        for (int i = 0; i < sChars.length; i ++) {
            if (Character.isLetter(sChars[i])) {
                //如果当前位置应该放字母，使用指针j
                while (!Character.isLetter(sChars[j])) {
                    j --;
                }
                //添加字母，并且让指针往后挪一位
                sb.append(sChars[j --]);
            } else {
                //不是字母，使用指针i，在原先位置上添加非字母
                sb.append(sChars[i]);
            }
        }
        return sb.toString();
    }
}
