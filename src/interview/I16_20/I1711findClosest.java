package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/18
 * @describe 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 *
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 *
 * 提示：
 *
 *     words.length <= 100000
 *
 */
public class I1711findClosest {
    public int findClosest(String[] words, String word1, String word2) {
        int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            if (str.equals(word1)) {
                left = i;
            } else if (str.equals(word2)) {
                right = i;
            } else continue;
            if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE) {
                res = Math.min(res, Math.abs(right - left));
            }
        }
        return res;
    }
}
