package leetcode_inta.leetcode701_750;

import java.util.*;

/**
 * @author inta
 * @date 2021/1/18
 * @describe 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，
 * 其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
 * 请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * 示例 1：
 * 输入：
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
 * ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：
 * [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 * ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 *
 * 提示：
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 */
public class Q721accountsMerge {
    // 思路，定义每个邮箱编号，方便进行并查集 一个map保存 email -> 编号 一个map保存email->name
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> email2Number = new HashMap<>();
        Map<String, String> email2Name = new HashMap<>();
        int number = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String curEmail = account.get(i);
                // 保存没保存过的邮箱编码信息
                if (!email2Number.containsKey(curEmail)) {
                    email2Number.put(curEmail, number++);
                    email2Name.put(curEmail, name);
                }
            }
        }

        // 使用并查集合并拥有共同父类邮箱(这里我们选用第一个邮箱)的元素
        UnionFind unionFind = new UnionFind(number);
        for (List<String> account : accounts) {
            String parentEmail = account.get(1);
            int parentNumber = email2Number.get(parentEmail);
            for (int i = 2; i < account.size(); i++) {
                int curNumber = email2Number.get(account.get(i));
                unionFind.merge(parentNumber, curNumber);
            }
        }

        // 搭建父类邮箱(暂时代表独立的个人)->邮箱集合的映射
        Map<Integer, List<String>> email2Emails = new HashMap<>();
        for (String email : email2Number.keySet()) {
            int parentNumber = unionFind.find(email2Number.get(email)); // 获得父类邮箱编号
            email2Emails.computeIfAbsent(parentNumber, key -> new ArrayList<>()).add(email);
        }

        // 从搭建的邮箱->邮箱集合映射以及邮箱->人名中返回答案
        List<List<String>> res = new ArrayList<>();
        for (List<String> emails : email2Emails.values()) {
            // 随便取一个邮箱就可以找回其人名
            String name = email2Name.get(emails.get(0));
            List<String> list = new ArrayList<>();
            list.add(name);
            // 题目有个坑，邮箱需要返回字典序
            Collections.sort(emails);
            list.addAll(emails);
            res.add(list);
        }
        return res;
    }

    /**
     * 并查集
     */
    private class UnionFind {
        int[] parents;
        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n ; i++) {
                this.parents[i] = i;
            }
        }

        public void merge(int x, int y) {
            parents[find(x)] = find(y);
        }

        public int find(int child) {
            if (child != parents[child]) {
                parents[child] = find(parents[child]);
            }
            return parents[child];
        }
    }
}
