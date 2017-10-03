package com.alg;

/**
 * Created by HAU on 10/3/2017.
 */
public class Sol237_DeleteNodeInALinkedList {
    public static class ListNode {
        int val;
        Sol206_ReverseLinkedlist.ListNode next;

        ListNode(int x) { val = x; }
    }
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next= node.next.next;
    }
}
