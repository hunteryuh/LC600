package com.alg;

/**
 * Created by HAU on 6/14/2017.
 */

/*Given a linked list, swap every two adjacent nodes and return its head.

        For example,
        Given 1->2->3->4, you should return the list as 2->1->4->3.*/
public class Sol24_SwapNodesInPairs {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
    public static ListNode swapPairs(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode newhead = head.next;
        head.next = swapPairs(head.next.next);
        newhead.next = head;
        return newhead;
    }
    public static void main(String[] args) {
        ListNode h3 = new ListNode(4);
        h3.next = new ListNode(5);
        h3.next.next = new ListNode(1);
        h3.next.next.next = new ListNode(6);
        h3.next.next.next.next = new ListNode(2);
        printList(h3);
        System.out.println("after Swap pairs");
        printList(swapPairs(h3));
    }
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

}
