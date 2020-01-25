package tencent;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(2);
        ll.add(3);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        Deque<Integer> d = new ArrayDeque<>();
        d.add(7);
        d.add(8);
        d.add(9);
        d.add(10);
        Deque<Integer> d2 = new LinkedList<>();
        d2.offer(21);
        d2.offer(22);
        d2.offer(23);
        d2.offer(24);
        while (!ll.isEmpty()) {
            System.out.print(ll.pop() + "-");
        }
        System.out.println();
        System.out.println("deque-----------------------");
        while (!d.isEmpty()) {
            System.out.print(d.pollLast() + "-");
        }
        System.out.println();
        System.out.println("linked Deuqe---------------");
        while (!d2.isEmpty()) {
            System.out.print(d2.pop() + "-");
        }
    }

}
