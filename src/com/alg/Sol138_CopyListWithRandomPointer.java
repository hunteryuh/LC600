package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 9/19/2017.
 */
/*A linked list is given such that each node contains
an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.*/
public class Sol138_CopyListWithRandomPointer {
    //using map
    public static class RandomListNode{
        int label;
        RandomListNode next, random;
        RandomListNode(int x){
            label = x;
        }
    }

    //using map
    public static RandomListNode copyRandomList(RandomListNode head){
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        //first step: copy all nodes;
        RandomListNode node = head;
        while ( node != null){
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        // 2nd step: assign next and random pointer
        node = head; // put the pointer at the head
        while(node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(node);
    }

    public static void main(String[] args) {
        // Pushing data in the linked list.
        RandomListNode list = new RandomListNode(5);
        list.next = new RandomListNode(4);
        list.next.next = new RandomListNode(3);
        list.next.next.next = new RandomListNode(2);
        list.next.next.next.next = new RandomListNode(1);
        list.random = list.next.next;
        list.next.random = list;
        list.next.next.random = list.next;
        list.next.next.next.random = list.next;
        list.next.next.next.next.random = list.next.next.next;

        RandomListNode clone = copyRandomList2(list);

    }

    // using node manipulation
    // https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
    public static RandomListNode copyRandomList2(RandomListNode head){
        if (head == null) return null;
        copyNext(head);
        copyRandom(head);
        return splitList(head);
    }

    private static RandomListNode splitList(RandomListNode head) {
        RandomListNode newhead = head.next;
        while (head != null){
            RandomListNode tmp = head.next;
            head.next = tmp.next;
            head = head.next;
            if ( tmp.next != null){
                tmp.next = tmp.next.next;
            }
        }
        return newhead;
    }

    private static void copyRandom(RandomListNode head) {
        while ( head != null){
            if (head.next.random != null){
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
    }


    private static void copyNext(RandomListNode head) {
        while (head != null){
            RandomListNode newnode = new RandomListNode(head.label);
            newnode.random = head.random;
            newnode.next = head.next;
            head.next = newnode;
            head = head.next.next;
        }
    }
}