package com.alg;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by HAU on 1/24/2018.
 */
/*Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

*/
public class Sol23_MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        /*//最佳方法，面试官肯定会希望通过这道题考察你对PriorityQueue的掌握
*/
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val < o2.val)
                    return -1;
                else if( o1.val == o2.val){
                    return 0;
                }else{
                    return 1;
                } //Override the compare function, sort by the head of the lists in ascending order
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for(ListNode node : lists){
            if(node != null){
                pq.add(node); // all head nodes of the lists
            }
        }
        while(!pq.isEmpty()){
            tail.next = pq.poll();  // get all smallest nodes in the polling
            tail = tail.next;
            if ( tail.next != null){
                pq.add(tail.next);  // add all other nodes in the list one by one
            }
        }
        return dummy.next;

    }
}
