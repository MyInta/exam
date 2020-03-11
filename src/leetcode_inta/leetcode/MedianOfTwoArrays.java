package leetcode_inta.leetcode;

/**
 * @author inta
 * @date 2019/7/2
 * @describe
 */
public class MedianOfTwoArrays {

        public double findMedianSortedArrays(int A[], int B[]) {
            int lengthA = A.length;
            int lengthB = B.length;
            if ((lengthA + lengthB) % 2 == 0) {
                double r1 = (double) findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2);
                double r2 = (double) findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2 + 1);
                return (r1 + r2) / 2;
            } else
                return findMedianSortedArrays(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB + 1) / 2);
        }

        public int findMedianSortedArrays(int A[], int startA, int endA, int B[], int startB, int endB, int k) {
            int n = endA - startA;
            int m = endB - startB;

            if (n <= 0)
                return B[startB + k - 1];
            if (m <= 0)
                return A[startA + k - 1];
            if (k == 1)
                return A[startA] < B[startB] ? A[startA] : B[startB];

            int midA = (startA + endA) / 2;
            int midB = (startB + endB) / 2;

            if (A[midA] <= B[midB]) {
                if (n / 2 + m / 2 + 1 >= k)//[TODO] 未能理解该行意义
                    return findMedianSortedArrays(A, startA, endA, B, startB, midB, k);
                else
                    return findMedianSortedArrays(A, midA + 1, endA, B, startB, endB, k - n / 2 - 1);
            } else {
                if (n / 2 + m / 2 + 1 >= k)
                    return findMedianSortedArrays(A, startA, midA, B, startB, endB, k);
                else
                    return findMedianSortedArrays(A, startA, endA, B, midB + 1, endB, k - m / 2 - 1);

            }
        }

        public double findMedianSortedArrays02(int A[], int B[]) {
            int lengthA = A.length;
            int lengthB = B.length;
            if ((lengthA + lengthB) % 2 == 0) {
                double r1 = (double) findMedianSortedArrays02(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2);
                double r2 = (double) findMedianSortedArrays02(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB) / 2 + 1);
                return (r1 + r2) / 2;
            } else
                return findMedianSortedArrays02(A, 0, lengthA, B, 0, lengthB, (lengthA + lengthB + 1) / 2);
         }
         public int special(int[] A,int startA,int endA,int[] B,int startB,int endB,int k){
            int midA = (endA+startA)/2;
            int midB = (endB+startB)/2;
            if(A[midA]<=B[midB]){
                 startA = midA+1;
                 k = k-midA-1;
             }else{
                 startB = midB+1;
                 k= k-midB-1;
             }
             return findMedianSortedArrays02(A,startA,endA,B,startB,endB,k);
         }
//         public int special(int i,int[] A,int startA,int endA,int[] B,int startB,int endB,int k){
//             if(A[startA]<=B[startB+i]){
//                 startA = startA+1;
//                 k = k-1;
//             }else{
//                 startB = startB+i;
//                 k= k-i-1;
//             }
//             return findMedianSortedArrays02(A,startA,endA,B,startB,endB,k);
//         }

        public int findMedianSortedArrays02(int[] A,int startA,int endA,int[] B,int startB,int endB,int k){
            int n = endA - startA;
            int m = endB - startB;
            if(n == 0){
                return B[startB+k-1];
            }
            if(m == 0){
                return A[startA+k-1];
            }
            if(k==1){
                return A[startA+k-1]<B[startB+k-1]?A[startA+k-1]:B[startB+k-1];
            }
            int i = k/2 - 1;
            if(i>n-1){
                return special(A,startA,endA,B,startB,endB,k);
            }
            if(i>m-1){
                return special(B,startB,endB,A,startA,endA,k);
            }

            if(A[startA+i]<=B[startB+i]){
                startA = startA+i+1;
                k = k-i-1;
            }else{
                startB = startB+i+1;
                k= k-i-1;
            }
            return findMedianSortedArrays02(A,startA,endA,B,startB,endB,k);

        }


    public static void main(String[] args) {
        MedianOfTwoArrays medianOfTwoArrays = new MedianOfTwoArrays();
        int[] j = {2,4,5,6};
        int[] i = {1,3,5,6};
        System.out.println(medianOfTwoArrays.findMedianSortedArrays(i,j));
        System.out.println("=================");
        System.out.println(medianOfTwoArrays.findMedianSortedArrays02(i,j));
    }

}
