package com.alg;

/**
 * Created by HAU on 11/26/2017.
 */
/*Given a linked list, remove the nth node from the end of list and return its head.*/
public class Sol19_RemoveNthNodeFromEndList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy, p2 = dummy;
        for ( int i = 0; i <= n; i++){
            p1 = p1.next; // n+ 1 steps
        }
        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return dummy.next;
    }
    /*Complexity Analysis

Time complexity : O(L)O(L). The algorithm makes one traversal of the list of LL nodes. Therefore time complexity is O(L)O(L).

Space complexity : O(1)O(1). We only used constant extra space.*/
}
