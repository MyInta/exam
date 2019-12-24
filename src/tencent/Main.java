package tencent;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        List l = null;
        List l2 = null;
        List l3 = null;
        List l4 = null;
        List l5 = new ArrayList();
        l5.add("ad");
        Queue<List> q = new LinkedList<>();
        q.add(l);
        q.add(l2);
        q.add(l3);
        q.add(l4);
        q.add(l5);

        System.out.println(q.size());
        for (List list : q) {
            System.out.println(list);
        }

    }

}
