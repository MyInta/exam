package leetcode_inta.leetcode301_350;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/17
 * @describe 给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的项或者为一个整数，或者是另一个列表。
 *
 * 示例 1:
 *
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 *
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,4,6]。
 *
 */
//public class Q341NestedIterator  implements Iterator<Integer> {

//    private interface NestedInteger{
//         // @return true if this NestedInteger holds a single integer, rather than a nested list.
//        //如果是嵌套list，返回false，如果是单个integer,返回true
////        private boolean isInteger();
////        private boolean isInteger(){return true;}
//
//        // @return the single integer that this NestedInteger holds, if it holds a single integer
//        //返回嵌入的单个整数（如果他有的话）,否则返回null
//         // Return null if this NestedInteger holds a nested list
////         private Integer getInteger();
////         private Integer getInteger(){return -1;}
//
//         // @return the nested list that this NestedInteger holds, if it holds a nested list
//         // Return null if this NestedInteger holds a single integer
//        //返回嵌入列表，若是整数，则返回null
////         private List<NestedInteger> getList();
////         private List<NestedInteger> getList(){return null;}
//    }
//
//    private List<NestedInteger> nestedList;
//    private int cur;
//    private ArrayList<Integer> nums;
//    public Q341NestedIterator(List<NestedInteger> nestedList) {
//        this.nestedList = nestedList;
//        cur = 0;
//        nums = new ArrayList<>();
//        solution(nestedList);
//    }
//
//    private void solution(List<NestedInteger> list) {
//        for (NestedInteger n : list) {
//            if (!n.isInteger()) {
//                solution(n.getList());
//            } else {
//                nums.add(n.getInteger());
//            }
//        }
//    }
//    @Override
//    public Integer next() {
//       return nums.get(cur ++);
//    }
//
//    @Override
//    public boolean hasNext() {
//        return cur < nums.size();
//    }
//}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
