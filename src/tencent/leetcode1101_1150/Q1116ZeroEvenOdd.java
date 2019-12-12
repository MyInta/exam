package tencent.leetcode1101_1150;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author inta
 * @date 2019/12/12
 * @describe 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 *
 * 输入：n = 5
 * 输出："0102030405"
 */
public class Q1116ZeroEvenOdd {
    private int n;

    private Semaphore one;
    private Semaphore two;
    private Semaphore three;

    public Q1116ZeroEvenOdd(int n) {
        this.n = n;
        //初始化zero有一个信号
        one = new Semaphore(1);
        two = new Semaphore(0);
        three = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i ++) {
            //查看有没有信号
            one.acquire();
            //他一直打印的是0
            printNumber.accept(0);
            if (i % 2 == 0) {
                //偶数情况，给偶数调用方法释放一个信号
                three.release();
            } else {
                //奇数情况，释放奇数信号
                two.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        //打印数量要控制好，最大是n个
        for (int i = 2; i <= n; i += 2) {
            two.acquire();
            printNumber.accept(i);
            //然后给zero方法释放信号
            one.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        //打印数量要控制好，最大是n个
        for (int i = 1; i <= n; i += 2) {
            three.acquire();
            printNumber.accept(i);
            //然后给zero方法释放信号
            one.release();
        }
    }
}
