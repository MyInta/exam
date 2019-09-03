package tencent;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,4,5};
        for (int i = 0; i < 3; i++) {
            nums[++i] = 7;
        }
        System.out.println(Arrays.toString(nums));
    }

}
