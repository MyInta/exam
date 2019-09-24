package tencent;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        boolean i = map.containsKey(4);
        System.out.println(i);
    }
}
