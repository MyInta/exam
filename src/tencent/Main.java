package tencent;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a',2);
        int res = map.get('a');
        System.out.println(res);
    }
}
