package com.alg;

public class Sol1721_SwapNodesInALinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode swapNodes(ListNode head, int k) {
        int len = 0;
        ListNode s = head;
        while (s != null) {
            s = s.next;
            len++;
        }

        if (len < k) {
            return head;
        }

        if ( k > len + 1 - k ) {
            k = len + 1 - k;
        }

        if ( k * 2 == len + 1) return head; // no swap, at middle
        ListNode x = head;
        ListNode prex = null;
        for (int i = 1; i < k; i++) {
            prex = x;
            x = x.next;
        }

        ListNode y = head;
        ListNode prey = null;
        for (int i = 1; i < len + 1 - k; i++) {
            prey = y;
            y = y.next;
        }

        if (prex != null) {
            prex.next = y;
        }

        if (prey != null) {
            prey.next = x;
        }

        ListNode temp = x.next;
        x.next = y.next;
        y.next = temp;

        if (k == 1) head = y;

        return head;
    }

    public static void main(String[] args) {
        ListNode h3 = new ListNode(4);
        h3.next = new ListNode(5);
        h3.next.next = new ListNode(1);
        h3.next.next.next = new ListNode(6);
        h3.next.next.next.next = new ListNode(2);
        h3.next.next.next.next.next = new ListNode(9);
        printList(h3);
        System.out.println();
        System.out.println("after Swap pairs");
        printList(swapNodes(h3, 6));
//        printList(swapNodes(h3, 2));
//        printList(swapNodes(h3, 3));
//        printList(swapNodes(h3, 4));
//        printList(swapNodes(h3, 5));

    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

}
