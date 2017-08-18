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

        while ( l1 != null && l2!= null){
            if (l1.val < l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null){
            p.next = l1;
        }else{
            p.next = l2;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode h1 = new ListNode(2);
        h1.next = new ListNode(5);
        h1.next.next = new ListNode (10);

        ListNode h2 = new ListNode (1);
        h2.next = new ListNode(7);
        ListNode m = merge2Lists(h1,h2);

        while (m!= null){
            System.out.println(m.val);
            m = m.next;
        }

    }

}
