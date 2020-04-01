package interview.I16_20;

import java.util.*;

/**
 * @author inta
 * @date 2020/3/30
 * @describe 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词，
 * 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 *
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 *
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出:
 * ["hit","hot","dot","lot","log","cog"]
 *
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: []
 *
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 */
public class I1722findLadders {
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(new String[]{beginWord}));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                List<String> temp = queue.poll();
                for (int i = wordList.size() - 1; i >= 0; i--) {
                    String str = wordList.get(i);
                    if (check(str, temp.get(temp.size() - 1))) {
                        //如果当前集合最后一个元素和字典中某个元素匹配度差一个，新建集合添加该元素即可
                        List<String> newList = new LinkedList<>(temp);
                        newList.add(str);
                        if (endWord.endsWith(str)) return newList;
                        queue.add(newList);
                        wordList.remove(str);
                    }
                }
                size --;
            }
        }
        return new ArrayList<>();
    }
    private boolean check(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i) && ++ diff > 1) return false;
        }
        return diff == 1;
    }
}
