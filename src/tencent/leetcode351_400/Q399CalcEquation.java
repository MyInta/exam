package tencent.leetcode351_400;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/9/20
 * @describe 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，
 * 并返回计算结果。如果结果不存在，则返回 -1.0。
 *
 * 示例 :
 * 给定 a / b = 2.0, b / c = 3.0
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * 输入为: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)，
 *  其中 equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。
 * 以上为方程式的描述。 返回vector<double>类型。
 *
 * 基于上述例子，输入如下：
 *
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
 * values(方程式结果) = [2.0, 3.0],
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 */
public class Q399CalcEquation {
    //思想是建立一个图graph再dfs或bfs找出解，其中图用k/v保存点与点之间路径值关系
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        HashMap<String, HashMap<String, Double>> graph = createGraph(equations, values);
        //遍历问题querise来对每一个问题使用解答方法getRes获取
        for (int i = 0; i < queries.size(); i++) {
            //每次都需要新建一个保存是否访问过key的HashSet
            double oneRes = getRes(graph, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
            res[i] = oneRes;
        }
        return res;
    }

    /**
     * 建图
     * @param values a-b路径上的值
     * @param equations 共有哪些关系等式
     * @return 返回建立的关系图
     */
    private HashMap<String, HashMap<String, Double>> createGraph(List<List<String>> equations, double[] values) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        //遍历所有等式，将等式如a->b = 2信息保存到k/v中
        for (int i = 0; i < equations.size(); i++) {
            //如果等式中的左侧字符(左侧是get(0)，而右侧是get(1))还没有被用作key建立map，就新建一个
            if (graph.get(equations.get(i).get(0)) == null) {
                graph.put(equations.get(i).get(0), new HashMap<>());
            }
            //将a->b 的b和路径值value添加到map中
            graph.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);
            //添加一个反向的b->a的关系,先看看有没有创建关系
            if (graph.get(equations.get(i).get(1)) == null) {
                graph.put(equations.get(i).get(1), new HashMap<>());
            }
            //将1/values保存到反向路径上
            graph.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1/values[i]);
        }
        return graph;
    }

    /**
     * 深度遍历，找到每一个公式 start->end的解
     * @param graph 图
     * @param start a-b的a
     * @param end a-b的b
     * @param visited 防止走回头路，单向深入a-b
     * @return 返回走成功的某条路径长度
     */
    private double getRes(HashMap<String, HashMap<String, Double>> graph, String start,
                          String end, HashSet<String> visited) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            //说明start或end不存在于图，即返回-1.0即可
            return -1.0;
        }
        //直到图中的k/v中v的key是终点时，返回值
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        //我们把目前这一步的key设为访问过了
        visited.add(start);
        //如果没找到终点，就深入遍历对应k为start的k/v中的所有v，直到跳到上面的判断中可以找到end为止
        for (Map.Entry<String, Double> entrys : graph.get(start).entrySet()) {
            //在此之前，我们需要用到visited判断，不要重复访问某个key了
            if (!visited.contains(entrys.getKey())) {
                //没访问过的key继续遍历找end和返回值
                double res = getRes(graph, entrys.getKey(), end, visited);
                //直到走通一条，即可返回值,不过要注意，如果没走通的话，返回的是-1.0
                if (res != -1.0) {
                    return res*entrys.getValue();
                }
            }
        }
        //走了一遍都没有提前返回答案，说明没有解，即-1.0
        return -1.0;
    }
}
