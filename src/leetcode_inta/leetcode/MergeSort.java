package leetcode_inta.leetcode;

/**
 * @author inta
 * @date 2019/12/10
 * @describe
 */
public class MergeSort {
    /**
     * 归并排序
     * @param array 原数组
     * @param left 左起点
     * @param right 右终点
     */
    public static void mergeSort(int[] array, int left, int right) {
        //如果区间只有一个元素或者没有元素，结束函数
        if (left >= right) return;

        //获取该区间的中间值
        int mid = (left + right) / 2;
        //将区间平均分成两半，对左区间进行归并排序
        mergeSort(array, left, mid);
        //对右区间进行归并排序
        mergeSort(array, mid + 1, right);

        //如果归并排序后的左区间与右区间未有序
        if (array[mid] > array[mid + 1]) {
            //将左区间与右区间合并形成有序数组
            merge(array, left, mid, right);
        }
    }

    /**
     * 合并两个区间
     * @param array 原数组
     * @param left 左起点
     * @param mid 中间点
     * @param right 右终点
     */
    public static void merge(int[] array, int left, int mid, int right) {
        //用来存储原数据的临时数组
        int[] temp = new int[right - left + 1];

        //将原数据拷贝到临时数组
//        System.arraycopy(array, left, temp, 0, right - left + 1);
        for (int i = left; i <= right; i++) {
            temp[i - left] = array[i];
        }

        //i为左区间起点，j为右区间起点
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) { //左区间的数已存完
                array[k] = temp[j - left]; //将右区间剩下的数存到数组
                j++; //右区间下标移动
            } else if (j > right) { //右区间的数已存完
                array[k] = temp[i - left]; //将左区间剩下的数存到数组
                i++; //左区间下标移动
            } else if (temp[i - left] < temp[j - left]) { //左区间的值小于右区间
                array[k] = temp[i - left]; //将左区间的值存入数组
                i++; //左区间下标移动
            } else { //右区间的值小于左区间
                array[k] = temp[j - left];
                j++; //右区间下标移动
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        mergeSort(array, 0, array.length - 1); //归并排序

        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
