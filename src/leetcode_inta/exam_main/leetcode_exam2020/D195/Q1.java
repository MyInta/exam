package leetcode_inta.exam_main.leetcode_exam2020.D195;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2020/6/28
 * @describe
 */
public class Q1 {
    public boolean isPathCrossing(String path) {
        Set<Pair> set = new HashSet<>();
        int x = 0, y = 0;
        for (char p : path.toCharArray()) {
            switch (p) {
                case 'N' : if (set.contains(new Pair(x, y + 1))) {
                                return true;
                            } else set.add(new Pair(x, y + 1));
                    break ;
                case 'S' : if (set.contains(new Pair(x, y - 1))) {
                    return true;
                } else set.add(new Pair(x, y - 1));
                    break ;
                case 'E' : if (set.contains(new Pair(x + 1, y))) {
                    return true;
                } else set.add(new Pair(x + 1, y));
                    break ;
                case 'W' : if (set.contains(new Pair(x - 1, y))) {
                    return true;
                } else set.add(new Pair(x - 1, y));
                    break ;
            }
        }
        return false;
    }

    private class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean isPathCrossing2(String path) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int x = 0, y = 0;
        map.put(0, new HashSet<>());
        map.get(0).add(y);
        for (char p : path.toCharArray()) {
            if (p == 'N') {
                y ++;
            } else if (p == 'S') {
                y --;
            } else if (p == 'E') {
                x ++;
            } else if (p == 'W') {
                x --;
            }
            if (!map.containsKey(x)) map.put(x, new HashSet<>());
            if (map.get(x).contains(y)) return true;
            map.get(x).add(y);
        }
        return false;
    }
}
