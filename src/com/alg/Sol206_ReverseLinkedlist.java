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

    // recursive 2
    public static ListNode reverseList2(ListNode head) {
        return reverse(head, null);
    }
    private static ListNode reverse(ListNode head, ListNode newHead) {
        if (head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverse(next, head);
    }

    //iterative
    public static ListNode reverseIter(ListNode head){
        ListNode prev = null; // prev , also the newHead
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
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
