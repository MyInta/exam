package tencent.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/7/26
 * @describe 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Q78Subsets {

    private List<List<Integer>> lists;
    private ArrayList<Integer> arrayList;
    private boolean[] b;
    private void robot(int idx,int[] nums){
        if(idx>nums.length-1){
            arrayList = new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                if(b[i]){
                    arrayList.add(nums[i]);
                }
            }
            lists.add(arrayList);
            return;
        }
        b[idx] = true;
        robot(idx+1,nums);
        b[idx] = false;
        robot(idx+1,nums);
    }

    public List<List<Integer>> subsets(int[] nums) {
        lists = new ArrayList<>();
        b = new boolean[nums.length];
        robot(0,nums);
        return lists;
    }

    private class Q78Subsets2{

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            backtrack(0,nums,res,new ArrayList<Integer>());
            return res;
        }

        private void backtrack(int st,int[] nums,List<List<Integer>> res,ArrayList<Integer> arr){
            res.add(new ArrayList<Integer>(arr));
            for(int i = st;i<nums.length;i++){
                arr.add(nums[i]);
                backtrack(i+1,nums,res,arr);
                //减去数组中最后一个元素
                arr.remove(arr.size()-1);
            }
        }
    }
}
