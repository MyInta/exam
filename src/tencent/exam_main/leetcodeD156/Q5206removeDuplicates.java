package tencent.exam_main.leetcodeD156;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/9/29
 * @describe 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，
 * 并删除它们，使被删去的字符串的左侧和右侧连在一起。
 *
 * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
 *
 * 在执行完所有删除操作后，返回最终得到的字符串。
 *
 * 本题答案保证唯一。
 */
public class Q5206removeDuplicates {
//    public String removeDuplicates(String s, int k) {
//        ArrayList<Map<Character, Integer>> arr = new ArrayList<>();
//        char[] cs = s.toCharArray();
//        int temp = 0;
//        for (int i = 0; i < cs.length - 1; i++) {
//            if (cs[i + 1] == cs[i]) {
//                temp++;
//                continue;
//            }
//            HashMap<Character, Integer> hm = new HashMap<>();
//            hm.put(cs[i], temp);
//            arr.add(hm);
//            temp = 0;
//        }
//
//    }
}
