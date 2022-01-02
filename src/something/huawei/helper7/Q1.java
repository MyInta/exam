package something.huawei.helper7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/21
 * @describe
 */
public class Q1 {
    public static List<List<Integer>> method(String[] messages) {
        // 如果有异常场景，直接返回error
        List<List<Integer>> res = new ArrayList<>();
        if (messages.length == 0) {
            return res;
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(Integer.valueOf(messages[0].split("/")[0]));
        boolean flag = true;
        for (int i = 1; i < messages.length; i++) {
            String[] curArr = messages[i].split("/");
            if (curArr[1].equals("Y")) {
                int value = Integer.valueOf(curArr[0]);
                if (flag) {
                    if (list2.contains(value)) {
                        return res;
                    }
                    list1.add(value);
                } else {
                    if (list1.contains(value)) {
                        return res;
                    }
                    list2.add(value);
                }
            } else {
                int value = Integer.valueOf(curArr[0]);
                if (flag) {
                    if (list1.contains(value)) {
                        return res;
                    }
                    list2.add(value);
                } else {
                    if (list2.contains(value)) {
                        return res;
                    }
                    list1.add(value);
                }
                flag = !flag;
            }
        }
        Collections.sort(list1);
        Collections.sort(list2);
        if (list2.isEmpty() || list1.get(0) < list2.get(0)) {
            // 先输出list1，在输出list2
            res.add(list1);
            res.add(list2);
        } else {
            res.add(list2);
            res.add(list1);
        }
        return res;
    }

    // 输入小朋友编号，给小朋友分成两个班，输出升序编号，且最小编号的班级优先输出，输入信息有误直接输出error
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] messages = sc.nextLine().split(" ");
        List<List<Integer>> res = method(messages);
        if (res.size() == 0) {
            System.out.println("ERROR");
        } else {
            for (List<Integer> list : res) {
                for (int num : list) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }
}
