package something.jingdong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author inta
 * @date 2020/9/17
 * @describe
 */
public class Q1 {

    //1000-3999
    private static List<String> solution(String str) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        List<String> result = new ArrayList<>();
        boolean check = false;
        for (char c : str.toCharArray()) {
            if ((c < '0' || c > '9') && check) {
                res.add(sb.toString());
                sb = new StringBuilder();
                check = false;
                continue;
            }
            if (c >= '0' && c <= '9') {
                check = true;
                sb.append(c);
            }
        }
        if (sb.length() > 0) res.add(sb.toString());
        for (String s : res) {
            int v = Integer.valueOf(s);
            if (v >= 1000 && v < 4000) result.add(s);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String n = in.next();
            List<String> temp = solution(n);
            if (temp.size() > 0) {
                for (int i = 0; i < temp.size(); i++) {
                    System.out.print(temp.get(i));
                    if (i != temp.size() - 1) System.out.print(" ");
                }
            }
        }
    }
}
