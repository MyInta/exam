package tencent.leetcode1101_1150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/4
 * @describe 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 *
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 *
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 *
 *
 *
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：label = 14
 * 输出：[1,3,4,14]
 * 示例 2：
 *
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 *  
 *
 * 提示：
 *
 * 1 <= label <= 10^6
 */
public class Q1104pathInZigZagTree {
    //思路：父节点为label / 2值的对称点，难点在于我怎么找对称点
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new LinkedList<>();
        //label所在行根据等比例求和公式可以找到 2^n 满足2^(n-1) < label + 1 而2^n > label + 1
        int n = 0;
        int count = 1;
        while (count <= label) {
            count <<= 1;
            n ++;
        }
        //此时n即为label所在行数，首行数作1
        res.add(label);
        while (n > 1) {
            int parent = label / 2;
            int symmetry = findSym(parent, n);
            res.add(0, symmetry);
            label = symmetry;
            n --;
        }
        return res;
    }
    //如何找到其对称点呢，我考虑使用这一层起点值加上末端值减去parent的数目，即为对称值
    private int findSym(int p, int dept) {
        int maxNum = 1;
        while (dept > 1) {
            maxNum <<= 1;
            dept --;
        }
        //maxNum / 2 为上一层末端+1 如第五层的上一层第四层的15对应的16
        return maxNum / 2 + maxNum - 1 - p;
    }
}
