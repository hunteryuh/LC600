package com.alg;

/**
 * Created by HAU on 10/2/2017.
 */
/*Reverse a linked list from position m to n. Do it in-place and in one-pass.*/
public class Sol92_ReverseLInkedListII {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }
    public static ListNode reverseBetween(ListNode head, int m, int n){
        if ( m >= n || head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < m - 1; i++){
            prev = prev.next;
        }
        ListNode start = prev.next; // beginning of the sublist
        ListNode post = start.next; // to be reversed
        for (int i = 0; i < n - m; i++){
            start.next = post.next;
            post.next = prev.next;
            prev.next = post;
            post = start.next;
        }
        return dummy.next;
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
        printList(reverseBetween(h3,2,4));
    }
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

    // redo
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode cur = head;
        ListNode pre = null;
        int index = 1;
        while (index < left) {
            pre = cur;
            cur = cur.next;
            index++;
        }
        // pre = 1; cur = 2;
        ListNode beforeHead = pre;
        ListNode curHead = cur;
        while (index <= right) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            index++;
        }

        curHead.next = cur;
        if (beforeHead == null) {
            return pre;
        }
        beforeHead.next = pre;
        return head;

    }
}
