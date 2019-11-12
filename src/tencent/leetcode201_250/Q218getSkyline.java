package tencent.leetcode201_250;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/10/18
 * @describe 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。
 * 现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，
 * 请编写一个程序以输出由这些建筑物形成的天际线（图B）。
 * 每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，
 * 其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。
 * 可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。
 * 您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
 *
 * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
 *
 * 输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，
 * 它们唯一地定义了天际线。关键点是水平线段的左端点。请注意，
 * 最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。
 * 此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 *
 * 例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
 *
 * 说明:
 *
 * 任何输入列表中的建筑物数量保证在 [0, 10000] 范围内。
 * 输入列表已经按左 x 坐标 Li  进行升序排列。
 * 输出列表必须按 x 位排序。
 * 输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...]
 * 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 */
public class Q218getSkyline {
    //看了N多大佬解题，还是一头雾水，但多少明白了一点点，先敲着理思路
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings.length == 0) return res;
        PriorityQueue<Pair> prior = new PriorityQueue<>((a, b) -> {
            //优先比较坐标
            if (a.pos != b.pos) {
                return a.pos - b.pos;
            } else if (a.height != b.height) {
                //再比较高度
                return a.height - b.height;
            } else {
                //若都相等，再返回0
                return 0;
            }
        });
        //遍历并填充prior，想要左右坐标有区分，就规定左坐标的高度为负，右坐标正常即可
        for (int[] build : buildings) {
            prior.add(new Pair(build[0], -build[2]));
            prior.add(new Pair(build[1], build[2]));
        }
        //高度也记录下来，从高到低
        PriorityQueue<Integer> p_height = new PriorityQueue<>((a, b) -> b - a);
        p_height.add(0);
        //记录前一个最高的高度
        int pre = 0;
        //准备接受新的转折点
        List<Integer> list;
        while (!prior.isEmpty()) {
            Pair p = prior.poll();
            //如果是负数，说明是左坐标，添加高度，反之删去对应高度
            if (p.height < 0) {
                p_height.add(-p.height);
            } else {
                //因为高度题意给是大于0的，不用考虑为0
                p_height.remove(p.height);
            }
            //当前一个最高高度和当前最高度不相等，说明出现了转折点，需要记录下来，并同步最高点
            if (pre != p_height.peek()) {
                pre = p_height.peek();
                list = new ArrayList<>();
                list.add(p.pos);
                list.add(pre);
                res.add(list);
            }
        }
        return res;
    }

    /**
     * 用来记录坐标和对应的高度，在设置的时候，左边界高度设为负值，右边界正常
     */
    private class Pair{
        int pos;
        int height;
        Pair(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }
}
