package tencent;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> l = new ArrayList();
        int i = 2;
        int j = 5;
        int k = 7;
        l.add((double) i);
        l.add((double) j);
        l.add((double) k);
        double res4 = (double) l.get((0 + 3) >> 1);
        System.out.println(res4);
    }
}
