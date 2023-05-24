package com.alg;

/**
 Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class Sol206_ReverseLinkedlist {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }
    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }

        ListNode cur = head;
        ListNode newhead = reverseList(head.next);
        cur.next.next = cur;
        cur.next = null;
        return newhead;

    }
    public static ListNode reverseIter(ListNode head){
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
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
        printList(reverseIter(h3));
    }
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }
}
