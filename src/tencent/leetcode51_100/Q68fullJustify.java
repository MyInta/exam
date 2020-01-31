package tencent.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/31
 * @describe 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class Q68fullJustify {
    //其实我感觉也不难，思路想好后实现即可
    //整理下思路：两个指针记录单词遍历情况，每次遍历单词统计长度，注意长度限值是添加间隔空格后小于限值
    //在指针区间内单词准备进入添加字符串操作，根据当前单词加间隔空格长度-限值内剩余空格，使用数组记录每个间隔空格数
    //为了解决最后一行问题，需要追溯到一开始遍历单词时候右指针判定，只要在右边界内正常操作，一旦到右边界跳出循环
    //最后一行添加方式自定义，单词加一个空格，期间判断长度得在范围内，剩余部分空格补齐
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        //记录单词数量
        int size = words.length;
        //两个指针用来遍历单词情况
        int left = 0, right = 0;
        //记录目前行内单词和空格长度
        int rowLen = 0;
        while (right < size) {
            //如果单行一来这单词就是长度极限了，直接添加即可
//            if (rowLen == 0 && words[right].length() == maxWidth) {
//                res.add(words[right]);
//                right ++;
//                continue;
//            }
            //遍历的下一个单词符合长度限值内，+1是因为新单词前要有空格
            if (rowLen + words[right].length() <= maxWidth) {
                //统计的行内长度增加
                rowLen += words[right].length() + 1;
                //准备遍历下一个单词
                right ++;
            } else {
                //若超过界限，我们要开启添加单词操作，先记录下有多少个单词
                int nums = right - left;
                //如果单词为一个，直接添加单词和补全空格即可
                if (nums == 1) {
                    //该单词后需要补全的空格数量
                    int emptyNum = maxWidth - words[left].length();
                    StringBuilder sb = new StringBuilder();
                    sb.append(words[left]);
                    while (emptyNum > 0) {
                        sb.append(" ");
                        emptyNum --;
                    }
                    res.add(sb.toString());
                } else {
                    //否则需要考虑空格数量的均衡排布,使用数组来记录空格数
                    int[] emptyNums = new int[nums - 1];
                    //该区间内单词加间隔空格后需要补全的空格数量
                    int emptyNum = maxWidth - rowLen + 1;
                    //每个空格区间需要添加的基本数量 后面+1是把原先保证最少间隔1个空格数量补上
                    int n = emptyNum / (nums - 1) + 1;
                    //以及可能的多的数要优先分配到左边
                    int resume = emptyNum % (nums - 1);
                    //开始赋值
                    for (int i = 0; i < emptyNums.length; i ++) {
                        emptyNums[i] = n;
                    }
                    for (int i = 0; i < resume; i ++) {
                        emptyNums[i] ++;
                    }
                    //终于到了添加字符串的操作
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < emptyNums.length; i ++) {
                        sb.append(words[left + i]);
                        for (int j = 0; j < emptyNums[i]; j ++) {
                            sb.append(" ");
                        }
                    }
                    //当然别忘了补上最后一个单词
                    sb.append(words[right - 1]);
                    res.add(sb.toString());
                }
                //每次操作完，我们都要重置当前行记录长度
                rowLen = 0;
                //并且更新left情况
                left = right;
            }
        }
        //跳出循环，意味着这将是最后一行需要添加的信息，仍旧统计单词数和空格操作
        int nums = right - left;
        //因为不需要考虑均匀排布空格的问题，添加很简单,统计空格数,
        // +1原因和上面一样，是rowLen记录的是一个单词一个空格的组合
        int emptyNum = maxWidth - rowLen + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums - 1; i ++) {
            sb.append(words[left + i]).append(" ");
        }
        sb.append(words[right - 1]);
        while (emptyNum > 0) {
            sb.append(" ");
            emptyNum --;
        }
        res.add(sb.toString());
        return res;
    }
}
