package tencent.leetcode751_800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/6
 * @describe 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 * 同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1:
 *
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 *
 */
public class Q763partitionLabels {
    //我的想法，遍历一遍，将每个元素对应右边最大索引位置都找出来，第二次遍历从左到右，只要在限定距离内，就是一个片段
    //效果拔群，但是我的语言表达上还是有点混乱，希望随着编码功力提升可以有所优化
    public List<Integer> partitionLabels(String S) {
        int[] count = new int[26];
        char[] sChars = S.toCharArray();
        //一轮遍历，将所有最远距离找到
        for (int i = 0; i < sChars.length; i ++) {
            count[sChars[i] - 'a'] = i;
        }
        //二轮开始切片
        List<Integer> res = new ArrayList<>();
//        //标记是否找到符合的字符串
//        boolean findOne = false;
//        int left = 0, right = count[sChars[0] - 'a'], start = 0, end = sChars.length;
//        while (left < end) {
//            //只要在start - right内发现有元素距离大于right的，就更新
//            if (count[sChars[left] - 'a'] > right) {
//                right = count[sChars[left] - 'a'];
//            }
//            //每次查看完当前元素，索引往后移一位
//            left ++;
//            //如果left超过right限值了，说明要添加新片段
//            if (left > right) {
//                findOne = true;
//            }
//            if (findOne) {
//                res.add(right - start + 1);
//                findOne = false;
//                //从下一个元素开始计算新的长度
//                start = left;
//            }
//        }
        int pre = -1, maxIndex = 0;
        for (int i = 0; i < sChars.length; i ++) {
            maxIndex = Math.max(maxIndex, count[sChars[i] - 'a']);
            if (i == maxIndex) {
                res.add(i - pre);
                pre = i;
            }
        }
        return res;
    }
}
