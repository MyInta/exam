package something.huawei.helper6;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/20
 * @describe
 * 某系统中有众多服务，每个服务用字符串（只包含字母和数字，长度<=10）唯一标识，
 * 服务间可能有依赖关系，如A依赖B,则当B
 * 故障时导致A也故障。依赖具有传递性，如A依赖B、B依赖C,则当C故障时导致B故障，也导致A故障。
 * 给出所有依赖关系，以及当前已知故障服务，要求输出所有正常服务。
 * 依赖关系：服务1-服务2 表示服务1依赖服务2
 * 不必考虑输入异常，用例保证：依赖关系列表、故障列表非空，且依赖关系数和故障服务数都不会超过3000，
 * 输入：
 * 逗号分隔的依赖关系列表
 * 逗号分隔的故障服务列表
 * 输出：
 * 依赖关系列表中提及的所有所有服务中可以正常工作的服务列表，用逗号分隔，按照依赖关系列表中出现的次序排序
 * 没有正常节点输出单独一个逗号
 *
 * 输入：
 * a1-a2,a5-a6,a2-a3
 * a5,a2
 * 输出：
 * a6,a3
 *
 * 输入:
 * a1-a2
 * a2
 * 输出：
 * ,
 *
 */
public class Q3 {
    public static String method(String[] dicArr, String[] deadList) {
        // 获取依赖列表
        Map<String, Set<String>> map = new HashMap<>();
        for (String dic : dicArr) {
            String[] curArr = dic.split("-");
            Set<String> curSet = map.getOrDefault(curArr[1], new HashSet<>());
            curSet.add(curArr[0]);
            map.put(curArr[1], curSet);
        }
        // 整一个死亡列表，表示这些字符串都是不允许出现的
        Set<String> deadSet = new HashSet<>();
        for (String dead : deadList) {
            deadSet.add(dead);
            Set<String> curSet = map.getOrDefault(dead, new HashSet<>());
            Deque<String> queue = new LinkedList<>();
            if (!curSet.isEmpty()) {
                queue.addAll(curSet);
            }
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String temp = queue.poll();
                    if (map.containsKey(temp) && !deadSet.contains(temp)) {
                        queue.addAll(map.get(temp));
                    }
                    deadSet.add(temp);
                }
            }
        }
        // 记得如果啥也没有，也得输出逗号
        StringBuilder sb = new StringBuilder();
        Set<String> visited = new HashSet<>();
        for (String dic : dicArr) {
            String[] curArr = dic.split("-");
            for (String de : curArr) {
                if (deadSet.contains(de) || visited.contains(de)) {
                    continue;
                }
                visited.add(de);
                sb.append(de).append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.length() > 0 ? sb.toString() : ",";
    }

    // a1-a2,a5-a6,a2-a3
    // a5,a2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dicArr = sc.nextLine().split(",");
        String[] deadList = sc.nextLine().split(",");
        System.out.println(method(dicArr, deadList));
    }
}
