package leetcode_inta.exam_main.leetcode_exam2020.D198;

import com.sun.javafx.scene.layout.region.LayeredBackgroundSizeConverter;

import java.util.*;

/**
 * @author inta
 * @date 2020/7/19
 * @describe
 */
public class Q3 {
    //依据题意，理解为，寻找不重叠的数组数量最大值情况
    public List<String> maxNumOfSubstrings(String s) {
        if (s.length() == 1) {
            List<String> res = new ArrayList<>();
            res.add(s);
            return res;
        }
        Set<Character> set = new HashSet<>();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);
            if (!set.contains(target)) {
                set.add(target);
                int[] cur = new int[2];
                cur[0] = i;
                int j = s.length() - 1;
                while (true) {
                    if (s.charAt(j) == target) {
                        cur[1] = j;
                        break;
                    }
                    j --;
                }
                lists.add(cur);
            }
        }
//        for (int[] l : lists) {
//            System.out.print(l[0] + "--" + l[1]);
//            System.out.println();
//        }
        List<int[]> newLists = new ArrayList<>();
        //此时获得了数组集合，排序缩小范围
        int mark = 0;
        int left = 0, right = s.length() - 1;
        while (mark < lists.size()) {
            int[] temp = lists.get(mark);
            if (temp[0] >= left && temp[1] <= right) {
                left = temp[0];
                right = temp[1];
            } else if (temp[0] > right) {
                int[] newList = new int[2];
                newList[0] = left;
                newList[1] = right;
                newLists.add(newList);
                left = temp[0];
                right = temp[1];
            } else if (temp[0] >= left && temp[1] - temp[0] <= right - left) {
                right = temp[1];
            } else if (temp[1] <= right && temp[1] - temp[0] <= right - left) {
                left = temp[0];
            }
            mark++;
        }
        int[] newList = new int[2];
        newList[0] = left;
        newList[1] = right;
        newLists.add(newList);
        List<String> res = new ArrayList<>();
        for (int[] n : newLists) {
            res.add(s.substring(n[0], n[1] + 1));
        }
        return res;
    }
}
