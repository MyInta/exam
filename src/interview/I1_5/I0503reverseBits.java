package interview.I1_5;

/**
 * @author inta
 * @date 2020/4/2
 * @describe 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 *
 * 示例 1：
 *
 * 输入: num = 1775(110111011112)
 * 输出: 8
 *
 * 示例 2：
 *
 * 输入: num = 7(01112)
 * 输出: 4
 *
 */
public class I0503reverseBits {
    //记录三个变量，分别是最大值、当前遍历到的段落长度以及上次翻转01后的段落长度
    public int reverseBits(int num) {
        //bit统计的是32位
        int max = 0, cur = 0, pre = 0, bit = 32;
        while (bit -- > 0) {
            //遍历下一个
            cur ++;
            //如果遍历到新的0位置，更新cur舍去之前累加的值pre
            if ((num & 1) == 0) {
                cur -= pre;
                //新的0位置变更为1，pre记录此时cur+1的长度，即作为下一次遍历到0时候的上一次状态记录
                pre = cur;
            }
            num >>= 1;
            max = Math.max(max, cur);
        }
        return max;
    }
}
