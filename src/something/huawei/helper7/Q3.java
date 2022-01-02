package something.huawei.helper7;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/21
 * @describe
 */
public class Q3 {
    public static String method(int[] nums) {
        // 这些元素需要和索引绑定，因为输出的时候只考虑索引
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            deque.add(new int[]{nums[i], i});
        }
        List<Integer> list = new ArrayList<>();
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            if (cur[0] == pq.peek()) {
                list.add(cur[1]);
                pq.poll();
            } else {
                deque.add(cur);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[list.get(i)] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            sb.append(num).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    // 输入一排任务，再输入这些任务对应的优先级，难点在于发现自己非当前最优，本身会被排到最后
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputArr = sc.nextLine().split(",");
        int[] nums = new int[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            nums[i] = Integer.valueOf(inputArr[i]);
        }
        System.out.println(method(nums));
    }
}
