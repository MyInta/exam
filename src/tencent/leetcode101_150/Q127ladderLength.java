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
                //本质上就是找宽度最小的为推进方，宽度大了就切换另一侧开始查找
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

    //参考网友的双向bfs 35ms
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        //查看末尾是否存在于字典中
        boolean flag = false;
        for (String word : wordList)
            if (word.equals(endWord))
                flag = true;
        if (!flag) return 0;
        //用一个map来保存所有字典中改变一位的字符串，所能变成的字典中的字情况
        Map<String, Set<String>> map = new HashMap<>();
        for (String word : wordList) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < len; i ++) {
                char c = chars[i];
                chars[i] = '*';
                String temp = String.valueOf(chars);
                //复原
                chars[i] = c;
                //如果该模板没有被保存过，新建一个
                if (!map.containsKey(temp)) {
                    map.put(temp, new HashSet<>());
                }
                map.get(temp).add(word);
            }
        }
        //双向bfs，一侧为推动方，一侧为比较方，为防止过多冗余，我们推动方始终让其为宽度最小方
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        //保留所有字典中字符串信息
        Set<String> all = new HashSet<>(wordList);
        //距离,保底距离为1，因为考虑到起始字符串算一个距离
        int dis = 1;
        while (!begin.isEmpty() && !end.isEmpty()) {
            //从保留信息中取出访问到的该层元素，因为是与起始只差一位，考虑最短路径下绝对不会被后序访问到，所以大胆去除
            all.removeAll(begin);
            //用来存储下一层元素
            Set<String> nextSet = new HashSet<>();
            //找出当前begin层的元素数量，用来挨个遍历找下一层元素
            for (String b : begin) {
                char[] chars = b.toCharArray();
                for (int i = 0; i < len; i ++) {
                    char c = chars[i];
                    chars[i] = '*';
                    String bTemp = String.valueOf(chars);
                    //回溯
                    chars[i] = c;
                    //如果该模板没有在字典中找到对应信息，跳过继续
                    if (!map.containsKey(bTemp)) continue;
                    //否则说明符合模板要求，就遍历模板对应字典内字符串
                    for (String word : map.get(bTemp)) {
                        //如果该字符是存在于bfs另一侧的层中，直接返回距离
                        if (end.contains(word)) return dis + 1;
                        //否则就给下一层添加,而这个字符串得是存在于字典中，并且没有被访问过的
                        if (all.contains(word)) {
                            nextSet.add(word);
                        }
                    }
                }
            }
            //此时下一层都已经添加好了，就将起始地址改为下一层
            begin = nextSet;
            //需要考虑起始侧为哪一侧，因为我们前面讲了，需要宽度最窄的为起始，这样数据冗余少
            Set<String> tempSet;
            if (begin.size() > end.size()) {
                //长度起始大，此时考虑交换
                tempSet = begin;
                begin = end;
                end = tempSet;
            }
            //层次递增
            dis ++;
        }
        return 0;
    }

    //做完126后反过来思考是否可以更精简一点
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        //保留所有字典中元素
        Set<String> all = new HashSet<>(wordList);
        //结尾不存在即说明无解
        if (!all.contains(endWord)) return 0;
        //分别保存bfs推动侧和接受侧
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        //起始位置beginWord算一个长度
        int res = 1;
        while (!begin.isEmpty()) {
            //从字典中去掉推动侧所有元素
            all.removeAll(begin);
            //此时先添加一行记录
            res ++;
            //保存下一行的信息
            Set<String> nexLine = new HashSet<>();
            for (String b : begin) {
                char[] bChars = b.toCharArray();
                for (int i = 0; i < bChars.length; i ++) {
                    //保留原先是哪个字符
                    char mark = bChars[i];
                    for (char ch = 'a'; ch <= 'z'; ch ++) {
                        if (ch == mark) continue;
                        bChars[i] = ch;
                        String newStr = String.valueOf(bChars);
                        //如果已经碰面，就直接返回
                        if (end.contains(newStr)) return res;
                        //如果字典中有她，就添加到下一行
                        if (all.contains(newStr)) nexLine.add(newStr);
                    }
                    //回溯
                    bChars[i] = mark;
                }
            }
            begin = nexLine;
            //选择宽度最段为推动侧
            Set<String> temp;
            if (begin.size() > end.size()) {
                temp = begin;
                begin = end;
                end = temp;
            }
        }
        //没找到合适碰面的，就是无解
        return 0;
    }
}
