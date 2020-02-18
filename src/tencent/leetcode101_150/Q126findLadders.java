package tencent.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2020/2/14
 * @describe 给定两个单词（beginWord 和 endWord）和一个字典 wordList，
 * 找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: []
 *
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 */
public class Q126findLadders {
    //核心思想是在127基础上，bfs搭建一个map，保存改变一个位置元素所能对应的下一批元素信息，dfs通过这个遍历寻找，找到结果集即可
    //容易忽视的点：在127中我们找到碰面就直接返回，但找所有路径不可以直接返回，要在该层全都遍历完后，才跳出循环，不跳出循环会出现路径较长解
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        //保存字典中所有字符串信息
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return res;
        //用来映射每个单词对应可以找到的下一层元素信息
        Map<String, List<String>> mapTree = new HashMap<>();
        //存储bfs推动的一层
        Set<String> begin = new HashSet<>();
        //存储迎接的一层
        Set<String> end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        //如果bfs能走通，并且在走的过程中已经将mapTree搭建好了，也就是字符串对应变化为的下一个字符串信息都找好了
        if (bfs(begin, end, words, mapTree)) {
            //深入遍历，使用上面bfs找好的map来一层层找下去，找到终点就添加和返回
            dfs(beginWord, endWord, new LinkedList<>(), mapTree, res);
        }
        return res;
    }
    private boolean bfs(Set<String> begin, Set<String> end, Set<String> words, Map<String, List<String>> mapTree) {
        //记录有无找到对接情况
        boolean flag = false;
        //记录bfs双向遍历过程中，有无改变推进侧，true默认是从上往下，false改为从下往上
        boolean changed = true;
        while (!begin.isEmpty() && !end.isEmpty() && !flag) {
            //保存下一层的元素
            Set<String> nextLine = new HashSet<>();
            //删除掉字典中当前层的所有元素，因为是只改变一位考虑路径最短，不会误删
            words.removeAll(begin);
            for (String b : begin) {
                char[] chars = b.toCharArray();
                for (int i = 0; i < chars.length; i ++) {
                    char markI = chars[i];
                    for (char ch = 'a'; ch <= 'z'; ch ++) {
//                        if (chars[i] == ch) continue;
                        chars[i] = ch;
                        //获得修改位置的新字符串
                        String str = String.valueOf(chars);
                        //查看该字符串是否在字典中
                        if (words.contains(str)) {
                            //下一层添加
                            nextLine.add(str);
                            //判断是否碰面
                            if (end.contains(str)) flag = true;
                            //根据上下bfs推进方式选择层与层之间关系，k->v
                            String k = changed ? b : str;
                            String v = changed ? str : b;
                            //若该字符串还没有添加过下一层信息，就新建
                            if (!mapTree.containsKey(k)) {
                                mapTree.put(k, new LinkedList<>());
                            }
                            //添加
                            mapTree.get(k).add(v);
                        }
                    }
                    //回溯
                    chars[i] = markI;
                }
            }
            //让起始变为下一行
            begin = nextLine;
            //因为我们要保证bfs推进侧宽度最小，所以需要一个判断
            Set<String> temp;
            if (end.size() < begin.size()) {
                temp = end;
                end = begin;
                begin = temp;
                //还要记得改变推进方向
                changed = !changed;
            }
        }
        return flag;
    }
    private void dfs(String beginWord, String endWord, LinkedList<String> list, Map<String, List<String>> mapTree, List<List<String>> res) {
        //每遍历一层位置都添加起始元素
        list.add(beginWord);
        //碰面，就考虑添加操作
        if (beginWord.equals(endWord)) {
            res.add(new LinkedList<>(list));
            //回溯，删除已经添加过的集合中最后一个元素
            list.removeLast();
            return;
        }
        //如果起始元素是存在下一层元素，就遍历这下一层元素
        if (mapTree.containsKey(beginWord)) {
            for (String newBegin : mapTree.get(beginWord)) {
                dfs(newBegin, endWord, list, mapTree, res);
            }
        }
        //回溯
        list.removeLast();
    }
}
