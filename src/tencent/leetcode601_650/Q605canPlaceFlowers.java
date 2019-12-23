package tencent.leetcode601_650;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/12/22
 * @describe 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。
 * 可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 *
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 *
 * 示例 1:
 *
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 示例 2:
 *
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * 注意:
 *
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 *
 */
public class Q605canPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean flag = n <= 0;
        for (int i = 0; i < flowerbed.length; i ++) {
            //如果是起始位置
            if (i == 0) {
                if (i + 1 == flowerbed.length && flowerbed[i] == 0 || flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    n --;
                    flowerbed[i] = 1;
                    if (n == 0) {
                        flag = true;
                        break;
                    }
                }
            } else if (i + 1 == flowerbed.length) {
                //如果是最后一个位置
                if (flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                    n --;
                    flowerbed[i] = 1;
                    if (n == 0) {
                        flag = true;
                        break;
                    }
                }
            } else {
                //非头非尾的情况
                if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    n --;
                    flowerbed[i] = 1;
                    if (n == 0) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    //大神的一次遍历思路，有考虑补位（前后加0的）我选择另一种直接判断索引的
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i ++) {
            if (flowerbed[i] == 0
                    //判断前一个位置是否也是0，考虑为首情况
                    && (i == 0 || flowerbed[i - 1] == 0)
                    //判断后一个位置为0和尾情况
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                n --;
            }
        }
        return n <= 0;
    }

}
