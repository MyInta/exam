package leetcode_inta.leetcode;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/7/6
 * @describe
 */
public class QuickSort {
    public static void sort(int[] array){
        quickSort(array,0,array.length-1);
    }

    private static void quickSort(int[] array, int left, int right) {
        if(left>=right){
            return;
        }
        //找到第一个前后对比pivot大小并需要更换位置的索引地址
        int index = partition(array,left,right);
        //一切顺利的话，整个数组被分割为比pivot小和大两个阵营
        quickSort(array,left,index-1);
        //对后半段阵营继续递归排序
        quickSort(array,index + 1,right);
    }

    private static int partition(int[] array, int left, int right) {
        int randomIndex = (int) (left + Math.random() * (right - left + 1));
        swap(array, randomIndex, left);
        int pivot = array[left];
        int i = left, j = right + 1;
        //对每个阵营都进行一次全面排序
        while(true){
            while(i < right && array[++ i] < pivot);
            while(j > left && array[-- j] > pivot);
            if (i >= j) break;
            //直到找到左右各一个值与pivot对比后大于等于和小于等于的，对它们进行交换
            swap(array, i, j);
        }
        //此时j为有序状态下真实基准pivot的所在索引位置,将该元素复位
        swap(array, left, j);
        return j;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] test = {5,6,7,3,3,11,23,1,25,14,17,17,2,4};
        int[] test2 = {5,6,7,3,3,11,23,1,25,14,17};
        int[] test3 = {5,6,7,3,3,0,0,1,1,1,2};
        int[] test4 = {7,6,5,4,3,2,1,0};
        QuickSort qs = new QuickSort();
        qs.sort(test);
        qs.sort(test2);
        qs.sort(test3);
        qs.sort(test4);
        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(test2));
        System.out.println(Arrays.toString(test3));
        System.out.println(Arrays.toString(test4));
    }
}
