package tencent.leetcode;

/**
 * @author inta
 * @date 2019/7/11
 * @describe
 */
public class MySqrt {

    public boolean guess(long x,long y){
        return x*x <= y;
    }

    public int mySqrt(int x){
        long L=0,R=(long)x+1;
        long ans = 0;
        while(L<R){
            long mid = (L+R)/2;
            if(guess(mid,x)){
                ans = mid;
                L = mid+1;
            }else{
                R = mid;
            }
        }
        return (int)ans;
    }

    //牛顿迭代法 x = (x+f(x)/x)/2
    public int mySqrt2(int x){
        long ans = x;
        while(ans*ans>x){
            ans = (ans+x/ans)/2;
        }
        return (int) ans;
    }

}
