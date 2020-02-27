package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/27
 * @describe 入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 *  
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *  
 *
 * 提示：
 *
 * 数组长度 <= 1000
 *
 */
public class V33verifyPostorder {
    //递归，核心思想是比较比根结点小的左边往右连续的索引边界和比根节点大的右往左连续的索引边界
    public boolean verifyPostorder(int[] postorder) {
        //空为任何树的子集
        return solution(postorder, 0, postorder.length - 1);
    }
    private boolean solution(int[] p, int start, int end) {
        if (start >= end) return true;
        //根节点为end索引位置元素
        int left = start, right = end - 1;
        //只要左边比根小，索引往后退
        while (left < end && p[left] < p[end]) left ++;
        while (right > start && p[right] > p[end]) right --;
        if (left < right) return false;
        //注意后半段要考虑不再使用原先的根节点
        return solution(p, start, left - 1) && solution(p, right + 1, end - 1);
    }
}
