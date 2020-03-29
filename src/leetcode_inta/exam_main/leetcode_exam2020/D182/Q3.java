package leetcode_inta.exam_main.leetcode_exam2020.D182;


import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/3/29
 * @describe
 */
public class Q3 {

    private class UndergroundSystem {

        private class Pair{
            String str;
            int v;
            Pair(String str, int v){
                this.str = str;
                this.v = v;
            }
            public String getStr() {
                return str;
            }
            public int getV() {
                return v;
            }
        }

        private Map<Integer, Pair> person;
        private Map<String, int[]> dis;
        public UndergroundSystem() {
            person = new HashMap<>();
            dis = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            person.put(id, new Pair(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            String from = person.get(id).getStr();
            int v = person.get(id).getV();
            int[] temp = dis.getOrDefault(from + stationName, new int[]{0,0});
            temp[0] += t-v;
            temp[1] ++;
            dis.put(from+stationName, temp);
        }

        public double getAverageTime(String startStation, String endStation) {
            int[] temp = dis.getOrDefault(startStation + endStation, new int[]{0,0});
            return temp[0] * 1.0/ temp[1];
        }
    }
}
