package tencent.exam_main.leetcode_exam2020.D175;

import java.util.*;

/**
 * @author inta
 * @date 2020/2/9
 * @describe
 */
public class D3 {


}
class TweetCounts{

    private Map<String, TreeMap<Integer, Integer>> map;
    public TweetCounts() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        TreeMap<Integer, Integer> tm = map.getOrDefault(tweetName, new TreeMap<>());
        tm.put(time, tm.getOrDefault(time, 0) + 1);
        map.put(tweetName, tm);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList<>();
        if (!map.containsKey(tweetName)) return res;
        TreeMap<Integer, Integer> tm = map.get(tweetName);
        int delta = 0;
        switch (freq) {
            case "minute" :
                delta = 60;
                break;
            case "hour" :
                delta = 3600;
                break;
            case "day" :
                delta = 24 * 3600;
                break;
        }
        while (startTime <= endTime) {
            //在list里找[startTime,startTime + delta)
            int end = Math.min(startTime + delta, endTime + 1);
            int count = 0;
            Map<Integer, Integer> nums = tm.subMap(startTime, end);
            for (int v : nums.values()) {
                count += v;
            }
            res.add(count);
            startTime = end;
        }
        return res;
    }
}