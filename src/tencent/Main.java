package tencent;


public class Main {

    //找字符串数量
    public static int countStr(String str, String s) {
        int res = 0;
        int index = 0;
        while (index >= 0 && index <= s.length() && s.indexOf(str, index) >= 0) {
            index = s.indexOf(str, index) + 1;
            res ++;
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(countStr("ab", "aabcabcab"));
        String s = "abc";
        System.out.println(s.indexOf("c", 3));
    }

}
