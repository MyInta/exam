package something.jingdong;

import java.util.Scanner;

/**
 * @author inta
 * @date 2020/9/17
 * @describe
 */
public class Q2 {

    //S E # .
    private static String solution(String[][] map, int n, int m) {
        res = false;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j].equals("S")) {
                    dfs(map, n, m, i, j);
                    return res ? "YES" : "NO";
                }
            }
        }
        return "NO";
    }
    private static boolean res;
    private static boolean[][] visited;
    private static void dfs(String[][] map, int n, int m, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) return;
        visited[x][y] = true;
        if (map[x][y].equals("#")) return;
        if (map[x][y].equals("E")) {
            res = true;
            return;
        }
        dfs(map, n, m, x - 1, y);
        dfs(map, n, m, x + 1, y);
        dfs(map, n, m, x, y - 1);
        dfs(map, n, m, x, y + 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = Integer.valueOf(in.nextLine());
        while (num > 0) {
            String[] titles = in.nextLine().split(" ");
            int n = Integer.valueOf(titles[0]);
            int m = Integer.valueOf(titles[1]);
            String[][] map = new String[n][m];
            for (int i = 0; i < n; i++) {
                String nextLine = in.nextLine();
                String[] splits = nextLine.split("");
                map[i] = splits;
            }
            System.out.println(solution(map, n, m));
            num--;
        }
    }
}
