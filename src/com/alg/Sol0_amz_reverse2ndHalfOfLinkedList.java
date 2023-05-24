package com.alg;

/**
 * Created by HAU on 12/26/2017.
 */
public class Sol0_amz_reverse2ndHalfOfLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode preHalf = null;
        while( fast!= null && fast.next!= null){
            fast = fast.next.next;
            preHalf = slow;
            slow = slow.next;
        }
        preHalf.next = reverse(slow);
        return head;
    }

    private static ListNode reverse(ListNode head){
        ListNode prev = null;
        while (head!= null){
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }
    public static void main(String[] args) {
    ListNode h3 = new ListNode(4);
    h3.next = new ListNode(5);
    h3.next.next = new ListNode(1);
    h3.next.next.next = new ListNode(6);
    h3.next.next.next.next = new ListNode(2);
    printList(h3);
        System.out.println();
        System.out.println("after reverse");
    //printList(reverseList(h3));
    printList(reverseList(h3));
}
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }
}

