package com.alg;

import java.util.HashMap;

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
    public class LRUCache{
        private class Node{
            Node prev;
            Node next;
            int key;
            int value;
            public Node(int key, int value){
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        private int capacity;
        private HashMap<Integer, Node> map = new HashMap<>();
        private Node head = new Node(-1,-1);
        private Node tail = new Node(-1,-1);

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;

        }

        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            // remove current
            Node cur = map.get(key);
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;

            // move the cur to tail
            moveToTail(cur);
            return map.get(key).value;
        }

        private void moveToTail(Node cur) {
            cur.prev = tail.prev;
            tail.prev = cur;
            cur.prev.next = cur;
            cur.next = tail; // move to the tail, dummy "tail"
        }

        public void put(int key, int value) {
            // the internal get method will update the key's position in the linked list
            if (get(key)!= -1){
                map.get(key).value = value;
                return;// why
            }
            if(map.size() == capacity){
                map.remove(head.next.key);
                head.next = head.next.next;
                head.next.prev = head;
            }
            Node insert = new Node(key,value);
            map.put(key,insert);
            moveToTail(insert);

        }
    }

}
