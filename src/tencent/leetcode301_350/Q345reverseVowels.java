package tencent.leetcode301_350;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2019/12/2
 * @describe 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。(大小写都算)
 *
 * 示例 1:
 *
 * 输入: "hello"
 * 输出: "holle"
 * 示例 2:
 *
 * 输入: "leetcode"
 * 输出: "leotcede"
 * 说明:
 * 元音字母不包含字母"y"。
 */
public class Q345reverseVowels {
    //自己的苯办法，保存了字符串里面的元音字母所在序列，最后修改对应char中索引反转，输出字符串
    public String reverseVowels(String s) {
        char[] s_chars = s.toCharArray();
        ArrayList<Integer> vowels = new ArrayList<>();
        for (int i = 0; i < s_chars.length; i ++) {
            //将所有元音字母以及索引添加到集合中
            if (isV(s_chars[i])) {
                vowels.add(i);
            }
        }
        //遍历集合对数组对应位置修改
        for (int i = vowels.size() - 1; i >= vowels.size() / 2; i --) {
            int temp = vowels.get(i);
            int temp2 = vowels.get(vowels.size() - 1 - i);
            //交换
            char temp_c = s_chars[temp];
            s_chars[temp] = s_chars[temp2];
            s_chars[temp2] = temp_c;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s_chars) {
            sb.append(c);
        }
        return sb.toString();
    }
    private boolean isV(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') return true;
        return false;
    }

    //使用双指针试下
    public String reverseVowels2(String s) {
        char[] s_chars = s.toCharArray();
        int left = 0;
        int right = s_chars.length - 1;
        StringBuilder sb = new StringBuilder();
        while (left < right) {
            //原本判断是否元音，可以使用set保存，我试一下使用私有方法减少内存占用？
            while (left < right && !isV(s_chars[left])) {
                left ++;
            }
            while (left < right && !isV(s_chars[right])) {
                right --;
            }
            if (left < right && s_chars[left] != s_chars[right]) {
                char temp = s_chars[left];
                s_chars[left] = s_chars[right];
                s_chars[right] = temp;
            }
            left ++;
            right --;
        }
        for (char c : s_chars) {
            sb.append(c);
        }
        return sb.toString();
    }

    //如果是使用了set呢？效率还没有自己写的私有方法快。。。
    public String reverseVowels3(String s) {
        char[] s_chars = s.toCharArray();
        int left = 0;
        int right = s_chars.length - 1;
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        char[] vowels_char = {'a','e','i','o','u','A','E','I','O','U'};
        for (char c : vowels_char) {
            set.add(c);
        }
        while (left < right) {
            //原本判断是否元音，可以使用set保存，我试一下使用私有方法减少内存占用？
            while (left < right && !set.contains(s_chars[left])) {
                left ++;
            }
            while (left < right && !set.contains(s_chars[right])) {
                right --;
            }
            if (left < right && s_chars[left] != s_chars[right]) {
                char temp = s_chars[left];
                s_chars[left] = s_chars[right];
                s_chars[right] = temp;
            }
            left ++;
            right --;
        }
        for (char c : s_chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}
