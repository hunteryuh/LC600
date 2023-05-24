package com.alg;

/**
 * Created by HAU on 8/17/2017.
 */

/*
Merge two sorted linked lists and return it as a new list.
        The new list should be made by splicing together the nodes of the first two lists.*/


public class Sol21_MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode merge2Lists(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0); // dummy node for head
        ListNode p = head;

        while (l1 != null && l2!= null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;  // move the pointer for both cases
        }
        if (l1 != null) {
            p.next = l1;
        } else {
            p.next = l2;
        }
        return head.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 == null) {
            p.next = list2;
        } else {
            p.next = list1;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode(2);
        h1.next = new ListNode(5);
        h1.next.next = new ListNode (10);

        ListNode h2 = new ListNode (1);
        h2.next = new ListNode(7);
        ListNode m = merge2Lists(h1,h2);

        while (m!= null) {
            System.out.println(m.val);
            m = m.next;
        }

    }

}
