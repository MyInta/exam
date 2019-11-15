package tencent.leetcode1051_1100;

/**
 * @author inta
 * @date 2019/11/15
 * @describe 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。
 * 由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
 *
 * 这儿有一份行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 
 * 包含了你的第 i 次行程信息：
 *
 * 必须接送的乘客数量；
 * 乘客的上车地点；
 * 以及乘客的下车地点。
 * 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
 *
 * 请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所用乘客的任务
 * （当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 * 示例 3：
 *
 * 输入：trips = [[2,1,5],[3,5,7]], capacity = 3
 * 输出：true
 * 示例 4：
 *
 * 输入：trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
 * 输出：true
 *  
 *
 * 提示：
 *
 * 你可以假设乘客会自觉遵守 “先下后上” 的良好素质
 * trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 */
public class Q1094carPooling {
    //核心在于记录出发地点上人数和容量的关系
    public boolean carPooling(int[][] trips, int capacity) {
        //因为距离不大于1000
        int[] paths = new int[1001];
        //将每个地点上的人数注入
        for (int[] trip : trips) {
            //出发地添加人数
            paths[trip[1]] += trip[0];
            //目的地减少人数
            paths[trip[2]] -= trip[0];
        }
        //从出发点开始遍历，查看是否人数超标
        for (int i = 1; i < paths.length; i ++) {
            //如果该地点上人数为正，代表需要消耗座位，为负代表补充座位
            capacity -= paths[i];
            //如果座位为负，代表不够坐
            if (capacity < 0) {
                return false;
            }
            // 因为题意出发地<目的地，不用担心凭空给车子“多”了几个座位,不存在capacity大于初始值情况
        }
        return true;
    }
}
