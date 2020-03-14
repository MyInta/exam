package leetcode_inta.leetcode1251_1300;

/**
 * @author inta
 * @date 2020/1/14
 * @describe 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
 *
 * 完成所有替换操作后，请你返回这个数组。
 *
 *  
 *
 * 示例：
 *
 * 输入：arr = [17,18,5,4,6,1]
 * 输出：[18,6,6,6,1,-1]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i] <= 10^5
 *
 */
public class Q1299replaceElements {
    //从右往左保存最大的元素
    public int[] replaceElements(int[] arr) {
        int[] max = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i --) {
            if (max[i + 1] >= arr[i + 1]) {
                //如果后一个记录的最大元素比当前元素大于等于，就设定当前元素最大值为前一个元素值
                max[i] = max[i + 1];
            } else {
                //若小于就赋值最大的
                max[i] = arr[i + 1];
            }
        }
        //至此，max所有元素都已经代表右侧最大的元素
        max[arr.length - 1] = -1;
        return max;
    }

    //不需要数组保存，直接单值保存右侧最大即可
    public int[] replaceElements2(int[] arr) {
        int max = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i --) {
            //记录当前值原先的值
            int temp = arr[i];
            arr[i] = max;
            //比较原先值和最大值的大小，保留最大值
            if (temp > max) {
                max = temp;
            }
        }
        return arr;
    }
}
