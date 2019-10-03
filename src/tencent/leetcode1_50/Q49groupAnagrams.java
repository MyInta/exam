package tencent.leetcode1_50;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/3
 * @describe 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 */
public class Q49groupAnagrams {
    //看题解后的简短解答
    public List<List<String>> groupAnagrams2(String[] strs) {
        //结果集
        List<List<String>> res = new ArrayList<>();
        //这个用来查重，当字典
        Map<String, Integer> map = new HashMap<>();
        //这个代表res的索引
        int count = 0;
        for (String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String sortedChar = String.valueOf(c);
            //如果不包含，则为新字符
            if (!map.containsKey(sortedChar)) {
                map.put(sortedChar, count++);
                //注意添加的是没有排序的字符
                List<String> list = new ArrayList<>();
                list.add(str);
                res.add(list);
            } else {
                //如果已经有了
                res.get(map.get(sortedChar)).add(str);
            }
        }
        return res;
    }



    //用于存放新鲜的字符
    private List<String> list = new ArrayList<>();
    //记录遍历寻找时，该字符从属的异位堆在list中的索引位置（图书属于哪一层的）
    private int index = 0;
    public List<List<String>> groupAnagrams(String[] strs) {
        //结果集
        List<List<String>> res = new ArrayList<>();
        for (String str : strs) {
            //如果是异位字母，就给数组添加元素
            if (isAnagrams(list, str)) {
                res.get(index).add(str);
            } else {
                //字典添新
                list.add(str);
                //结果集也添新
                List<String> newList = new ArrayList<>();
                newList.add(str);
                res.add(newList);
            }
        }
        return res;
    }
    //判断是否是异位字符
    private boolean isAnagrams(List<String> list, String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0; i < list.size(); i ++) {
            //每一个新字符寻找前都设个标识符，默认失败
            boolean listFlag = false;
            String listStr = list.get(i);
            if (listStr.length() != str.length()) {
                continue;
            }
            char[] listChar = listStr.toCharArray();
            for (char strC : strChar) {
                //每一个单词寻找前都设个标识符，默认失败
                boolean flag = false;
                for (int lc = 0; lc < listChar.length; lc ++) {
                    //找到相等的，就返回true
                    if (strC == listChar[lc]) {
                        listChar[lc] = 'A';
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    //说明该字符串不对，要寻找下一个字符串，将listFlag设为true
                    listFlag = true;
                    break;
                }
            }
            //遍历一圈，看看当初立下的listFlag有没有被人动过，没动过说明找到了
            if (!listFlag) {
                //都没问题，那就定位索引，并返回true
                index = i;
                return true;
            }
        }
        return false;
    }


}
