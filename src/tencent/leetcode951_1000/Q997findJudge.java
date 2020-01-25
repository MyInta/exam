package tencent.leetcode951_1000;

/**
 * @author inta
 * @date 2020/1/24
 * @describe 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
 *
 * 如果小镇的法官真的存在，那么：
 *
 * 小镇的法官不相信任何人。
 * 每个人（除了小镇法官外）都信任小镇的法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
 *
 * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：N = 2, trust = [[1,2]]
 * 输出：2
 * 示例 2：
 *
 * 输入：N = 3, trust = [[1,3],[2,3]]
 * 输出：3
 * 示例 3：
 *
 * 输入：N = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 * 示例 4：
 *
 * 输入：N = 3, trust = [[1,2],[2,3]]
 * 输出：-1
 * 示例 5：
 *
 * 输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * 输出：3
 *
 * 提示：
 *
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] 是完全不同的
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 *
 */
public class Q997findJudge {
    //使用出入度的概念来做的，二维数组记录出入度，入度N - 1，出度0的是法官
    public int findJudge(int N, int[][] trust) {
        int[][] degrees = new int[N][2];
        //出入度统计
        for (int[] t : trust) {
            int outDegree = t[0];
            int inDegree = t[1];
            //出度元素为索引位置的出度增加
            degrees[outDegree - 1][0] ++;
            //入度索引位置的入度增加
            degrees[inDegree - 1][1] ++;
        }
        //遍历度 寻找法官
        for (int i = 0; i < degrees.length; i ++) {
            if (degrees[i][0] == 0 && degrees[i][1] == N - 1) return i + 1;
        }
        //没找到，就返回约定的-1
        return -1;
    }

    //一维数组做
    public int findJudge2(int N, int[][] trust) {
        //建立一个所有人信息档案，默认若在trust中发现他指向别人，就初始为-1，表示这人不是法官
        int[] people = new int[N];
        for (int[] t : trust) {
            //需要减1是因为我们定的是索引
            int t0 = t[0] - 1;
            int t1 = t[1] - 1;
            people[t0] = -1;
            //如果目前没看见他指向别人，就先假定他可能是法官，累加他被信任的数量(题意说不会有重复信任情况出现)
            if (people[t1] != -1) {
                people[t1] ++;
            }
        }
        //然后遍历下档案，从中找到一个非-1，且被N-1个村民信任的人，他，是法官
        for (int i = 0; i < N; i ++) {
            if (people[i] == N - 1) return i + 1;
        }
        //否则，说明没有法官
        return -1;
    }
}
