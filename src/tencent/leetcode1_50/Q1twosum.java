package tencent.leetcode1_50;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/6/6
 * @describe 求数组中的两个数 合为给定值
 */
public class Q1twosum {

    public int[] twoSum(int[] nums, int target) {
        //handle corner case
        if(nums==null||nums.length==0){
            return null;
        }

        int[] rst = null;
        int len = nums.length;
        boolean flag = false;
        for(int i=0;i<len;i++){
            int temp1 = i;
            int temp2 = i+1;
            int sub = target - nums[i];
            for(int j=i+1;j<len;j++){
                if(sub==nums[j]){
                    temp2 = j;
                    flag = true;
                }
            }
            if(flag){
                rst = new int[2];
                rst[0] = temp1;
                rst[1] = temp2;
                break;
            }
        }

        return rst;
    }

    private static int[] twoSum02(int[] nums,int target){
        int[] indexArray  = new int[2];
        //handle corner case
        if(nums==null||nums.length==0){
            return null;
        }
        //使用Map存贮
        Map<Integer,Integer> hMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            hMap.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            if(hMap.containsKey(target-nums[i])){
                indexArray[0] = i;
                indexArray[1] = hMap.get(target-nums[i]);
            }
            if(indexArray[0] == indexArray[1]) {
                continue;
            }
            return indexArray; //因为前面的continue导致了没匹配到就跳过这一步
        }
        return null;
    }

    public static void main(String[] args) {
        Q1twosum q1twosum = new Q1twosum();
        int[] nums = {2,7,11,15};
        int target = 26 ;
        int[] rst = q1twosum.twoSum(nums,target);
        System.out.println(Arrays.toString(rst));
        System.out.println("====================");
        int[] rst2 = q1twosum.twoSum02(nums,target);
        System.out.println(Arrays.toString(rst2));
    }
}
