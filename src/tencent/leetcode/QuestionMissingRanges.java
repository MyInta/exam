package tencent.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/6/4
 * @describe 给出一个数组，和一个包含该数组元素的区间，返回出区间内缺省的元素
 *          例如：given [0,1,3,50,75] lower=0 and upper = 99,
 *  		return  ["2","4->49","51->74","76->99"]
 */
public class QuestionMissingRanges {
    private static List<String> findMissingRanges(int[] nums,int lower,int upper){
        //步骤一：定义返回值
        List<String> rst = new ArrayList<>();
        //步骤二：考虑特殊情况(没有数组元素)
        if(nums==null||nums.length==0){
            addToList(rst,lower,upper);
        }
        //步骤三：正常情况下，填充逻辑业务
        //先添加lower之后
        addToList(rst,lower,nums[0]-1);
        //再添加在lower和upper之间的
        int prev = nums[0];
        int i = 1;
        while(i<nums.length){
            //指针放到下一个元素处
            int cur = nums[i];
            //只要前后元素不相差1，就添加
            if(prev!=cur-1){
                addToList(rst,prev+1,cur-1);
            }
            //移动指针
            prev = cur;
            i++;
        }
        //最后添加upper之前的
        addToList(rst,nums[nums.length-1]+1,upper);
        return rst;
    }

    /**
     * 将结果添加到数组中
     * @param rst 要添加到的数组对象
     * @param start 开始元素
     * @param end 结束元素
     */
    private static void addToList(List<String> rst,int start,int end){
        //如果前后两参数相等，那么就添加一个元素
        if(start==end){
            rst.add(String.valueOf(start));
        }else if(start<end){ //如果前元素小于后元素，返回x->y形式
            rst.add(start+"->"+end);
        }//再者可能前面大于后面，这种情况就不用添加操作了
    }


    public static void main(String[] args) {
        int[] nums = {0,1,3,50,75};
        int lower = 0;
        int upper = 99;
        List<String> rst = findMissingRanges(nums,lower,upper);
        System.out.println(rst.toString());
    }
}
