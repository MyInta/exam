package tencent.leetcode101_200;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2019/7/22
 * @describe 时间最优
 *
 */
public class Q139WordBreak2 {
    //使用一个缓存空间来去重
    Set<Integer> set;

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<Integer>();
        return word_break(s,set,0,wordDict);
    }

    private boolean word_break(String s,Set<Integer> hs,int start,List<String> ls){
        //一走到尽头得退出啊
        if(start == s.length()){
            return true;
        }
        if(hs.contains(start)){
            return false;
        }
        for(String str:ls){
            //如果字符开头有字典内字符,str为搜索字符前缀，start为开始查找索引位置
            if(s.startsWith(str,start)){
                if(word_break(s,hs,start+str.length(),ls)){
                    return true;
                }else{
                    //说明该索引位置下查找前缀找不到字典匹配的
                    hs.add(start+str.length());
                }
            }
        }
        //全遍历后没有提前返回true，说明没有一条路是完全走通的
        return false;
    }

}
