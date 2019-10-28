package tencent.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/28
 * @describe 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class Q127ladderLength {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int level = 0;
        //用来储存已经遍历的字符串
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        set.add(beginWord);
        while (!queue.isEmpty()) {
            level ++;
            int len = queue.size();
            for (int i = 0; i < len; i ++) {
                String temp = queue.poll();
                //如果和目标串匹配，直接返回即可
                if (temp.equals(endWord)) {
                    return level;
                }
                //该字符未出现在集合中，
                for (String str : wordList) {
                    if (!set.contains(str) && isValid(str, temp)) {
                        queue.add(str);
                        set.add(str);
                    }
                }
            }
        }
        return 0;
    }
    //判断两个单词是否是只差一位的
    private boolean isValid(String s1, String s2) {
        int res = 0;
        //较费时
//        char[] s1_chars = s1.toCharArray();
////        char[] s2_chars = s2.toCharArray();
////        for (int i = 0; i < s1_chars.length; i ++) {
////            if (s1_chars[i] != s2_chars[i]) {
////                res ++;
////                if (res > 1) return false;
////            }
////        }
        for (int i = 0; i < s1.length(); i ++) {
            if (s1.charAt(i) != s2.charAt(i)) res ++;
            if (res > 1) return false;
        }
        return res == 1;
    }

    private class Q127ladderLength2 {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            //先判断一下结尾字符串是否在集合中
            boolean flag = false;
            for(String s:wordList)
                if(s.equals(endWord))
                    flag = true;
            if(!flag)return 0;

            int level = 0;
            int level2 = 0;
            //用来储存已经遍历的字符串
            Set<String> set = new HashSet<>();
            Set<String> set2 = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            Queue<String> queue2 = new LinkedList<>();
            //这里的先后顺序，直接影响到了结果，反过来找就会出错，不知道是为什么？？？
            queue2.add(beginWord);
            queue.add(endWord);
            set2.add(beginWord);
            set.add(endWord);
            while (!queue.isEmpty() || !queue2.isEmpty()) {
                if (queue.isEmpty() || !queue2.isEmpty() && queue2.size() <= queue.size()) {
                    int len = queue2.size();
                    level2 ++;
                    for (int i = 0; i < len; i ++) {
                        String temp = queue2.poll();
                        //如果和目标串匹配，直接返回即可
                        if (set.contains(temp)) return level + level2;
                        //该字符未出现在集合中，
                        for (String str : wordList) {
                            if (!set2.contains(str) && isValid(temp, str)) {
                                queue2.add(str);
                                set2.add(str);
                            }
                        }
                    }
                } else {
                    int len2 = queue.size();
                    level ++;
                    for (int i = 0; i < len2; i ++) {
                        String temp = queue.poll();
                        //如果和目标串匹配，直接返回即可
                        if (set2.contains(temp)) return level + level2;
                        //该字符未出现在集合中，
                        for (String str : wordList) {
                            if (!set.contains(str) && isValid(temp, str)) {
                                queue.add(str);
                                set.add(str);
                            }
                        }
                    }
                }

            }
            return 0;
        }
    }
}
