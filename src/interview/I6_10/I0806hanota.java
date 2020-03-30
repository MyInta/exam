package interview.I6_10;

import java.util.List;

/**
 * @author inta
 * @date 2020/3/30
 * @describe 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，
 * 盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。
 * 移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 *
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 *
 * 你需要原地修改栈。
 *
 * 示例1:
 *
 *  输入：A = [2, 1, 0], B = [], C = []
 *  输出：C = [2, 1, 0]
 *
 * 示例2:
 *
 *  输入：A = [1, 0], B = [], C = []
 *  输出：C = [1, 0]
 *
 * 提示:
 *
 *     A中盘子的数目不大于14个。
 *
 */
public class I0806hanota {
    //这是一道递归题，但是我看了题解才有的思路，还比较难以理解细节过程，需要后续加深理解
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        recruit(A.size(), A, B, C);
    }
    //递归体 size代表要移动数量 A从哪儿来 B借助谁 C到哪儿去
    private void recruit(int size, List<Integer> A, List<Integer> B, List<Integer> C) {
        //如果目前尺寸是最后一个，就直接把from给end即可（即A->C）
        if (size == 1) {
            removeTo(A, C);
            return;
        }
        //否则的话，考虑递归，先把A上的n-1个给B，剩下一个也就是A的最大值，给C
        recruit(size - 1, A, C, B);
        //此时A剩下一个，直接给C作为底盘（该底盘是目前需要移动的盘子里最大的）
        removeTo(A, C);
        //然后此时是B有n-1个需要移动归位的盘子，而C的最上面盘子为目前最大值
        recruit(size - 1, B, A, C);
    }
    private void removeTo(List<Integer> list1, List<Integer> list2) {
        //将list1的最后一个添加到list2上
        list2.add(list1.remove(list1.size() - 1));
    }
}
