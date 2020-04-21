package leetcode_inta.exam_main.leetcode_exam2020.D185;

import java.util.*;

/**
 * @author inta
 * @date 2020/4/19
 * @describe
 */
public class Q2 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        //需要一个《String,<String,number>》关系表
        Map<String, Map<String, Integer>> map = new HashMap<>();
        //用一个set来储存所有食物
        Set<String> foods = new HashSet<>();
        //一个set来存储桌子
        Set<String> tables = new HashSet<>();
        for (List<String> order : orders) {
            String table = order.get(1);
            String food = order.get(2);
            if (!map.containsKey(table)) {
                map.put(table, new HashMap<>());
            }
            //添加食物
            map.get(table).put(food, map.get(table).getOrDefault(food, 0) + 1);
            foods.add(food);
            tables.add(table);
        }
        String[] foods_Str = new String[foods.size()];
        int index = 0;
        for (String food : foods) {
            foods_Str[index ++] = food;
        }
        Arrays.sort(foods_Str);

        Integer[] tables_Str = new Integer[tables.size()];
        index = 0;
        for (String table : tables) {
            tables_Str[index ++] = Integer.valueOf(table);
        }
        Arrays.sort(tables_Str);

        List<List<String>> res = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        for (String food : foods_Str) {
            title.add(food);
        }
        res.add(title);
        //此时标题添加好了，就开始添加桌子和食物信息
        for (Integer table : tables_Str) {
            List<String> list = new ArrayList<>();
            list.add(table + "");
            for (String food : foods_Str) {
                list.add(map.get(table + "").getOrDefault(food, 0) + "");
            }
            res.add(list);
        }
        return res;
    }
}
