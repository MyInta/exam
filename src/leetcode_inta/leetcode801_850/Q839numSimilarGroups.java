package leetcode_inta.leetcode801_850;

/**
 * @author inta
 * @date 2021/1/31
 * @describe 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
 * 如果这两个字符串本身是相等的，那它们也是相似的。
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，
 * 但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
 * 注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，
 * 要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 *
 * 示例 1：
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * 示例 2：
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 *  
 * 提示：
 * 1 <= strs.length <= 100
 * 1 <= strs[i].length <= 1000
 * sum(strs[i].length) <= 2 * 104
 * strs[i] 只包含小写字母。
 * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 *
 * 备注：
 *       字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
 */
public class Q839numSimilarGroups {
    // 思路：属于异构就归并到一个大家庭，最后返回大家庭个数即为解
    public int numSimilarGroups(String[] strs) {
        int size = strs.length;
        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (areSimilar(strs[i], strs[j])) {
                    unionFind.merge(i, j);
                }
            }
        }
        return unionFind.getCount();
    }

    // 判断是否相似
    private boolean areSimilar(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            if (count > 2) {
                break;
            }
        }
        return count <= 2;
    }

    private class UnionFind {
        int[] parents;
        int count;

        public UnionFind(int n) {
            this.parents = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        public int find(int child) {
            if (parents[child] != child) {
                parents[child] = find(parents[child]);
            }
            return parents[child];
        }

        public void merge(int x, int y) {
            int pX = find(x);
            int pY = find(y);
            if (pX == pY) {
                return;
            }
            parents[pX] = pY;
            count--;
        }
    }
}
