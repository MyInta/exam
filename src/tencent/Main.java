package tencent;


import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> test = new Stack<>();
        test.add("a");
        test.add("b");
        test.add("c");
        test.add("d");
        for (String s : test) {
            System.out.println(s);
        }
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }

}
