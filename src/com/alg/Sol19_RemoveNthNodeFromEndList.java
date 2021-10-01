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

    //  https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0019.%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACN%E4%B8%AA%E8%8A%82%E7%82%B9.md
    public ListNode removeNthNodeFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p1 = pre;
        ListNode p2 = pre;
        while (n > 0 && p1 != null) {
            p1 = p1.next;
            n--;
        }

        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p2 != null && p2.next != null) {
            p2.next = p2.next.next;
        }
        return pre.next;
    }
    /*Complexity Analysis

Time complexity : O(L)O(L). The algorithm makes one traversal of the list of LL nodes. Therefore time complexity is O(L)O(L).

Space complexity : O(1)O(1). We only used constant extra space.*/
    public ListNode removeNthFromTheEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
