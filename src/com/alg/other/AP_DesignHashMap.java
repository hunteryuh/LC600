package com.alg.other;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
// https://leetcode.com/problems/design-hashmap/solutions/?orderBy=most_votes
public class AP_DesignHashMap {
// https://leetcode.com/problems/design-hashmap/solutions/227081/java-solutions/ leetcode 706
    /*
     * We want to implement a hash map without using any library provided by the programming language.
     * API:
     */
    class Node {
        String key;
        String value;
        Node next;
        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    public class HashMap {
        Node[] arr;
        int size;
        public HashMap(int size) {
            arr = new Node[size];
            this.size = size;
        }

        // a -> 123445 % size = 5
        // d -> 764575 % size = 5

        // key1 -> v1
        // key1 -> v2

        public void put(String key, String value) {

            int index = hash(key) % size;
            Node node = arr[index];
            if (node == null) {
                arr[index] = new Node(key, value);
                return;
            }
            Node prev = null;
            while (node != null && !node.key.equals(key)) {
                prev = node;
                node = node.next;
            }
            if (node == null) {
                node = new Node(key, value);
            }
            node.value = value;

        }

        // 0 -> (k, v) -> (k1, v1) -> (k2, v2) -> null

        // 1 ->

        public String get(String key) {
            int index = hash(key) % size;
            Node node = arr[index];

            while (node != null && !node.key.equals(key)) {
                node = node.next;
            }
            if (node != null) {
                return node.value;
            }
            return null;
        }

        public void remove(String key) {

        }

        //
        private int hash(String key) {
            int hashCode = key.hashCode();
            hashCode = hashCode < 0 ? hashCode * -1 : hashCode;
            return hashCode;
        }
    }



    // use large array
    class MyHashMap
    {
        int[] map;

        public MyHashMap()
        {
            map = new int[1000001];
            Arrays.fill(map,-1);
        }

        public int get(int key)
        {
            return map[key];
        }

        public void put(int key, int value)
        {
            map[key] = value;
        }

        public void remove(int key)
        {
            map[key] = -1;
        }
    }
}
