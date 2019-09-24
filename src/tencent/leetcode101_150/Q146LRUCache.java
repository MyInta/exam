package tencent.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2019/9/24
 * @describe 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作：
 * 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * LRUCache cache = new LRUCache( 2 )
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 */
public class Q146LRUCache {

    private HashMap<Integer, Integer> cache;
    private Queue<Integer> queue;
    private int capacity;
    public Q146LRUCache(int capacity) {
        cache = new HashMap<>();
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            queue.remove(key);
            queue.add(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            if (cache.size() == capacity) {
                cache.remove(queue.poll());
            }
            queue.add(key);
            cache.put(key, value);
        } else {
            cache.put(key, value);
            queue.remove(key);
            queue.add(key);
        }
    }

    //自创一个双向map的结点
    private class Node{
        public int key;
        public int val;
        public Node pre;
        public Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private class DLinked{
        public Node head;
        public Node end;
        private int size;

        //初始化
        public DLinked() {
            head = new Node(0, 0);
            end = new Node(0, 0);
            head.next = end;
            end.pre = head;
            size = 0;
        }
        //在链表头部加元素
        public void addFirst(Node first) {
            first.next = head.next;
            head.next.pre = first;
            head.next = first;
            first.pre = head;
            size++;
        }
        //删除链表某结点元素(x一定存在的)
        public void remove(Node x) {
            x.pre.next = x.next;
            x.next.pre = x.pre;
            size--;
        }
        //删除链表最后一个位置,并返回
        public Node removeLast() {
            if (end.pre == head) {
                return null;
            }
            Node last = end.pre;
            remove(last);
            return last;
        }
        //返回链表长度
        public int getSize() {
            return size;
        }
    }

    private class Q146LRUCache2{
        //用于缓存序列
        private DLinked cache;
        //key-Node之间关系的映射
        private HashMap<Integer, Node> map;
        //长度
        private int size;
        public Q146LRUCache2(int capacity) {
            cache = new DLinked();
            map = new HashMap<>();
            this.size = capacity;
        }

        public int get(int key) {
            //如果没有key，直接返回-1
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            //用于跟新缓存
            put(key, node.val);
            return node.val;
        }

        public void put(int key, int value) {
            //重复key的情况下
            Node n = new Node(key, value);
            if (map.containsKey(key)) {
                //需要更新下缓存:删除旧的，保存新的到最前面
                cache.remove(map.get(key));
                cache.addFirst(n);
                map.put(key, n);
            } else {
                //需要判断有没有超过边界长度
                if (size == cache.getSize()) {
                    //遇到瓶颈就删除缓存中的旧元素
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                //统一添加新结点,并更新缓存
                map.put(key, n);
                cache.addFirst(n);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
