package leetcode_inta.exam_main.leetcode_exam2020.D180;

/**
 * @author inta
 * @date 2020/3/15
 * @describe
 */
public class Q2 {

    private class CustomStack{

        private int[] counts;
        private int index;
        private int maxIndex;
        public CustomStack(int maxSize) {
            counts = new int[maxSize];
            index = 0;
            maxIndex = maxSize;
        }

        public void push(int x) {
            if (index == maxIndex) return;
            counts[index ++] = x;
        }

        public int pop() {
            if (index == 0) return -1;
            int temp = counts[-- index];
            counts[index] = 0;
            return temp;
        }

        public void increment(int k, int val) {
            if (k > index) k = index;
            for (int i = 0; i < k; i ++) {
                counts[i] += val;
            }
        }

    }
}
