package tencent.exam_main;

import java.util.LinkedList;
import java.util.List;

public class Main_4 {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(0);
        list.add(10);
        list.add(60);
        for (int i : list) {
            System.out.println(i);
        }
        int end = 59;
        int left2 = 0, right2 = list.size();
        while (left2 < right2) {
            int mid = left2 + (right2 - left2) / 2;
            if (list.get(mid) <= end) {
                left2 = mid + 1;
            } else {
                right2 = mid;
            }
        }
        System.out.println(left2);
    }

}
