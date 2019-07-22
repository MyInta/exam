package tencent.leetcode101_200;

import tencent.leetcode201_300.Q208Trie2;

import java.util.*;

/**
 * @author inta
 * @date 2019/7/18
 * @describe 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
public class Q139WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        //转成set用以方便查看是否包含某元素
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        //i是s字符的截取段,j是截取段内的截取段分割位置
        for(int i=0;i<s.length()+1;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }

        }
        return dp[s.length()];
    }


    //=============================宽度优先搜索========================================
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] visited = new boolean[s.length()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int start = queue.remove();
            if(!visited[start]){
                for(int end=start+1;end<s.length()+1;end++){
                    if(set.contains(s.substring(start,end))){
                        //如果截取的是最后一段，且通过上面判断说明为真
                        if(end==s.length()){
                            return true;
                        }
                        queue.add(end);
                    }
                }
                visited[start] = true;
            }

        }
        //遍历一遍都没有走通（最终返回true）的路线，即说明不存在
        return false;
    }

    //==========================使用缓存减少回溯次数复杂度n2===================================
    public boolean wordBreak3(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0,new Boolean[s.length()]);
    }
    //采用记忆回溯法，将遍历过的位置设置到mem中
    public boolean word_Break(String s,HashSet<String> set,int start,Boolean[] mem){
        //如果start位置到了末端，即遍历完全了
        if(start == s.length()){
            return true;
        }
        //看看是不是遍历过的
        if(mem[start]!=null){
            return mem[start];
        }
        for(int end = start+1;end<s.length()+1;end++){
            if(set.contains(s.substring(start,end))&&word_Break(s,set,end,mem)){
                mem[start] = true;
                return true;
            }
        }
        return mem[start] = false;
    }


}
