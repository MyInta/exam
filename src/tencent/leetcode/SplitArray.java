package tencent.leetcode;

/**
 * @author inta
 * @date 2019/7/12
 * @describe 给定一个非负整数数组和一个整数 m，
 *          你需要将这个数组分成 m 个非空的连续子数组。
 *          设计一个算法使得这 m 个子数组各自和的最大值最小。
 *          Input:
 *              nums = [7,2,5,10,8]
 *              m = 2
 *          Output:
 *              18
 */
public class SplitArray {
    
    public int splitArray(int[] nums,int m){
        int n = nums.length;
        //创建一个数组，f[i][j]表示i个元素的数组分割成j份，最小的分割子组最大值
        int[][] f = new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                f[i][j] = Integer.MAX_VALUE;
            }
        }
        int[] sub = new int[n+1];
        for(int i=0;i<n;i++){
            sub[i+1] = sub[i]+nums[i];
        }
        f[0][0] =0;
        for(int i=1;i<n+1;i++){
            for(int j =1;j<m+1;j++){
                for(int k=0;k<i;k++){
                    f[i][j] = Math.min(f[i][j],Math.max(f[k][j-1],sub[i]-sub[k]));
                }
            }
        }
        return f[n][m];
    }

    public int splitArray2(int[] nums,int m){
        int n = nums.length;
        long left=0,right=0;
        //找到数组元素中最大值，和总值
        for(int i=0;i<n;i++){
            right+=nums[i];
            if(left<nums[i]){
                left = nums[i];
            }
        }
        //先假定答案是数组总和
        long ans = right;
        while(left<=right){
            //得到mid（数组值介于单元素最大值和数组和之间）
            long mid = (left+right)>>1;
            //用以记录递归时候针对的数组内元素挨个加和
            int sum = 0;
            //记录当前被划分的数组数量
            int cnt = 1;
            //寻找前后累加接近mid的数组元素组合（要求连续）
            for(int i=0;i<n;i++){
                //如果加上遍历的当前元素爆值，开始判断
                if(sum + nums[i]>mid){
                    cnt++;//爆值了，说明需要分割了，数组增加
                    //我们将需要累加的地方清算到该元素，继续在接下来的数组元素中寻找
                    sum = nums[i];
                }else{
                    sum +=nums[i];
                }
            }
            //当把所有元素都遍历按照mid比大小形式分割数组后，我们来看看数组数量和预期是否符合
            if(cnt<=m){//我们分割出来的数组数量比需要的少，说明mid设大了导致的，缩小区间
                right = mid - 1;
                //把答案向mid逼近
                ans = Math.min(ans,mid);
            }else{
                //数组分割出来数量太多，表面这个mid有点小，给他放大
                left = mid+1;
            }
            //上诉过程一直循环，左右两区间值不断靠近，
            // 直到左右大小颠倒退出（因为理论上right和left在mid左右，颠倒说明碰到正确的mid了，即答案）
        }

        return (int)ans;
    }

    public int splitArray3(int[] nums,int m){
        int n = nums.length;
        long left = 0,right = 0;
        for(int i=0;i<n;i++){
            right+=nums[i];
            if(left<nums[i]){
                left = nums[i];
            }
        }
        long ans=right;
        while(left<=right){
            long mid = (left+right)>>1;
            if(guess(nums,mid,m)){
                right = mid-1;
                ans = Math.min(ans,mid);
            }else{
                left = mid+1;
            }
        }
        return (int)ans;
    }

    public boolean guess(int[] nums,long mid,int m){
        long sum = 0;
        int n = nums.length;
        int cnt=1;//这个很重要，初始化数组数量就是1个的
        for(int i=0;i<n;i++){
            if(sum+nums[i]>mid){
                cnt++;
                sum = nums[i];
            }else{
                sum+=nums[i];
            }
        }
        return cnt<=m;
    }

}
