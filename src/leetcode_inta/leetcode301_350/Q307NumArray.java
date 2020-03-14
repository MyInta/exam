package leetcode_inta.leetcode301_350;

/**
 * @author inta
 * @date 2019/7/22
 * @describe 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 *
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 */
public class Q307NumArray {
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * obj.update(i,val);
     * int param_2 = obj.sumRange(i,j);
     */
    int[] nums;
    public Q307NumArray(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int res = 0;
        for(int start = i;start<=j;start++){
            res+=nums[start];
        }
        return res;
    }

    private class Q307NumArray2{

        int[] nums;
        int[] mark;
        public Q307NumArray2(int[] nums) {
            this.nums = nums;
            mark = new int[nums.length+1];
            mark[0] = 0;
            for(int i=0;i<nums.length;i++){
                mark[i+1] = mark[i]+nums[i];
            }
        }

        public void update(int i, int val) {
            int change = nums[i]-val;
            nums[i] = val;
            for(int start = i+1;start<=nums.length;start++){
                mark[start]-=change;
            }
        }

        public int sumRange(int i, int j) {
            if(j>i){
                return mark[j+1]-mark[i];
            }else{
                return nums[i];
            }
        }
        //["NumArray","sumRange","update","sumRange"]
        //[[[-1]],[0,0],[0,1],[0,0]]
        //["NumArray","sumRange","update","sumRange"]
        //[[[1,3,5,6,7]],[0,2],[1,2],[0,2]]

    }

}
