package leetcode_inta.leetcode851_900;

/**
 * @author inta
 * @date 2019/11/14
 * @describe 有一个特殊的正方形房间，每面墙上都有一面镜子。
 * 除西南角以外，每个角落都放有一个接受器，编号为 0， 1，以及 2。
 *
 * 正方形房间的墙壁长度为 p，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 0 的距离为 q 。
 *
 * 返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。
 * 示例：
 *
 * 输入： p = 2, q = 1
 * 输出： 2
 * 解释： 这条光线在第一次被反射回左边的墙时就遇到了接收器 2
 * 提示：
 *
 * 1 <= p <= 1000
 * 0 <= q <= p
 */
public class Q858mirrorReflection {
    public int mirrorReflection(int p, int q) {
        if (q == 0) return 0;
        //找到p,q的最小公倍数 g
        int g = gcd(p, q);
        //找到最小k，使得kq=np 且n为最小正数 k = pq/g，根据k奇偶性判断光线在左侧还是右侧
        p /= g;
        p &= 1;
        //找到kq/p的奇偶性判断光线在上方还是下方 kq/p = q^2/g的奇偶性=q/g的奇偶性
        q /= g;
        q &= 1;

        if (p == 1 && q == 1) return 1; //右上
        //当p为1，那么q必为偶 反之 q为奇数 即分别对应 右下和左上
        return p == 1 ? 0 : 2;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
