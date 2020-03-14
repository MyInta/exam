package leetcode_inta.leetcode501_550;

/**
 * @author inta
 * @date 2019/12/23
 * @describe 给定一个单词，你需要判断单词的大写使用是否正确。
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 *
 * 示例 1:
 *
 * 输入: "USA"
 * 输出: True
 * 示例 2:
 *
 * 输入: "FlaG"
 * 输出: False
 * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
 */
public class Q520detectCapitalUse {
    //暴力
    public boolean detectCapitalUse(String word) {
        //是否大写开头
        boolean flag1 = false;
        //是否除头外存在大写
        boolean flag2 = false;
        //是否除头外存在小写
        boolean flag3 = false;
        if (word.charAt(0) <= 'Z' && word.charAt(0) >= 'A') flag1 = true;
        for (int i = 1; i < word.length(); i ++) {
            if (word.charAt(i) <= 'Z' && word.charAt(i) >= 'A') {
                flag2 = true;
            } else {
                flag3 = true;
            }
        }
        return flag1 && !flag2 || flag1 && flag2 && !flag3 || !flag2 && flag3 || !flag2 && !flag3;
    }
}
