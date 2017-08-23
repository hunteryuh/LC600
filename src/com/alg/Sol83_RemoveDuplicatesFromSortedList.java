package com.alg;

/**
 * Created by HAU on 8/22/2017.
 */
/*Given a sorted linked list, delete all duplicates such that
each element appear only once.

        For example,
        Given 1->1->2, return 1->2.
        Given 1->1->2->3->3, return 1->2->3.*/
public class Sol83_RemoveDuplicatesFromSortedList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){ val = x;}
    }
    public static ListNode deleteDuplicates(ListNode head){
        ListNode cur = head;
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(12);
        l1.next.next.next.next = new ListNode(12);
        ListNode head = deleteDuplicates(l1);
        while ( head != null){
            System.out.println(head.val);
            head = head.next;
        }

    }

}
