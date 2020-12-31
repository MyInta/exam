package something.huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/11/12
 * @describe 1、 一个积木接龙游戏，
 * 若干积木， 每个积木可能有前向接口，
 * 或者后向接口，或者两者， 注意接口可能有不同形状，
 * 求一种拼接所有积木的方法
 */
public class Q4 {


    //值为负值，不存在形状
    public static class Jimu{
        //前接口的形状
        int pre;
        //后接口的形状
        int next;
        //用来判断是否拥有双接口 true 不拥有 false 拥有
        boolean onlyOne;
        //单接口的形状
        int one;
        public Jimu(int pre, int next, boolean onlyOne, int one) {
            this.pre = pre;
            this.next = next;
            this.onlyOne = onlyOne;
            this.one = one;
        }
    }




    public static void main(String[] args) {

        Jimu jm1 = new Jimu(-1,-1, true, 2);
        Jimu jm2 = new Jimu(2,3, false, -1);
        Jimu jm3 = new Jimu(3,4, false, -1);
        Jimu jm4 = new Jimu(4,6, false, -1);
        Jimu jm5 = new Jimu(8,9, false, -1);

        Set<Jimu> set = new HashSet<>();
        set.add(jm1);
        set.add(jm2);
        set.add(jm3);
        set.add(jm4);
        set.add(jm5);

        //默认结果值，认为积木能拼成
        boolean res = true;
        boolean onlyOne = jm1.onlyOne;
        int pre = jm1.pre;
        int next = jm1.next;
        int one = jm1.one;
        //如果随机选取的一块积木只存在单接口
        boolean find = true;
        while (find && set.isEmpty()) {
            //初始化为没找到
            find = false;
            //下一个拼图
            Jimu temp = null;
            //如果当前拼出来的图形是单接口的
            if (onlyOne) {
                //从集合中找下一个拼图
                for (Jimu jm : set) {
                    if (jm.pre == one || jm.next == one || jm.one == one) {
                        //标记找到了
                        find = true;
                        temp = jm;
                        //如果找到的下一个拼图是单接口
                        if (jm.one == one) {
                            //直接返回判断集合中是否只有一个拼图了
                            res = set.size() == 1;
                            break;
                        }
                        //如果下一个拼图是双接口，考虑到我们用的是一个单接口拼图，所以寻找的时候，也是一个单接口出发
                        one = temp.pre == one ? temp.next : temp.pre;
                        break;
                    }
                }
                //如果遍历之下没有找到，说明不存在,find没有被修改
                res = false;
            } else {
                //如果目前拼接结果是双接口形式的
                for (Jimu jm : set) {
                    if (jm.pre == pre || jm.next == pre || jm.one == pre || jm.pre == next || jm.next == next || jm.one == next) {
                        //标记找到了
                        find = true;
                        temp = jm;
                        //当我们找到的下一个拼图是单接口的，那么整体的拼图也会变成单接口
                        if (jm.onlyOne) {
                            onlyOne = true;
                            //那么此时剩下的接口为拼接之后的另一个接口
                            one = pre == jm.one ? next : pre;
                        } else {
                            //否则的话，说明我们找到的下一个拼图还是双接口的，那么形成的拼图也是双接口的
                            pre = pre == jm.pre ? next : pre;
                            //我们新的形成的拼图设置其前接口为原先拼图的前接口或后接口，而后接口是找到的拼图的没有被拼接的部分
                            next = jm.pre == pre ? jm.next : jm.pre;
                        }
                        break;
                    }
                }
            }
            //如果没有找到下一个拼图
            if (temp == null) {
                res = false;
                //跳出继续的拼图寻找
                break;
            }
            //找到的下一个拼图将其删除
            set.remove(temp);
        }
        //预测这是true
//        System.out.println(res);
        //预测为false
        System.out.println(res);
    }



}
