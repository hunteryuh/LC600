package com.alg;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HAU on 11/27/2017.
 */
/*Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

// LRUCache cache = new LRUCache( 2 / capacity / );

/*        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4*/
// */
/*The LRU cache is a hash table of keys and double linked nodes.
The hash table makes the time of get() to be O(1).
The list of double linked nodes make the nodes adding/removal operations O(1).*/
public class Sol146_LRUCache {
    // Hashmap + DoubleLinkedList
    public class LRUCache{
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode(int key, int value){
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        private int capacity;
        private int size;
        private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
        private DLinkedNode head = new DLinkedNode(-1,-1);
        private DLinkedNode tail = new DLinkedNode(-1,-1);

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;

            // move the accessed node to the head;
            moveToHead(node);

            return node.value;
        }
        private void moveToHead(DLinkedNode node){
            /**
             * Move certain node in between to the head.
             */
            removeNode(node);
            addNode(node);
        }

        private void removeNode(DLinkedNode node){
            DLinkedNode before = node.prev;
            DLinkedNode after = node.next;
            before.next = after;
            after.prev = before;
        }

        private void addNode(DLinkedNode node){
            DLinkedNode after = head.next;
            head.next = node;
            node.prev = head;
            node.next = after;
            after.prev = node;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                ++size;
                if (size > capacity) {
                    // pop the tail
                    DLinkedNode tail = popTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }
          // rewrite put with map size
//        public void put(int key, int value) {
//            if (map.containsKey(key)) {
//                Node node = map.get(key);
//                node.value = value;
//                deleteNode(node);
//                addToHead(node);
//                return;
//            }
//            Node node = new Node(key, value);
//            map.put(key, node);
//            addToHead(node);
//            // size++;
//            if (map.size() > capacity) {
//                Node end = tail.prev;
//                map.remove(end.key);
//                deleteNode(end);
//                // size--;
//            }
//        }

        private DLinkedNode popTail() {
            // pop the current tail
            DLinkedNode node = tail.prev;
            removeNode(node);
            return node;
        }

//        private void moveToTail(Node cur) {
//            cur.prev = tail.prev;
//            tail.prev = cur;
//            cur.prev.next = cur;
//            cur.next = tail; // move to the tail, dummy "tail"
//        }

//        public void put(int key, int value) {
//            // the internal get method will update the key's position in the linked list
//            // get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail
//            if (get(key)!= -1){
//                map.get(key).value = value;
//                return;
//            }
//            if (map.size() == capacity) {
//                map.remove(head.next.key);
//                head.next = head.next.next;
//                head.next.prev = head;
//            }
//            Node insert = new Node(key,value);
//            map.put(key,insert);
//            moveToTail(insert);
//
//        }
    }

    // https://leetcode.com/problems/lru-cache/discuss/45939/Laziest-implementation%3A-Java's-LinkedHashMap-takes-care-of-everything
    public class LRUCache2{
        int capacity;
        LinkedHashMap<Integer, Integer> map;

        public LRUCache2(int capacity) {
            this.capacity = capacity;
            // by passing in true, we turned on access-order, whereas the default was insertion-order.
            // 这个参数实现了LRU，不管是get还是put, 都会放到latest.
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                public boolean removeEldestEntry(Map.Entry eldest) {
                     return size() > capacity;
                 }
            };
        }
        public int get(int key) {
            return map.getOrDefault(key, -1);
        }
        public void put(int key, int value) {
            map.put(key, value);
        }
    }


    // https://www.jiuzhang.com/problem/lru-cache/
    // 用LinkedHashMap模拟Queue记录每个entry被用的先后顺序：
    // (1) 任何被get或被set的元素都会被移到LinkedHashMap的最后；
    // (2) 若新插入元素后，LinkedHashMap超出capacity，则移除首位元素。
    public class LRUCache_lh {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        int cap;

        public LRUCache_lh(int capacity) {
            cap = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;

            int val = map.remove(key);
            map.put(key, val);
            return val;
        }

        public void set(int key, int value) {
            if (map.containsKey(key)) {
                map.remove(key);
                map.put(key, value);
                return;
            }

            map.put(key, value);

            if (map.size() > cap)
                map.remove(map.entrySet().iterator().next().getKey());
//                map.remove(map.keySet().iterator().next());
        }
    }

}
