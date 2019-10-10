package tencent;


import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList res = new LinkedList();
        res.add(1);
        res.add(2);
        res.add(3);
        res.add(4);
        res.remove();
        System.out.println(res.toString());
    }
}
