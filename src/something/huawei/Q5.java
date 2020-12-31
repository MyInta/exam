package something.huawei;

/**
 * @author inta
 * @date 2020/11/16
 * @describe
 */
public class Q5 {

    //升序数组，找两数和为target,如果不存在呢：依照题意决定，这里假定返回[0,0]
    public static int[] find(int[] arr, int target) {
        //长度小于两个的，不符合题意，返回[0,0]
        if (arr.length < 2) return new int[2];
        int[] res = new int[2];
        int left = 0, right = arr.length - 1;
        int sum;
        while (left < right) {
            sum = arr[left] + arr[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                res[0] = arr[left];
                res[1] = arr[right];
                break;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6};
        int[] arr2 = {1,22,33,42,5,6};
        int[] arr3 = {1,2,32,42,55,6};
        int[] arr4 = {1,2,33,44,5,6};

        int[] test1 = find(arr1, 7);
        int[] test2 = find(arr1, 9);
        int[] test3 = find(arr1, 10);
        System.out.println(test1[0] + "-" + test1[1]);
    }
}
