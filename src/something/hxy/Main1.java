package something.hxy;

import java.util.*;

/**
 * @author hxy
 * @date 2021/12/18
 * @describe
 */
public class Main1 {

    private static final int BIG = 1024;

    private static final String SPLIT = ",";

    private static final String SPLIT_2 = ":";

    static class Node implements Comparable<Node>{
        private int capacity;
        private int num;

        public Node(int capacity, int num) {
            this.capacity = capacity;
            this.num = num;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return this.capacity - o.capacity;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "capacity=" + capacity +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str == null || str.isEmpty()) {
            return;
        }
        String useStr = sc.nextLine();
        if (useStr == null || useStr.isEmpty()) {
            return;
        }
        String[] arr = str.split(SPLIT);
        List<Node> list = new ArrayList<>(BIG);
        String[] tmpArr;
        for(String tmp: arr) {
            if (tmp.isEmpty()) {
                continue;
            }
            tmpArr = tmp.split(SPLIT_2);
            if (tmpArr.length<2) {
                continue;
            }
            list.add(new Node(Integer.parseInt(tmpArr[0]),Integer.parseInt(tmpArr[1])));
        }
        Collections.sort(list);

        StringJoiner joiner = new StringJoiner(SPLIT);
        arr = useStr.split(SPLIT);
        for (String tmp: arr) {
            if (tmp.isEmpty()) {
                joiner.add(String.valueOf(Boolean.TRUE));
                continue;
            }
            joiner.add(use(Integer.parseInt(tmp), list));
        }
//        System.out.println(list);
        System.out.print(joiner.toString());
    }

    private static String use(int capacity, List<Node> list) {
        for (Node node: list) {
            if (node.capacity < capacity || node.num <= 0) {
                continue;
            }
            node.setNum(node.getNum() - 1);
            return String.valueOf(Boolean.TRUE);
        }
        return String.valueOf(Boolean.FALSE);
    }
}
