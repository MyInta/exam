package something;

/**
 * @author inta
 * @date 2020/3/13
 * @describe
 */
public class Test {

//    int countCharacters(char[] chars, String[] words) {
//        int[] counts = new int[128];
//        for (char c : chars) {
//            counts[c] ++;
//        }
//        int res = 0;
//        for (String word : words) {
//            int[] temp = Arrays.copyOf(counts, counts.length);
//            if (valid(word, temp)) res += word.length();
//        }
//        return res;
//    }
//    private boolean valid(String str, int[] counts) {
//        for (char c : str.toCharArray()) {
//            if (counts[c] == 0) return false;
//            counts[c] --;
//        }
//        return true;
//    }

    public static int BinarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(BinarySearch(nums, 8));
        System.out.println(BinarySearch(nums, 8));
        System.out.println(BinarySearch(nums, 8));
        System.out.println(BinarySearch(nums, 8));
    }

}
