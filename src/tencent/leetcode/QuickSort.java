package tencent.leetcode;

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
        //选取数组中间为参照，一般是随机选取，这把特殊值化了
        int pivot = array[(left+right)/2];
        //找到第一个前后对比pivot大小并需要更换位置的索引地址
        int index = partition(array,left,right,pivot);
        //一切顺利的话，整个数组被分割为比pivot小和大两个阵营
        quickSort(array,left,index-1);
        //对后半段阵营继续递归排序
        quickSort(array,index,right);
    }

    private static int partition(int[] array, int left, int right, int pivot) {
        //对每个阵营都进行一次全面排序
        while(left<=right){
            while(array[left]<pivot){
                left++;
            }
            while(array[right]>pivot){
                right--;
            }
            //直到找到左右各一个值与pivot对比后大于等于和小于等于的，对它们进行交换
            if(left<=right){
                swap(array,left,right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] test = {5,6,7,3,3,11,23,1,25,14,17,17,2,4};
        QuickSort qs = new QuickSort();
        qs.sort(test);
        System.out.println(Arrays.toString(test));
    }
}
