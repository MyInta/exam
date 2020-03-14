package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/7/23
 * @describe 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
 * 系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，
 * 能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class Q198Rob {
    public int rob(int[] nums){
        int max_nums = 0;
        int pre = 0;
        //关系式为 f(k+2) = max( f(k+1) ,f(k)+i);
        // 即下一个最大值为前一个最大值和前前最大值与先元素和的比较
        for(int i:nums){
            int temp = max_nums;//保留f(k)
            //第一次时f(k+1) = max( f(k) ,0+i),第二次和之后f(k+2) = max( f(k+1) ,f(k)+i)
            max_nums = Math.max(max_nums,pre+i);
            pre = temp;
        }
        return max_nums;
    }

    private static int[] result;
    public int rob2(int[] nums){
        int idx = nums.length-1;
        result = new int[idx+1];
        for(int i=0;i<=idx;i++){
            result[i] = -1;
        }
        return solve(idx,nums);
    }
    private int solve(int idx,int[] nums){
        if(idx<0){
            return 0;
        }
        if(result[idx]>=0){
            return result[idx];
        }
        result[idx] = Math.max(solve(idx-1,nums),nums[idx]+solve(idx-2,nums));
        return result[idx];
    }

    public int rob3(int[] nums){
        //空
        if(nums.length==0){
            return 0;
        }
        //当大于等于两个元素时
        int[] res;
        res = new int[nums.length];
        res[0] = nums[0];

//        if(nums.length==1){
//            return nums[0];
//        }
//        res[1] = Math.max(nums[0],nums[1]);
        //等效替换成以下代码
        if(nums.length>=2){
            res[1] = Math.max(nums[0],nums[1]);
        }

        for(int idx=2;idx<nums.length;idx++){
            res[idx] = Math.max(res[idx-1],nums[idx]+res[idx-2]);
        }
        return res[nums.length-1];
    }

    public static void main(String[] args) {
        Q198Rob q = new Q198Rob();
        int[] nums = {1,2,3,4};
        System.out.println(q.rob(nums));
    }
}
