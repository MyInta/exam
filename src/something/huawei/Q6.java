package something.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/11/25
 * @describe
 */
public class Q6 {

    private static Map<Character, Character> map;

    public static void build(char c1, char c2) {
        map.put(c1, c2);
    }


    //[{(

    public static boolean check(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (stack.isEmpty()) {
                if (!map.containsKey(c)) return false;
                stack.push(c);
            } else if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char temp = stack.peek();
                //ASCII 规律 () 距离1 距离2
                if (c - temp > 2 || c- temp < 1) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        String str1 = "";
        String str2 = "(";
        String str3 = "}";
        String str4 = "{([])}";
        String str5 = "{([)]}";
        String str6 = "{([])";
        String str7 = "}])";
        String str8 = "{(()[])}";
        String str9 = "{(())[])}";


        System.out.println(check(str1));
        System.out.println(check(str2));
        System.out.println(check(str3));
        System.out.println(check(str4));
        System.out.println(check(str5));
        System.out.println(check(str6));
        System.out.println(check(str7));
        System.out.println(check(str8));
        System.out.println(check(str9));
    }
}
