package tencent;


import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        int res = (int) l.pollLast();
        int res2 = (int) l.peekFirst();
        int res3 = (int) l.peek();
        int res4 = (int) l.pop();
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
    }
}
