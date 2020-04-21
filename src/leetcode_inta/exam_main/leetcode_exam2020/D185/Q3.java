package leetcode_inta.exam_main.leetcode_exam2020.D185;

/**
 * @author inta
 * @date 2020/4/19
 * @describe
 */
public class Q3 {
    //用暴力解法，一遍寻找第一只青蛙的所有信息，并且给访问过的位置标志下，
    // 以此类推，累加青蛙数量，如果出现未访问过，但是非青蛙叫，直接返回-1
    private boolean[] visited;
    public int minNumberOfFrogs(String croakOfFrogs) {
        int length = croakOfFrogs.length();
        visited = new boolean[length];
        int res = 0;
        for (int i = 0; i < length; i++) {
            solution(croakOfFrogs, i);
            if (flag) {
                System.out.println("hello添加res");
                res++;
            } else {
                //如果有没被访问的，但是不是青蛙
                if (changed) {
                    return -1;
                } else {
                    return res;
                }
            }
            //重置
            changed = false;
            flag = false;
        }
        return res;
    }
    //标记下是否还有未访问过的
    private boolean changed;
    //有无青蛙
    private boolean flag;
    private void solution(String croakOfFrogs, int start) {
        for (int c = start; c < croakOfFrogs.length(); c++) {
            if (!changed && !visited[c]) changed = true;
            if (!visited[c] && croakOfFrogs.charAt(c) == 'c') {
                for (int r = c + 1; r < croakOfFrogs.length(); r++) {
                    if (!visited[r] && croakOfFrogs.charAt(r) == 'r') {
                        for (int o = r + 1; o < croakOfFrogs.length(); o++) {
                            if (!visited[o] && croakOfFrogs.charAt(o) == 'o') {
                                System.out.println("helloo");
                                for (int a = o + 1; a < croakOfFrogs.length(); a++) {
                                    if (!visited[a] && croakOfFrogs.charAt(a) == 'a') {
                                         for (int k = a + 1; k < croakOfFrogs.length(); k++) {
                                             if (!visited[k] && croakOfFrogs.charAt(k) == 'k') {
                                                 System.out.println("hello1");
                                                 flag = true;
                                                 visited[c] = true;
                                                 visited[r] = true;
                                                 visited[o] = true;
                                                 visited[a] = true;
                                                 visited[k] = true;
                                                solution(croakOfFrogs, k + 1);
                                             }
                                         }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
