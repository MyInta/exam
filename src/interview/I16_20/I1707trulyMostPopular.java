package interview.I16_20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/3/26
 * @describe 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
 * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
 * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。
 * 设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，
 * 并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 *
 * 在结果列表中，选择字典序最小的名字作为真实名字。
 *
 * 示例：
 *
 * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
 * synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 *
 * 提示：
 *
 *     names.length <= 100000
 *
 */
public class I1707trulyMostPopular {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        //统计每个名字对应数量
        counts = new HashMap<>();
        for (String name : names) {
            //每个字符串拆分出名字和数量
            int indexL = name.indexOf('(');
            String realName = name.substring(0, indexL);
//            System.out.println(realName);
            int v = Integer.valueOf(name.substring(indexL + 1, name.length() - 1));
//            System.out.println(v);
            counts.put(realName, v);
        }
        //使用并查集来合并相同名字
        unionMap = new HashMap<>();
        for (String synonym : synonyms) {
            //找出两个名字
            int index = synonym.indexOf(',');
            String name1 = synonym.substring(1, index);
            String name2 = synonym.substring(index + 1, synonym.length() - 1);
//            System.out.println(name1 + " " + name2);
            //合并掉他们的掌门
            name1 = findParent(name1);
            name2 = findParent(name2);
            if (!name1.equals(name2)) merge(name1, name2);
        }
        //现在，我们拥有了每人对应数量，以及每人的掌门是谁，我们开始统计所有掌门以及对应他们帮派内成员所有数量
        String[] res = new String[counts.size()];
        int index = 0;
        for (String name : counts.keySet()) {
            StringBuilder sb = new StringBuilder(name);
            sb.append('(').append(counts.get(name)).append(')');
            res[index ++] = sb.toString();
        }
        return res;
    }
    private Map<String, String> unionMap;
    private Map<String, Integer> counts;
    private String findParent(String name) {
        while (unionMap.containsKey(name)) {
            name = unionMap.get(name);
        }
        return name;
    }
    private void merge(String name1, String name2) {
        int frequency = counts.getOrDefault(name1, 0) + counts.getOrDefault(name2, 0);
        //如果x在y前面，y掌门就跟着x了
        if (name1.compareTo(name2) < 0) {
            unionMap.put(name2, name1);
            counts.remove(name2);
            counts.put(name1, frequency);
        } else {
            unionMap.put(name1, name2);
            counts.remove(name1);
            counts.put(name2, frequency);
        }
    }
}
