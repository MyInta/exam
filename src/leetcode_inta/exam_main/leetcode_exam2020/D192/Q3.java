package leetcode_inta.exam_main.leetcode_exam2020.D192;

import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2020/6/7
 * @describe
 */
public class Q3 {

    private class BrowserHistory {

        private List<String> backList;
        private List<String> forwardList;
        private String cur;
        public BrowserHistory(String homepage) {
            cur = homepage;
            backList = new LinkedList<>();
            forwardList = new LinkedList<>();
        }

        public void visit(String url) {
            //清空操作
            forwardList = new LinkedList<>();
            //空或非重复情况添加
            if (backList.isEmpty() || !backList.get(backList.size() - 1).equals(cur)) {
                backList.add(cur);
                cur = url;
            }
        }

        public String back(int steps) {
            if (backList.isEmpty()) return cur;
            if (steps > backList.size()) steps = backList.size();
            while (steps > 0) {
                steps --;
                forwardList.add(cur);
                cur = backList.remove(backList.size() - 1);
            }
            return cur;
        }

        public String forward(int steps) {
            if (forwardList.isEmpty()) return cur;
            if (steps > forwardList.size()) steps = forwardList.size();
            while (steps > 0) {
                steps --;
                backList.add(cur);
                cur = forwardList.remove(forwardList.size() - 1);
            }
            return cur;
        }

    }
}
