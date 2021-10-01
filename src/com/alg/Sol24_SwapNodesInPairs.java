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
//        printList(h3);
//        System.out.println("after Swap pairs");
//        printList(swapPairs(h3));
        Sol24_SwapNodesInPairs ss = new Sol24_SwapNodesInPairs();
        printList(ss.swapPairs3(h3));
    }
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head.next;
        first.next = swapPairs(second.next);
        second.next = first;

        return second;
    }

    public ListNode swapPairs3(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode dummy = pre;
        while (pre.next !=null && pre.next.next != null) {
            ListNode curHead = pre.next;
            ListNode nextHead = pre.next.next.next;
            pre.next = pre.next.next;
            pre.next.next = curHead;
            curHead.next = nextHead;

            pre = pre.next.next;
        }
        return dummy.next;
    }

    public ListNode swapPair4(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode first = pre.next;
            ListNode second = pre.next.next;

            //swap
            first.next = second.next;
            second.next = first;
            pre.next = second;

            // move pre
            pre = first;
        }
        return dummy.next;
    }

}
