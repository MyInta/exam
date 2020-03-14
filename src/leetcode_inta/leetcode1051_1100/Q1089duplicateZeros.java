package leetcode_inta.leetcode1051_1100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/20
 * @describe 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 *
 * 注意：请不要在超过该数组长度的位置写入元素。
 *
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * 示例 2：
 *
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 *
 */
public class Q1089duplicateZeros {
    //遇事不决，暴力优先     自然，测试效率极低
    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i ++) {
            if (arr[i] == 0 && i < len - 1) {
                int temp = arr[i + 1];
                arr[i + 1] = 0;
                for (int j = i + 2; j < len; j ++) {
                    int temp2 = arr[j];
                    arr[j] = temp;
                    temp = temp2;
                }
                //向后移动一位
                i ++;
            }
        }
    }

    //自己想的使用集合的方法，发现效率还是提升不明显
    public void duplicateZeros2(int[] arr) {
        int len = arr.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i ++) {
            list.add(arr[i]);
            if (arr[i] == 0 && list.size() < len) {
                list.add(0);
            }
            if (list.size() == len) break;
        }
        for (int j = 0; j < len ; j ++) {
            arr[j] = list.get(j);
        }
    }

    //大神使用的是双指针，分别记录原数组中用到的元素数量和改变之后数组元素最大索引位置
    public void duplicateZeros3(int[] arr) {
        int slow = -1;
        int fast = -1;
        int len = arr.length;
        //当fast触及到数组索引最大值时候，就跳出循环
        while (fast < len - 1) {
            slow ++;
            if (arr[slow] == 0) fast ++;
            fast ++;
        }
        //从上诉的操作可以看到。fast一次可能加两个，那么这种情况下，其值有可能为len而不是我们理想中的len - 1
        //然后我们常规思路，如果需要用到的原数组的元素数量slow是索引规范内的，就填数据
        while (slow >= 0) {
            //从后往前复制
            if (fast < len) arr[fast] = arr[slow];
            //当遇到零的情况，赋值两个0
            if (arr[slow] == 0) arr[-- fast] = arr[slow];

            slow --;
            fast --;
        }
    }

}
