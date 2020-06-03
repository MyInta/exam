package leetcode_inta.leetcode751_800;

import com.sun.jdi.event.StepEvent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author inta
 * @date 2020/6/3
 * @describe 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 *
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 *
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 * 提示：
 *
 *     死亡列表 deadends 的长度范围为 [1, 500]。
 *     目标数字 target 不会在 deadends 之中。
 *     每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 *
 */
public class Q752openLock {
    //bfs和双向bfs优化
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        Set<String > deadSet = new HashSet<>();
        for (String dead : deadends) {
            deadSet.add(dead);
        }
//        if (deadSet.contains("0000")) return -1;
        queue.add("0000");
        set.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                if (temp.equals(target)) return step;
                if (deadSet.contains(temp)) continue;
                for (int j = 0; j < 4; j++) {
                    String upStr = upone(temp, j);
                    String downStr = downone(temp, j);
//                    if (upStr.equals(target) || downStr.equals(target)) return step + 1;
//                    if (!set.contains(upStr) && !deadSet.contains(upStr)) {
//                        set.add(upStr);
//                        queue.add(upStr);
//                    }
//                    if (!set.contains(downStr) && !deadSet.contains(downStr)) {
//                        set.add(downStr);
//                        queue.add(downStr);
//                    }
                    if (!set.contains(upStr)) {
                        set.add(upStr);
                        queue.add(upStr);
                    }
                    if (!set.contains(downStr)) {
                        set.add(downStr);
                        queue.add(downStr);
                    }
                }
            }
            //往下一层寻找，步数添加1；
            step ++;
        }
        return -1;
    }

    //向上拨动一位
    private String upone(String str, int i) {
        char[] chars = str.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return String.valueOf(chars);
    }

    //向下拨动一位
    private String downone(String str, int i) {
        char[] chars = str.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return String.valueOf(chars);
    }

    //双向bfs
    public int openLock2(String[] deadends, String target) {
        //死亡清单
        Set<String> deadSet = new HashSet<>();
        for (String dead : deadends) deadSet.add(dead);
        //是否拜访过
        Set<String> visited = new HashSet<>();
        //方向1和方向2
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add("0000");
        q2.add(target);
        int step = 0;
        //这里我们默认遍历方向是先从q1到q2的
        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> nextLine = new HashSet<>();
            //一个开头简单的优化，从宽度最少的地方开始遍历
            if (q1.size() > q2.size()) {
                Set<String> temp = q1;
                q1 = q2;
                q2 = temp;
            }
            for (String str : q1) {
                if (deadSet.contains(str)) continue;
                //此时该字符串认为访问过了
                visited.add(str);
                if (q2.contains(str)) return step;
                //考虑是否添加新节点
                for (int i = 0; i < 4; i++) {
                    String upStr = upone(str, i);
                    String downStr = downone(str, i);
                    //防治重复遍历，看看是不是改成了之前的模样
                    if (!visited.contains(upStr)) nextLine.add(upStr);
                    if (!visited.contains(downStr)) nextLine.add(downStr);
                }
            }
            //到下一层遍历，倒置方向
            q1 = q2;
            q2 = nextLine;
            step ++;
        }
        return -1;
    }
}
